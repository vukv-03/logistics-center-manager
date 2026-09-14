package rs.ac.singidunum.novisad.lcm.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.users.CustomerDTO;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;
import rs.ac.singidunum.novisad.lcm.repository.users.CustomerRepository;

@Service
public class CustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	
	public Iterable<Customer> findAll() {
		return this.customerRepository.findAll();
	}
	
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
	}
	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void delete(Customer customer) {
		this.customerRepository.delete(customer);
	}
	
	public void deleteById(Long id) {
		this.customerRepository.deleteById(id);
	}
	
///////////////////////////////////////////////////////////////////////////////	

	public CustomerDTO toDTO(Customer customer) {
		CustomerDTO newCustomer = new CustomerDTO(customer.getId(), customer.getName());
		return newCustomer;
	}
	
///////////////////////////////////////////////////////////////////////////////	
}
