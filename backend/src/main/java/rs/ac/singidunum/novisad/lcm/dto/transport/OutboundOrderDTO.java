package rs.ac.singidunum.novisad.lcm.dto.transport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import rs.ac.singidunum.novisad.lcm.dto.helperClasses.AddressDTO;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Status;

public class OutboundOrderDTO {
	private Long id;
	private LocalDateTime createdAt;
	private LocalDate shippingDate;
	private LocalTime shippingTime;
	private Status status;
	private AddressDTO deliveryAddress;
	private Long customerId;
	private String customer;
	private List<OutboundOrderItemDTO> items;
	
	public OutboundOrderDTO() {
		super();
	}

	public OutboundOrderDTO(Long id, LocalDateTime createdAt, LocalDate shippingDate,
			LocalTime shippingTime, Status status, AddressDTO deliveryAddress, Long customerId,
			String customer, List<OutboundOrderItemDTO> items) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.shippingDate = shippingDate;
		this.shippingTime = shippingTime;
		this.status = status;
		this.deliveryAddress = deliveryAddress;
		this.customerId = customerId;
		this.customer = customer;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public LocalTime getShippingTime() {
		return shippingTime;
	}

	public Status getStatus() {
		return status;
	}

	public AddressDTO getDeliveryAddress() {
		return deliveryAddress;
	}

	public Long getCustomerId() {
		return customerId;
	}
	
	public String getCustomer() {
		return customer;
	}

	public List<OutboundOrderItemDTO> getItems() {
		return items;
	}
}
