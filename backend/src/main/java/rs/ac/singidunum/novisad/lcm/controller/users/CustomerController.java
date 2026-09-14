package rs.ac.singidunum.novisad.lcm.controller.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.novisad.lcm.dto.users.CustomerDTO;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;
import rs.ac.singidunum.novisad.lcm.service.users.CustomerService;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@GetMapping(path = "")
	public List<CustomerDTO> getAll() {
		ArrayList<CustomerDTO> customerDTO = new ArrayList<CustomerDTO>();
		
		for (Customer c : this.service.findAll()) {
			customerDTO.add(this.service.toDTO(c));
		}
		return customerDTO;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
		Customer customer = this.service.findById(id);
		CustomerDTO customerDTO = this.service.toDTO(customer);
		return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
	}
}
