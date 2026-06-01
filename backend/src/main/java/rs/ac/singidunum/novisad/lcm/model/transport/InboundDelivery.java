package rs.ac.singidunum.novisad.lcm.model.transport;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.TimeSlot;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Status;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;

@Entity
public class InboundDelivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne()
	private TimeSlot timeSlot;
	
	@ManyToOne()
	private Customer customer;
	
	@OneToMany(mappedBy = "inboundDelivery")
	private List<InboundDeliveryItem> items;

	public InboundDelivery() {
		super();
	}

	public InboundDelivery(Long id, Status status, TimeSlot timeSlot, Customer customer,
			List<InboundDeliveryItem> items) {
		super();
		this.id = id;
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

	public List<InboundDeliveryItem> getItems() {
		return items;
	}

	public void setItems(List<InboundDeliveryItem> items) {
		this.items = items;
	}
}
