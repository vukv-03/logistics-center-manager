package rs.ac.singidunum.novisad.lcm.service.transport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.transport.OutboundOrderDTO;
import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.Address;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Status;
import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrder;
import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrderItem;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;
import rs.ac.singidunum.novisad.lcm.repository.transport.OutboundOrderRepository;
import rs.ac.singidunum.novisad.lcm.service.ProductService;
import rs.ac.singidunum.novisad.lcm.service.helperClasses.AddressService;
import rs.ac.singidunum.novisad.lcm.service.users.CustomerService;

@Service
public class OutboundOrderService {
	@Autowired
	private OutboundOrderRepository repo;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	@Autowired 
	private AddressService addressService;
	@Autowired
	private OutboundOrderItemService outboundOrderItemService;
	
	
	public List<OutboundOrder> findAll() {
		return this.repo.findAll();
	}
	
	public OutboundOrder findById(Long id) {
		return this.repo.findById(id).orElseThrow(() -> new RuntimeException("Outbound order not found."));
	}
	
	public OutboundOrder save(OutboundOrder order) {
		return this.repo.save(order);
	}
	
	public void deleteById(Long id) {
		OutboundOrder order = findById(id);
		if (canCancel(order.getCreatedAt()) && order.getStatus() != Status.COMPLETED) {
			this.repo.deleteById(id);
		} else {
			throw new RuntimeException("Time window for cancellation has expired / order is already completed!");
		}
	}
	
///////////////////////////////////////////////////////////////////////////////	
	
	public OutboundOrderDTO toDTO(OutboundOrder order) {
		OutboundOrderDTO newOrderDTO = new OutboundOrderDTO(order.getId(), order.getCreatedAt(), order.getShippingDate(), order.getShippingTime(), order.getStatus(), addressService.toDTO(order.getDeliveryAddress()), order.getCustomerOrder().getId(), order.getCustomerOrder().getName(), outboundOrderItemService.listToDTO(order.getItems()));
		return newOrderDTO;
	}
	
	public OutboundOrder toOrder(OutboundOrderDTO orderDTO, List<OutboundOrderItem> items) {
		OutboundOrder newOrder = new OutboundOrder(orderDTO.getShippingDate(), orderDTO.getShippingTime(), findAddress(orderDTO.getDeliveryAddress().getId()), findCustomer(orderDTO.getCustomerId()), items);
		for (OutboundOrderItem item : items) {
			item.setOutboundOrder(newOrder);
		}
		
		return newOrder;
	}
	
///////////////////////////////////////////////////////////////////////////////
	
	public OutboundOrderDTO createOutboundOrder(OutboundOrderDTO orderDTO) {
		isTimeAvailable(orderDTO.getShippingDate(), orderDTO.getShippingTime());
		List<OutboundOrderItem> items = outboundOrderItemService.listToOrder(orderDTO.getItems());
		isProductUnique(items);
		areItemsAvailable(items);
		OutboundOrder newOrder = toOrder(orderDTO, items);
		OutboundOrder no = this.repo.save(newOrder);
		OutboundOrderDTO newOrderDTO = toDTO(no);
		
		return newOrderDTO;
	}
	
	private boolean isValidTime(LocalTime shippingTime) {
		return (shippingTime.getMinute() == 0 || shippingTime.getMinute() == 30)
	            && shippingTime.getSecond() == 0
	            && shippingTime.getNano() == 0;
	}
	
	private boolean isTimeAvailable(LocalDate shippingDate, LocalTime shippingTime) {
		boolean valid = isValidTime(shippingTime);
		
		if (!valid) {
			throw new RuntimeException("Time must be scheduled in 30min segments (eg. 11.00 or 11.30)!");
		}
		
		boolean exists = repo.existsByShippingDateAndShippingTime(shippingDate, shippingTime);
		if (exists) {
			throw new RuntimeException("Chosen time is not available!");
		}
		
		LocalDateTime newShippingDateTime = LocalDateTime.of(shippingDate, shippingTime);
		if (newShippingDateTime.isBefore(LocalDateTime.now())) {
		    throw new RuntimeException("Shipping date and time cannot be in the past.");
		}
		
		return true;
	}
	
