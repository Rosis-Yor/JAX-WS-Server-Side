package coolschool.ws.soap;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.apache.cxf.feature.Features;

import coolschool.stubs.customerlist.CreateCustomerRequest;
import coolschool.stubs.customerlist.CreateCustomerResponse;
import coolschool.stubs.customerlist.Customer;
import coolschool.stubs.customerlist.CustomerList;
import coolschool.stubs.customerlist.GetCustomerRequest;
import coolschool.stubs.customerlist.GetCustomerResponse;

@Features(features="org.apache.cxf.feature.LoggingFeature")
public class CustomerListWsImpl implements CustomerList {

	
	List<Customer> customers = new ArrayList<>(); 
	
	public void init() {
		
		Customer customer = new Customer();
		
		customer.setId(1);
		customer.setName("Perl");
		customer.setCompany("@Perl's Yard");
		customer.setEmail("perl@perl.me");
		customer.setLocation("Perl's Place");
		customers.add(customer) ;
		
	}
	
	@Override
	public CreateCustomerResponse createCustomer(CreateCustomerRequest request) {

		Customer customer = new Customer();
		customer.setId(request.getCustomerID());
		customer.setName(request.getName());
		customer.setCompany(request.getCompany());
		customer.setEmail(request.getEmail());
		customer.setLocation(request.getLocation());
		customers.add(customer);

		CreateCustomerResponse response = new CreateCustomerResponse();
		if(!response.isConfirm()) {
			response.setConfirm(true);}
		return response;
	}

	@Override
	public GetCustomerResponse getCustomer(GetCustomerRequest request) {
		init();
		int custId = request.getCustomerID();
		
		List<Customer> selection = new ArrayList<>();
		
		Iterator iter = customers.iterator();
		while(iter.hasNext()) {
			
			
			Customer cust = (Customer) iter.next();
			if (cust.getId() == custId) {
				
				selection.add(cust);
			}		
			
		}
		
		GetCustomerResponse response = new GetCustomerResponse();
		
		response.getCustomer().addAll(selection);
		
		return response;
	}

}
