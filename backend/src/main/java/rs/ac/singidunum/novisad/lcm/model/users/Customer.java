package rs.ac.singidunum.novisad.lcm.model.users;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Role;
import rs.ac.singidunum.novisad.lcm.model.transport.InboundDelivery;
import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrder;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "productOwner")
	private List<Product> ownedProducts;
	
	@OneToMany(mappedBy = "customer")
	private List<InboundDelivery> deliveries;
	
	@OneToMany(mappedBy = "customer")
	private List<OutboundOrder> orders;

	public Customer() {
		super();
	}

	public Customer(Long id, String email, Role role) {
		super(id, email, role);
	}

	public Customer(Long id, String email, Role role, Long id2, List<InboundDelivery> deliveries,
			List<OutboundOrder> orders, String name, List<Product> ownedProducts) {
		super(id, email, role);
		id = id2;
		this.deliveries = deliveries;
		this.orders = orders;
		this.name = name;
		this.ownedProducts = ownedProducts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<InboundDelivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<InboundDelivery> deliveries) {
		this.deliveries = deliveries;
	}

	public List<OutboundOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<OutboundOrder> orders) {
		this.orders = orders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getOwnedProducts() {
		return ownedProducts;
	}

	public void setOwnedProducts(List<Product> ownedProducts) {
		this.ownedProducts = ownedProducts;
	}
}

