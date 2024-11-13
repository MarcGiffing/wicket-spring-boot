package com.giffing.wicket.spring.boot.example.web.pages.customers;

import java.util.ArrayList;
import java.util.List;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.AbstrractActionItem;
import com.giffing.wicket.spring.boot.example.web.pages.BaseAuthenticatedPage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.events.CustomerDeletedEvent;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.extensions.markup.html.bootstrap.icon.FontAwesome6IconType;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilteredPropertyColumn;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerSort;
import com.giffing.wicket.spring.boot.example.web.general.action.panel.ActionPanel;
import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.links.ActionItemLink;
import com.giffing.wicket.spring.boot.example.web.general.action.panel.items.yesno.YesNoLink;
import com.giffing.wicket.spring.boot.example.web.html.basic.YesNoLabel;
import com.giffing.wicket.spring.boot.example.web.html.form.ValidationForm;
import com.giffing.wicket.spring.boot.example.web.html.panel.FeedbackPanel;
import com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter.AbstractCheckBoxFilter;
import com.giffing.wicket.spring.boot.example.web.html.repeater.data.table.filter.AbstractTextFieldFilter;
import com.giffing.wicket.spring.boot.example.web.pages.customers.create.CustomerCreatePage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.edit.CustomerEditPage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.events.CustomerChangedEvent;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.CustomerDataProvider;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.UsernameSearchTextField;

@WicketHomePage
@MountPath("custsomers")
@AuthorizeInstantiation("USER")
public class CustomerListPage extends BaseAuthenticatedPage {

	@SpringBean
	private CustomerRepositoryService customerRepositoryService;

	@SpringBean
	private WebSocketMessageBroadcaster webSocketMessageBroadcaster;
	
	private IModel<CustomerFilter> customerFilterModel;

	private FilterForm<CustomerFilter> filterForm;

	DataTable<Customer, CustomerSort> dataTable;
	
	public CustomerListPage() {
		super(new PageParameters());
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
		
		add(getWebSocketBehavior(feedbackPanel));
		
		customerFilterModel = new CompoundPropertyModel<>(new CustomerFilter());
		CustomerDataProvider customerDataProvider = new CustomerDataProvider(customerFilterModel);
		
		queue(new BootstrapBookmarkablePageLink<Customer>("create", CustomerCreatePage.class, Buttons.Type.Link)
				.setIconType(FontAwesome6IconType.plus_s)
				.setSize(Buttons.Size.Large));
		
		queue(new ValidationForm<>("form", customerFilterModel));
		queueFormComponent(new TextField<>("id"));
		queueFormComponent(new UsernameSearchTextField("usernameLike"));
		queueFormComponent(new TextField<String>("firstnameLike").add(StringValidator.minimumLength(3)));
		queueFormComponent(new TextField<String>("lastnameLike").add(StringValidator.minimumLength(3)));
		queueFormComponent(new CheckBox("active"));
		queue(cancelButton());
		
		customerDataTable(customerDataProvider);

	}

	private WebSocketBehavior getWebSocketBehavior(FeedbackPanel feedbackPanel) {
		return new WebSocketBehavior() {

			@Override
			protected void onPush(WebSocketRequestHandler handler, IWebSocketPushMessage message) {
				if (message instanceof CustomerChangedEvent event) {
					info("Customer changed " + event.getCustomer().getFirstname() + " " + event.getCustomer().getLastname());
					handler.add(feedbackPanel);
				}
				if (message instanceof CustomerDeletedEvent event) {
					warn("Customer deleted: " + event.getCustomer().getFirstname() + " " + event.getCustomer().getLastname());
					handler.add(feedbackPanel);
				}
			}

		};
	}

	private Button cancelButton() {
		Button cancelButton = new Button("cancel") {

			@Override
			public void onSubmit() {
				customerFilterModel.setObject(new CustomerFilter());
				getForm().clearInput();
				filterForm.clearInput();
			}

		};
		cancelButton.setDefaultFormProcessing(false);
		return cancelButton;
	}

	private void customerDataTable(CustomerDataProvider customerDataProvider) {

		filterForm = new FilterForm<>("filterForm", customerDataProvider);
		queue(filterForm);

		List<IColumn<Customer, CustomerSort>> columns = new ArrayList<>();
		columns.add(idColumn());
		columns.add(usernameColumn());
		columns.add(firstnameColumn());
		columns.add(lastnameColumn());
		columns.add(activeColumn());
		columns.add(actionColumn());

		dataTable = new AjaxFallbackDefaultDataTable<>("table", columns,
				customerDataProvider, 10);
		dataTable.setOutputMarkupId(true);
		FilterToolbar filterToolbar = new FilterToolbar(dataTable, filterForm);

		dataTable.addTopToolbar(filterToolbar);
		queue(dataTable);
	}

