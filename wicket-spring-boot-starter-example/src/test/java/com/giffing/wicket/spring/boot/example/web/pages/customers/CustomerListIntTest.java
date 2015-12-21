package com.giffing.wicket.spring.boot.example.web.pages.customers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.markup.repeater.Item;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.giffing.wicket.spring.boot.example.model.Customer;
import com.giffing.wicket.spring.boot.example.repository.services.customer.CustomerRepositoryService;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerFilter;
import com.giffing.wicket.spring.boot.example.repository.services.customer.filter.CustomerSort;
import com.giffing.wicket.spring.boot.example.web.WicketBaseIntTest;
import com.giffing.wicket.spring.boot.example.web.html.modal.YesNoModal;

@Transactional
@Rollback
public class CustomerListIntTest extends WicketBaseIntTest {
	
	@Autowired
	private CustomerRepositoryService service;
	
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void assert_start_customer_list_page(){
		getTester().startPage(CustomerListPage.class);
		getTester().assertRenderedPage(CustomerListPage.class);
		System.out.println(service.findAll(0L, 10L, new CustomerFilter()).size());
		getTester().assertComponent("filterForm:table", DataTable.class);
		
		DataTable<Customer, CustomerSort> dataTable = (DataTable) getTester().getComponentFromLastRenderedPage("filterForm:table");
		assertThat(dataTable.getItemCount(), equalTo(5L));
		//id, username, firstname, lastname, active, actions
		assertThat(dataTable.getColumns().size(), equalTo(6));
		//get third row
		Item<Customer> item3 = (Item) getTester().getComponentFromLastRenderedPage("filterForm:table:body:rows:3");
		assertThat(item3.getModelObject().getId(), equalTo(3L));
		assertThat(item3.getModelObject().getUsername(), equalTo("adalgrim"));
		
		Item<Customer> item5 = (Item) getTester().getComponentFromLastRenderedPage("filterForm:table:body:rows:5");
		assertThat(item5.getModelObject().getId(), equalTo(5L));
		assertThat(item5.getModelObject().getUsername(), equalTo("tuk"));
	}
	
	@Test
	public void assert_delete_customer_method_called_once(){
		getTester().startPage(CustomerListPage.class);
		getTester().assertRenderedPage(CustomerListPage.class);
		
		DataTable<Customer, CustomerSort> dataTable = (DataTable) getTester().getComponentFromLastRenderedPage("filterForm:table");
		assertThat(dataTable.getItemCount(), equalTo(5L));
		
		getTester().clickLink(getTableCell(5, 6) + "items:2:item:link");
		getTester().assertComponent("defaultModal", YesNoModal.class);
		getTester().clickLink("defaultModal:content:yes", true);
		
		assertThat(dataTable.getItemCount(), equalTo(4L));
		
	}
	
	private String getTableCell(int row, int cell){
		return "filterForm:table:body:rows:" + row + ":cells:" + cell + ":cell:";
	}
}
