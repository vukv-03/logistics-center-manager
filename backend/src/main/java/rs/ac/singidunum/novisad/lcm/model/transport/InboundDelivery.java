package rs.ac.singidunum.novisad.lcm.model.transport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Status;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;

@Entity
public class InboundDelivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime createdAt;
	private LocalDate arrivalDate;
	private LocalTime dockingTime;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne()
	private Customer customerDelivery;
	
	@OneToMany(mappedBy = "inboundDelivery", cascade = CascadeType.ALL)
	private List<InboundDeliveryItem> items;
	
	@PrePersist
	private void onCreate() {
		createdAt = LocalDateTime.now();
		status = Status.PENDING;
	}

	public InboundDelivery() {
		super();
	}

	public InboundDelivery(LocalDate arrivalDate, LocalTime dockingTime,
			Customer customerDelivery, List<InboundDeliveryItem> items) {
		super();
		this.arrivalDate = arrivalDate;
		this.dockingTime = dockingTime;
		this.customerDelivery = customerDelivery;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalTime getDockingTime() {
		return dockingTime;
	}

	public void setDockingTime(LocalTime dockingTime) {
		this.dockingTime = dockingTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Customer getCustomerDelivery() {
		return customerDelivery;
	}

	public void setCustomerDelivery(Customer customerDelivery) {
		this.customerDelivery = customerDelivery;
	}

	public List<InboundDeliveryItem> getItems() {
		return items;
	}

	public void setItems(List<InboundDeliveryItem> items) {
		this.items = items;
	}
}
