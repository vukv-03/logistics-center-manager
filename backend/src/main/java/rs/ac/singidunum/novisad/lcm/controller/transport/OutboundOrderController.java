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

import rs.ac.singidunum.novisad.lcm.dto.transport.OutboundOrderDTO;
import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrder;
import rs.ac.singidunum.novisad.lcm.service.transport.OutboundOrderService;

@RestController
@RequestMapping(path = "/api/outbound_orders")
public class OutboundOrderController {
	@Autowired
	private OutboundOrderService service;
	
	@GetMapping(path = "")
	public List<OutboundOrderDTO> getAll() {
		ArrayList<OutboundOrderDTO> outboundOrderDTO = new ArrayList<OutboundOrderDTO>();
		
		for (OutboundOrder oo : this.service.findAll()) {
			outboundOrderDTO.add(this.service.toDTO(oo));
		}
		return outboundOrderDTO;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<OutboundOrderDTO> getById(@PathVariable Long id) {
		OutboundOrder outboundOrder = this.service.findById(id);
		OutboundOrderDTO outboundOrderDTO = this.service.toDTO(outboundOrder);
		return new ResponseEntity<OutboundOrderDTO>(outboundOrderDTO, HttpStatus.OK);
	}
	
	@PostMapping(path = "")
	public ResponseEntity<OutboundOrderDTO> create(@RequestBody OutboundOrderDTO newOutboundOrderDTO) {
		OutboundOrderDTO newOutboundOrder = this.service.createOutboundOrder(newOutboundOrderDTO);
		
		return new ResponseEntity<OutboundOrderDTO>(newOutboundOrder, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<OutboundOrderDTO> update(@PathVariable Long id, @RequestBody OutboundOrderDTO newOutboundOrderDTO) {
		OutboundOrderDTO result = this.service.changeOutboundOrder(id, newOutboundOrderDTO);
		
		return new ResponseEntity<OutboundOrderDTO>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<OutboundOrderDTO> deleteById(@PathVariable Long id) {
		this.service.deleteById(id);
		
		return new ResponseEntity<OutboundOrderDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<OutboundOrderDTO>> searchOutboundOrders(@RequestParam String customer) {			
		List<OutboundOrderDTO> newOutboundOrderDTO = this.service.searchOutboundOrders(customer);
		
		return new ResponseEntity<List<OutboundOrderDTO>>(newOutboundOrderDTO, HttpStatus.OK);
	}
	
	@GetMapping("/history/search")
	public ResponseEntity<List<OutboundOrderDTO>> searchOutboundOrdersHistory(@RequestParam String customer) {			
		List<OutboundOrderDTO> newOutboundOrderDTO = this.service.searchOutboundOrdersHistory(customer);
		
		return new ResponseEntity<List<OutboundOrderDTO>>(newOutboundOrderDTO, HttpStatus.OK);
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<OutboundOrderDTO>> showCompleted() {
		List<OutboundOrderDTO> newOutboundOrderDTO = this.service.showCompleted();
		
		return new ResponseEntity<List<OutboundOrderDTO>>(newOutboundOrderDTO, HttpStatus.OK);
	}
}
