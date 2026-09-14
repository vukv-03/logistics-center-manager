package rs.ac.singidunum.novisad.lcm.service.transport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.transport.InboundDeliveryDTO;
import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Status;
import rs.ac.singidunum.novisad.lcm.model.transport.InboundDelivery;
import rs.ac.singidunum.novisad.lcm.model.transport.InboundDeliveryItem;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;
import rs.ac.singidunum.novisad.lcm.repository.transport.InboundDeliveryRepository;
import rs.ac.singidunum.novisad.lcm.service.ProductService;
import rs.ac.singidunum.novisad.lcm.service.users.CustomerService;

@Service
public class InboundDeliveryService {
	@Autowired
	private InboundDeliveryRepository repo;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private InboundDeliveryItemService inboundDeliveryItemService;
	
	
	public List<InboundDelivery> findAll() {
		return this.repo.findAll();
	}
	
	public InboundDelivery findById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new RuntimeException("Inbound delivery not found."));
	}
	
	public InboundDelivery save(InboundDelivery delivery) {
		return this.repo.save(delivery);
	}
	
	public void deleteById(Long id) {
		InboundDelivery delivery = findById(id);
		if (canCancel(delivery.getCreatedAt()) && delivery.getStatus() != Status.COMPLETED) {
			this.repo.deleteById(id);
		} else {
			throw new RuntimeException("Time window for cancellation has expired / delivery is already completed!");
		}
	}
	
///////////////////////////////////////////////////////////////////////////////	
	
	public InboundDeliveryDTO toDTO(InboundDelivery delivery) {
		InboundDeliveryDTO newDeliveryDTO = new InboundDeliveryDTO(delivery.getId(), delivery.getCreatedAt(), delivery.getArrivalDate(), delivery.getDockingTime(), delivery.getStatus(), delivery.getCustomerDelivery().getId(), delivery.getCustomerDelivery().getName(), inboundDeliveryItemService.listToDTO(delivery.getItems()));
		return newDeliveryDTO;
	}
	
	public InboundDelivery toDelivery(InboundDeliveryDTO deliveryDTO, List<InboundDeliveryItem> items) {
		InboundDelivery newDelivery = new InboundDelivery(deliveryDTO.getArrivalDate(), deliveryDTO.getDockingTime(), findCustomer(deliveryDTO.getCustomerId()), items);
		for (InboundDeliveryItem item : items) {
			item.setInboundDelivery(newDelivery);
		}
		
		return newDelivery;
	}
	
