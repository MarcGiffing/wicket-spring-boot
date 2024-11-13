package com.giffing.wicket.spring.boot.example.web.pages.customers.create;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.html.form.ValidationForm;
import com.giffing.wicket.spring.boot.example.web.pages.BaseAuthenticatedPage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.events.CustomerChangedEvent;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.UsernameTextField;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;
import lombok.Getter;
import lombok.Setter;
import org.apache.wicket.Component;
import org.apache.wicket.PageReference;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("customers/create")
@AuthorizeInstantiation("USER")
public class CustomerCreatePage extends BaseAuthenticatedPage {

    @SpringBean
    private CustomerRepositoryService service;

    @SpringBean
    private WebSocketMessageBroadcaster webSocketMessageBroadcaster;

    @Getter
    CompoundPropertyModel<Customer> customerModel;

    @Setter
    private Integer pageReferenceId;

    public CustomerCreatePage(Integer pageId) {
        super(new PageParameters());
        this.pageReferenceId = pageId;
    }

    public CustomerCreatePage() {
        super(new PageParameters());
        customerModel = new CompoundPropertyModel<>(new Customer());
        queue(new ValidationForm<>("form", customerModel));
        queueFormComponent(usernameField());
        queueFormComponent(new RequiredTextField<>("firstname"));
        queueFormComponent(new RequiredTextField<>("lastname"));
        queueFormComponent(new CheckBox("active"));
        queue(submitButton());
        queue(cancelButton());
    }


    private FormComponent<String> usernameField() {
        return new UsernameTextField("username") {

            @Override
            public boolean isEnabled() {
                return isCreatePage();
            }
        };
    }

    public boolean isCreatePage() {
        return true;
    }

    private Component submitButton() {
        return new Button("submit") {
            @Override
            public void onSubmit() {
                var customer = customerModel.getObject();
                service.save(customer);
                webSocketMessageBroadcaster.sendToAll(new CustomerChangedEvent(customer));
                if (pageReferenceId != null) {
                    setResponsePage(new PageReference(pageReferenceId).getPage());
                } else {
                    setResponsePage(CustomerListPage.class);
                }
            }
        };
    }

    private Component cancelButton() {
        Button cancelButton = new Button("cancel") {

            @Override
            public void onSubmit() {
                if (pageReferenceId != null) {
                    setResponsePage(new PageReference(pageReferenceId).getPage());
                } else {
                    setResponsePage(CustomerListPage.class);
                }
            }

        };
        cancelButton.setDefaultFormProcessing(false);
        return cancelButton;
    }

}
