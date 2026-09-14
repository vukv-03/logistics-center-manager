package rs.ac.singidunum.novisad.lcm.dto.transport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Status;

public class InboundDeliveryDTO {
	private Long id;
	private LocalDateTime createdAt;
	private LocalDate arrivalDate;
	private LocalTime dockingTime;
	private Status status;
	private Long customerId;
	private String customer;
	private List<InboundDeliveryItemDTO> items;
	
	public InboundDeliveryDTO() {
		super();
	}

	public InboundDeliveryDTO(Long id, LocalDateTime createdAt, LocalDate arrivalDate, LocalTime dockingTime,
			Status status, Long customerId, String customer, List<InboundDeliveryItemDTO> items) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.arrivalDate = arrivalDate;
		this.dockingTime = dockingTime;
		this.status = status;
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

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public LocalTime getDockingTime() {
		return dockingTime;
	}

	public Status getStatus() {
		return status;
	}

	public String getCustomer() {
		return customer;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public List<InboundDeliveryItemDTO> getItems() {
		return items;
	}
}
