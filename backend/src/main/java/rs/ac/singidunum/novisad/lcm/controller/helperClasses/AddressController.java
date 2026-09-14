package rs.ac.singidunum.novisad.lcm.controller.helperClasses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.novisad.lcm.dto.helperClasses.AddressDTO;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.Address;
import rs.ac.singidunum.novisad.lcm.service.helperClasses.AddressService;

@RestController
@RequestMapping(path = "/api/addresses")
public class AddressController {
	@Autowired
	private AddressService service;
	
	@GetMapping(path = "")
	public List<AddressDTO> getAll() {
		ArrayList<AddressDTO> addressDTO = new ArrayList<AddressDTO>();
		
		for (Address a : this.service.findAll()) {
			addressDTO.add(this.service.toDTO(a));
		}
		return addressDTO;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<AddressDTO> getById(@PathVariable Long id) {
		Address address = this.service.findById(id);
		AddressDTO addressDTO = this.service.toDTO(address);
		return new ResponseEntity<AddressDTO>(addressDTO, HttpStatus.OK);
	}
}