///////////////////////////////////////////////////////////////////////////////
	
	public InboundDeliveryDTO createInboundDelivery(InboundDeliveryDTO deliveryDTO) {
		isTimeAvailable(deliveryDTO.getArrivalDate(), deliveryDTO.getDockingTime());
		List<InboundDeliveryItem> items = inboundDeliveryItemService.listToDelivery(deliveryDTO.getItems());
		isProductUnique(items);
		areItemsAvailable(items);
		InboundDelivery newDelivery = toDelivery(deliveryDTO, items);
		InboundDelivery nd = this.repo.save(newDelivery);
		InboundDeliveryDTO newDeliveryDTO = toDTO(nd);
		
		return newDeliveryDTO;
	}
	
	private boolean isValidTime(LocalTime dockingTime) {
		return (dockingTime.getMinute() == 0 || dockingTime.getMinute() == 30)
	            && dockingTime.getSecond() == 0
	            && dockingTime.getNano() == 0;
	}
	
	private boolean isTimeAvailable(LocalDate arrivalDate, LocalTime dockingTime) {
		boolean valid = isValidTime(dockingTime);
		
		if (!valid) {
			throw new RuntimeException("Time must be scheduled in 30min segments (eg. 11.00 or 11.30)!");
		}
		
		boolean exists = repo.existsByArrivalDateAndDockingTime(arrivalDate, dockingTime);
		if (exists) {
			throw new RuntimeException("Chosen time is not available!");
		}
		
		LocalDateTime newArrivalDockingDateTime = LocalDateTime.of(arrivalDate, dockingTime);
		if (newArrivalDockingDateTime.isBefore(LocalDateTime.now())) {
		    throw new RuntimeException("Arrival/Docking date and time cannot be in the past.");
		}
		
		return true;
	}
	
	public Customer findCustomer(Long customerId) {
		Customer customer = customerService.findById(customerId);
		return customer;
	}
	
	private boolean areItemsAvailable(List<InboundDeliveryItem> items) {
		for (InboundDeliveryItem item : items) {
			Long quantity = item.getQuantity();
			Product product = item.getProduct();
			Long currentQuantity = product.getQuantityInStock();
			
			if (quantity < 1) {
				throw new RuntimeException(String.format("Quantity too low! Product: %s, Quantity in stock: %d", product.getName(), currentQuantity));
			}
		}
		return true;
	}
	
	private void isProductUnique(List<InboundDeliveryItem> items) {
	    for (int i = 0; i < items.size(); i++) {
	        for (int j = i + 1; j < items.size(); j++) {
	            if (items.get(i).getProduct().getId().equals(items.get(j).getProduct().getId())) {
	                throw new RuntimeException("Duplicate product in order.");
	            }
	        }
	    }
	}

	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	private boolean canCancel(LocalDateTime createdAt) {
		if (LocalDateTime.now().isAfter(createdAt.plusHours(1))) {
			return false;
		}
		return true;
	}
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public InboundDeliveryDTO changeInboundDelivery(Long id, InboundDeliveryDTO newInboundDeliveryDTO) {
		InboundDelivery delivery = findById(id);
		List<InboundDeliveryItem> items = inboundDeliveryItemService.listToDelivery(newInboundDeliveryDTO.getItems());
		isProductUnique(items);
		
		if (delivery.getStatus() == Status.PROCESSING && newInboundDeliveryDTO.getStatus() == Status.COMPLETED) {
			areItemsAvailable(items);
			productService.increaseQuantity(delivery.getItems());
			delivery.setStatus(Status.COMPLETED);
			
		} else if (delivery.getStatus() == Status.PENDING && newInboundDeliveryDTO.getStatus() == Status.PROCESSING) {
			delivery.setStatus(newInboundDeliveryDTO.getStatus());
			
		} else if (delivery.getStatus() == Status.PENDING && newInboundDeliveryDTO.getStatus() == Status.PENDING) {
			LocalDateTime newArrivalDockingDateTime = LocalDateTime.of(newInboundDeliveryDTO.getArrivalDate(), newInboundDeliveryDTO.getDockingTime());
			if (newArrivalDockingDateTime.isBefore(LocalDateTime.now())) {
			    throw new RuntimeException("Arrival/Docking date and time cannot be in the past.");
			}
			if (!delivery.getArrivalDate().equals(newInboundDeliveryDTO.getArrivalDate()) || !delivery.getDockingTime().equals(newInboundDeliveryDTO.getDockingTime())) {
				isTimeAvailable(newInboundDeliveryDTO.getArrivalDate(), newInboundDeliveryDTO.getDockingTime());
			}
			delivery.setArrivalDate(newInboundDeliveryDTO.getArrivalDate());
			delivery.setDockingTime(newInboundDeliveryDTO.getDockingTime());
			areItemsAvailable(items);
			delivery.setItems(items);
			delivery.setCustomerDelivery(findCustomer(newInboundDeliveryDTO.getCustomerId()));
			
		} else {
			throw new RuntimeException("Prohibited status change!");
		}
		
		InboundDelivery newDelivery = save(delivery);
		InboundDeliveryDTO result = toDTO(newDelivery);
		return result;
	}
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public List<InboundDeliveryDTO> searchInboundDeliveries(String customer) {
	    List<InboundDeliveryDTO> result = new ArrayList<>();

	    for (InboundDelivery delivery : repo.findByCustomerDeliveryNameContainingIgnoreCase(customer)) {
	    	if (delivery.getStatus() != Status.COMPLETED) {
	    		result.add(toDTO(delivery));
	    	}
	    }
	    return result;
	}
	
	public List<InboundDeliveryDTO> searchInboundDeliveriesHistory(String customer) {
	    List<InboundDeliveryDTO> result = new ArrayList<>();

	    for (InboundDelivery delivery : repo.findByCustomerDeliveryNameContainingIgnoreCase(customer)) {
	        if (delivery.getStatus() == Status.COMPLETED) {
	        	result.add(toDTO(delivery));
	        }
	    }
	    return result;
	}
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
		
	public List<InboundDeliveryDTO> showCompleted() {
		ArrayList<InboundDeliveryDTO> inboundDeliveryDTO = new ArrayList<InboundDeliveryDTO>();
		
		for (InboundDelivery id : findAll()) {
			if (id.getStatus() == Status.COMPLETED) {
				inboundDeliveryDTO.add(toDTO(id));
			}
		}
		return inboundDeliveryDTO;
	}
		
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
}
