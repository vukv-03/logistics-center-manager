package rs.ac.singidunum.novisad.lcm.model.transport;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.Address;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.TimeSlot;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Status;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;

@Entity
public class OutboundOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate orderDate;
	private LocalDate shippingDate;
	private LocalDate deliveryDate;
	private Status status;
	
	@ManyToOne()
	private Address deliveryAddress;
	
	@ManyToOne()
	private TimeSlot timeSlot;
	
	@ManyToOne()
	private Customer customer;
	
	@OneToMany(mappedBy = "outboundOrder")
	private List<OutboundOrderItem> items;

	public OutboundOrder() {
		super();
	}

	public OutboundOrder(Long id, Address deliveryAddress, LocalDate orderDate, LocalDate shippingDate,
			LocalDate deliveryDate, Status status, TimeSlot timeSlot, Customer customer,
			List<OutboundOrderItem> items) {
		super();
		this.id = id;
		this.deliveryAddress = deliveryAddress;
		this.orderDate = orderDate;
		this.shippingDate = shippingDate;
		this.deliveryDate = deliveryDate;
		this.status = status;
		this.timeSlot = timeSlot;
		this.customer = customer;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OutboundOrderItem> getItems() {
		return items;
	}

	public void setItems(List<OutboundOrderItem> items) {
		this.items = items;
	}
}
