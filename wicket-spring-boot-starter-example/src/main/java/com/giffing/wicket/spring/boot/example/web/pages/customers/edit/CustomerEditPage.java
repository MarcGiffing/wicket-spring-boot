package com.giffing.wicket.spring.boot.example.web.pages.customers.edit;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;
import org.wicketstuff.annotation.mount.MountPath;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.web.pages.customers.CustomerListPage;
import com.giffing.wicket.spring.boot.example.web.pages.customers.create.CustomerCreatePage;

@MountPath("customers/edit/${" + CustomerEditPage.CUSTOMER_ID_PARAM + "}")
public class CustomerEditPage extends CustomerCreatePage {
	
	public static final String CUSTOMER_ID_PARAM = "id";
	
	@SpringBean
	private CustomerRepositoryService service;
	
	public CustomerEditPage(PageParameters params, Integer pageId){
		this(params);
		setPageReferenceId(pageId);
	}
	
	public CustomerEditPage(PageParameters params){
		super();
		StringValue stringValue = params.get(CUSTOMER_ID_PARAM);
		if(stringValue.isEmpty() || !StringUtils.isNumeric(stringValue.toString())){
			getSession().error(MessageFormat.format(getString("param.customer.id.missing"), stringValue));
//			"Missing customer id " + stringValue
			setResponsePage(CustomerListPage.class);
		}
		Long customerId = stringValue.toLong();
		Customer customer = service.findById(customerId);
		if(customer == null){
			getSession().error(MessageFormat.format(getString("customer.not-found"), customerId.toString()));
			setResponsePage(CustomerListPage.class);
		}
		
		getCustomerModel().setObject(customer);
	}

	@Override
	public boolean isCreatePage() {
		return false;
	}
	
}
