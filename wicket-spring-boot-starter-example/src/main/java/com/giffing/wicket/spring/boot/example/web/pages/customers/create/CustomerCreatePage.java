package com.giffing.wicket.spring.boot.example.web.pages.customers.create;

import org.apache.wicket.Component;
import org.apache.wicket.PageReference;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.html.border.LabledFormBroder;
import com.giffing.wicket.spring.boot.example.web.html.form.ValidationForm;
import com.giffing.wicket.spring.boot.example.web.pages.BasePage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.events.CustomerChangedEvent;
import com.giffing.wicket.spring.boot.example.web.pages.customers.model.UsernameTextField;
import com.giffing.wicket.spring.boot.starter.web.servlet.websocket.WebSocketMessageBroadcaster;

@MountPath("customers/create")
public class CustomerCreatePage extends BasePage{
	
	@SpringBean
	private CustomerRepositoryService service;
	
	@SpringBean
	private WebSocketMessageBroadcaster webSocketMessageBroadcaster;
	
	CompoundPropertyModel<Customer> customerModel;
	
	private Integer pageReferenceId;
	
	public CustomerCreatePage(Integer pageId){
		this.pageReferenceId = pageId;
	}
	
	public CustomerCreatePage(){
		customerModel = new CompoundPropertyModel<>(new Customer());
		queue(new ValidationForm<>("form", customerModel));
		queue(usernameField());
		queue(firstnameField());
		queue(lastnameField());
		queue(passwordField());
		queue(activeField());
		queue(submitButton());
		queue(cancelButton());
	}

	private Component activeField() {
		return new LabledFormBroder<Boolean>(getString("active"), new CheckBox("active"));
	}

	private LabledFormBroder<String> usernameField() {
		return new LabledFormBroder<String>(getString("username"), new UsernameTextField("username")){

			@Override
			public boolean isEnabled() {
				return isCreatePage();
			}
			
		};
	}

	private LabledFormBroder<Object> firstnameField() {
		return new LabledFormBroder<>(getString("firstname"), new RequiredTextField<>("firstname"));
	}

	private LabledFormBroder<Object> lastnameField() {
		return new LabledFormBroder<>(getString("lastname"), new RequiredTextField<>("lastname"));
	}
	
	private LabledFormBroder<String> passwordField() {
		PasswordTextField passwordTextField = new PasswordTextField("password");
		//TODO its not recommended to disable the password reset. But without my tests are failing cause the password is not submitted. https://issues.apache.org/jira/browse/WICKET-6221 
		passwordTextField.setResetPassword(false);
		LabledFormBroder<String> labledPasswordTextField = new LabledFormBroder<String>(getString("password"), passwordTextField){

			@Override
			public boolean isVisible() {
				return isCreatePage();
			}
			
		};
		return labledPasswordTextField;
	}

	public boolean isCreatePage(){
		return true;
	}
	
	private Component submitButton() {
		return new Button("submit"){
			@Override
			public void onSubmit() {
				service.save(customerModel.getObject());
				webSocketMessageBroadcaster.send(new CustomerChangedEvent(customerModel.getObject()));
				if(pageReferenceId != null){
					setResponsePage(new PageReference(pageReferenceId).getPage());
				} else {
					setResponsePage(CustomerListPage.class);
				}
			}
		};
	}
	
	private Component cancelButton() {
		Button cancelButton = new Button("cancel"){

			@Override
			public void onSubmit() {
				if(pageReferenceId != null){
					setResponsePage(new PageReference(pageReferenceId).getPage());
				} else {
					setResponsePage(CustomerListPage.class);
				}
			}
			
		};
		cancelButton.setDefaultFormProcessing(false);
		return cancelButton;
	}

	public CompoundPropertyModel<Customer> getCustomerModel() {
		return customerModel;
	}

	public Integer getPageReferenceId() {
		return pageReferenceId;
	}

	public void setPageReferenceId(Integer pageReferenceId) {
		this.pageReferenceId = pageReferenceId;
	}
	
}
