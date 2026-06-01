package rs.ac.singidunum.novisad.lcm.service.users;

import rs.ac.singidunum.novisad.lcm.model.users.Customer;
import rs.ac.singidunum.novisad.lcm.repository.users.CustomerRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class CustomerService extends GenericService<Customer, CustomerRepository> {

	public CustomerService(CustomerRepository repository) {
		super(repository);
	}

}
