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
import rs.ac.singidunum.novisad.lcm.model.helperClasses.Address;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Status;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;

@Entity
public class OutboundOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime createdAt;
	private LocalDate shippingDate;
	private LocalTime shippingTime;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne()
	private Address deliveryAddress;
	
	@ManyToOne()
	private Customer customerOrder;
	
	@OneToMany(mappedBy = "outboundOrder", cascade = CascadeType.ALL)
	private List<OutboundOrderItem> items;
	
	@PrePersist
	private void onCreate() {
		createdAt = LocalDateTime.now();
		status = Status.PENDING;
	}

	public OutboundOrder() {
		super();
	}

	public OutboundOrder(LocalDate shippingDate, LocalTime shippingTime, Address deliveryAddress,
			Customer customerOrder, List<OutboundOrderItem> items) {
		super();
		this.shippingDate = shippingDate;
		this.shippingTime = shippingTime;
		this.deliveryAddress = deliveryAddress;
		this.customerOrder = customerOrder;
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

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	public LocalTime getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(LocalTime shippingTime) {
		this.shippingTime = shippingTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Customer getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(Customer customerOrder) {
		this.customerOrder = customerOrder;
	}

	public List<OutboundOrderItem> getItems() {
		return items;
	}

	public void setItems(List<OutboundOrderItem> items) {
		this.items = items;
	}
}