	private PropertyColumn<Customer, CustomerSort> idColumn() {
		return new PropertyColumn<>(Model.of("Id"), CustomerSort.ID,
				CustomerSort.ID.getFieldName());
	}
	
	private FilteredPropertyColumn<Customer, CustomerSort> usernameColumn() {
		return new FilteredPropertyColumn<>(new ResourceModel("username"), CustomerSort.USERNAME,
                CustomerSort.USERNAME.getFieldName()) {

            @Override
            public Component getFilter(String componentId, FilterForm<?> form) {
                return new AbstractTextFieldFilter<String>(componentId,
                        new PropertyModel<>(form.getModel(), "usernameLike"), form) {

                    @Override
                    public TextField<String> createTextFieldComponent(String componentId, IModel<String> model) {
                        return new UsernameSearchTextField(componentId, model);
                    }

                };
            }

        };
	}
	
	private FilteredPropertyColumn<Customer, CustomerSort> firstnameColumn() {
		return new FilteredPropertyColumn<>(new ResourceModel("firstname"),
                CustomerSort.FIRSTNAME, CustomerSort.FIRSTNAME.getFieldName()) {

            @Override
            public Component getFilter(String componentId, FilterForm<?> form) {
                return new AbstractTextFieldFilter<String>(componentId,
                        new PropertyModel<>(form.getModel(), "firstnameLike"), form) {

                    @Override
                    public TextField<String> createTextFieldComponent(String componentId, IModel<String> model) {
                        return new TextField<>(componentId, model);
                    }

                };
            }

        };
	}
	
	private FilteredPropertyColumn<Customer, CustomerSort> lastnameColumn() {
		return new FilteredPropertyColumn<>(new ResourceModel("lastname"), CustomerSort.LASTNAME,
                CustomerSort.LASTNAME.getFieldName()) {

            @Override
            public Component getFilter(String componentId, FilterForm<?> form) {
                return new AbstractTextFieldFilter<String>(componentId,
                        new PropertyModel<>(form.getModel(), "lastnameLike"), form) {

                    @Override
                    public TextField<String> createTextFieldComponent(String componentId, IModel<String> model) {
                        return new TextField<>(componentId, model);
                    }

                };
            }

        };
	}

	private FilteredPropertyColumn<Customer, CustomerSort> activeColumn() {
		return new FilteredPropertyColumn<>(new ResourceModel("active"), CustomerSort.ACTIVE,
                CustomerSort.ACTIVE.getFieldName()) {

            @Override
            public Component getFilter(String componentId, FilterForm<?> form) {
                return new AbstractCheckBoxFilter(componentId, new PropertyModel<>(form.getModel(), "active"), form);
            }

            @Override
            public void populateItem(Item<ICellPopulator<Customer>> item, String componentId,
                                     IModel<Customer> rowModel) {
                item.add(new YesNoLabel(componentId, (IModel<Boolean>) getDataModel(rowModel)));
            }

        };
	}
	
	private AbstractColumn<Customer, CustomerSort> actionColumn() {
		return new AbstractColumn<>(Model.of("Action")) {

            @Override
            public void populateItem(Item<ICellPopulator<Customer>> cellItem, String componentId,
                                     IModel<Customer> rowModel) {
                List<AbstrractActionItem> abstractItems = new ArrayList<>();
                var params = new PageParameters();
                params.add(CustomerEditPage.CUSTOMER_ID_PARAM, rowModel.getObject().getId());
                params.add(CustomerEditPage.PAGE_REFERENCE_ID, getPageId());

                abstractItems.add(editActionItem(params));
                abstractItems.add(deleteActionItem(rowModel));
                cellItem.add(new ActionPanel(componentId, abstractItems));
            }

            private static ActionItemLink editActionItem(PageParameters params) {
                return new ActionItemLink(
                        FontAwesome6IconType.pen_s,
                        new BookmarkablePageLink<Customer>("link", CustomerEditPage.class, params)
                );
            }

            private YesNoLink<Object> deleteActionItem(IModel<Customer> rowModel) {
                return new YesNoLink<>(FontAwesome6IconType.trash_s) {

                    @Override
                    protected void yesClicked(AjaxRequestTarget target) {
                        customerRepositoryService.delete(rowModel.getObject().getId());
                        webSocketMessageBroadcaster.sendToAll(new CustomerDeletedEvent(rowModel.getObject()));
                        target.add(dataTable);
                    }
                };
            }
        };
	}

}
