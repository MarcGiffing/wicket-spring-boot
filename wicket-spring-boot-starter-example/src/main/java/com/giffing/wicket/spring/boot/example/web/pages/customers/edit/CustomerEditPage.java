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
	public static final String PAGE_REFERENCE_ID = "referenceId";
	
	@SpringBean
	private CustomerRepositoryService service;
	
	public CustomerEditPage(PageParameters params){
		super();
		StringValue customerIdParam = params.get(CUSTOMER_ID_PARAM);
		if(customerIdParam.isEmpty() || !StringUtils.isNumeric(customerIdParam.toString())){
			getSession().error(MessageFormat.format(getString("param.customer.id.missing"), customerIdParam));
//			"Missing customer id " + stringValue
			setResponsePage(CustomerListPage.class);
		}
		Long customerId = customerIdParam.toLong();
		Customer customer = service.findById(customerId);
		if(customer == null){
			getSession().error(MessageFormat.format(getString("customer.not-found"), customerId.toString()));
			setResponsePage(CustomerListPage.class);
		}
		
		StringValue pageReferfenceIdParam = params.get(PAGE_REFERENCE_ID);
		if(!pageReferfenceIdParam.isEmpty() || StringUtils.isNumeric(pageReferfenceIdParam.toString())){
			setPageReferenceId(pageReferfenceIdParam.toInteger());
		}
		
		getCustomerModel().setObject(customer);
	}

	@Override
	public boolean isCreatePage() {
		return false;
	}
	
}