	public Customer findCustomer(Long customerId) {
		Customer customer = customerService.findById(customerId);
		return customer;
	}
	
	private boolean areItemsAvailable(List<OutboundOrderItem> items) {
		for (OutboundOrderItem item : items) {
			Long quantity = item.getQuantity();
			Product product = item.getProduct();
			Long currentQuantity = product.getQuantityInStock();
			
			if (quantity > currentQuantity) {
				throw new RuntimeException(String.format("Quantity too high! Product: %s, Quantity in stock: %d", product.getName(), currentQuantity));
			}
		}
		return true;
	}
	
	private Address findAddress(Long addressId) {
		Address address = addressService.findById(addressId);
		return address;
	}
	
	private void isProductUnique(List<OutboundOrderItem> items) {
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

	public OutboundOrderDTO changeOutboundOrder(Long id, OutboundOrderDTO newOutboundOrderDTO) {
		OutboundOrder order = findById(id);
		List<OutboundOrderItem> items = outboundOrderItemService.listToOrder(newOutboundOrderDTO.getItems());
		isProductUnique(items);
		
		if (order.getStatus() == Status.PROCESSING && newOutboundOrderDTO.getStatus() == Status.COMPLETED) {
			areItemsAvailable(items);
			productService.decreaseQuantity(order.getItems());
			order.setStatus(Status.COMPLETED);
			
		} else if (order.getStatus() == Status.PENDING && newOutboundOrderDTO.getStatus() == Status.PROCESSING) {
			order.setStatus(newOutboundOrderDTO.getStatus());
			
		} else if (order.getStatus() == Status.PENDING && newOutboundOrderDTO.getStatus() == Status.PENDING) {
			LocalDateTime newShippingDateTime = LocalDateTime.of(newOutboundOrderDTO.getShippingDate(), newOutboundOrderDTO.getShippingTime());
			if (newShippingDateTime.isBefore(LocalDateTime.now())) {
			    throw new RuntimeException("Shipping date and time cannot be in the past.");
			}
			if (!order.getShippingDate().equals(newOutboundOrderDTO.getShippingDate()) || !order.getShippingTime().equals(newOutboundOrderDTO.getShippingTime())) {
				isTimeAvailable(newOutboundOrderDTO.getShippingDate(), newOutboundOrderDTO.getShippingTime());
			}
			order.setShippingDate(newOutboundOrderDTO.getShippingDate());
			order.setShippingTime(newOutboundOrderDTO.getShippingTime());
			areItemsAvailable(items);
			order.setItems(items);
			order.setDeliveryAddress(findAddress(newOutboundOrderDTO.getDeliveryAddress().getId()));
			order.setCustomerOrder(findCustomer(newOutboundOrderDTO.getCustomerId()));
			
		} else {
			throw new RuntimeException("Prohibited status change!");
		}
		
		OutboundOrder newOrder = save(order);
		OutboundOrderDTO result = toDTO(newOrder);
		return result;
	}
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public List<OutboundOrderDTO> searchOutboundOrders(String customer) {
	    List<OutboundOrderDTO> result = new ArrayList<>();

	    for (OutboundOrder order : repo.findByCustomerOrderNameContainingIgnoreCase(customer)) {
	    	if (order.getStatus() != Status.COMPLETED) {
	    		result.add(toDTO(order));
	    	}
	    }
	    return result;
	}
	
	public List<OutboundOrderDTO> searchOutboundOrdersHistory(String customer) {
	    List<OutboundOrderDTO> result = new ArrayList<>();

	    for (OutboundOrder order : repo.findByCustomerOrderNameContainingIgnoreCase(customer)) {
	    	if (order.getStatus() == Status.COMPLETED) {
	    		result.add(toDTO(order));
	    	}
	    }
	    return result;
	}
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public List<OutboundOrderDTO> showCompleted() {
		ArrayList<OutboundOrderDTO> outboundDeliveryDTO = new ArrayList<OutboundOrderDTO>();
		
		for (OutboundOrder oo : findAll()) {
			if (oo.getStatus() == Status.COMPLETED) {
				outboundDeliveryDTO.add(toDTO(oo));
			}
		}
		return outboundDeliveryDTO;
	}
}
