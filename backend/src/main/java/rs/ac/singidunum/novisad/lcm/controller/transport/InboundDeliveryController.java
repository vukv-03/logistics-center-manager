package rs.ac.singidunum.novisad.lcm.controller.transport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.novisad.lcm.dto.transport.InboundDeliveryDTO;
import rs.ac.singidunum.novisad.lcm.model.transport.InboundDelivery;
import rs.ac.singidunum.novisad.lcm.service.transport.InboundDeliveryService;

@RestController
@RequestMapping(path = "/api/inbound_deliveries")
public class InboundDeliveryController {
	@Autowired
	private InboundDeliveryService service;
	
	@GetMapping(path = "")
	public List<InboundDeliveryDTO> getAll() {
		ArrayList<InboundDeliveryDTO> inboundDeliveryDTO = new ArrayList<InboundDeliveryDTO>();
		
		for (InboundDelivery id : this.service.findAll()) {
			inboundDeliveryDTO.add(this.service.toDTO(id));
		}
		return inboundDeliveryDTO;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<InboundDeliveryDTO> getById(@PathVariable Long id) {
		InboundDelivery inboundDelivery = this.service.findById(id);
		InboundDeliveryDTO inboundDeliveryDTO = this.service.toDTO(inboundDelivery);
		return new ResponseEntity<InboundDeliveryDTO>(inboundDeliveryDTO, HttpStatus.OK);
	}
	
	@PostMapping(path = "")
	public ResponseEntity<InboundDeliveryDTO> create(@RequestBody InboundDeliveryDTO newInboundDeliveryDTO) {
		InboundDeliveryDTO newInboundDelivery = this.service.createInboundDelivery(newInboundDeliveryDTO);
		
		return new ResponseEntity<InboundDeliveryDTO>(newInboundDelivery, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<InboundDeliveryDTO> update(@PathVariable Long id, @RequestBody InboundDeliveryDTO newInboundDeliveryDTO) {
		InboundDeliveryDTO result = this.service.changeInboundDelivery(id, newInboundDeliveryDTO);
		
		return new ResponseEntity<InboundDeliveryDTO>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<InboundDeliveryDTO> deleteById(@PathVariable Long id) {
		this.service.deleteById(id);
		
		return new ResponseEntity<InboundDeliveryDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<InboundDeliveryDTO>> searchInboundDeliveries(@RequestParam String customer) {			
		List<InboundDeliveryDTO> newInboundDeliveryDTO = this.service.searchInboundDeliveries(customer);
		
		return new ResponseEntity<List<InboundDeliveryDTO>>(newInboundDeliveryDTO, HttpStatus.OK);
	}
	
	@GetMapping("/history/search")
	public ResponseEntity<List<InboundDeliveryDTO>> searchInboundDeliveriesHistory(@RequestParam String customer) {			
		List<InboundDeliveryDTO> newInboundDeliveryDTO = this.service.searchInboundDeliveriesHistory(customer);
		
		return new ResponseEntity<List<InboundDeliveryDTO>>(newInboundDeliveryDTO, HttpStatus.OK);
	}
	
	@GetMapping("/history")
	public  ResponseEntity<List<InboundDeliveryDTO>> showCompleted() {
		List<InboundDeliveryDTO> newInboundDeliveryDTO = this.service.showCompleted();
		
		return new ResponseEntity<List<InboundDeliveryDTO>>(newInboundDeliveryDTO, HttpStatus.OK);
	}
}
