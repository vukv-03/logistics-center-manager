package rs.ac.singidunum.novisad.lcm.model.users;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.Role;
import rs.ac.singidunum.novisad.lcm.model.transport.InboundDelivery;
import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrder;

@Entity
public class Customer extends User {
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "productOwner")
	private List<Product> ownedProducts;
	
	@OneToMany(mappedBy = "customerDelivery")
	private List<InboundDelivery> deliveries;
	
	@OneToMany(mappedBy = "customerOrder")
	private List<OutboundOrder> orders;
	
	public Customer() {
	    super();
	}

	public Customer(Long id, String email, Role role, String name, List<Product> ownedProducts, List<InboundDelivery> deliveries,
			List<OutboundOrder> orders) {
		super(id, email, role);
		this.name = name;
		this.ownedProducts = ownedProducts;
		this.deliveries = deliveries;
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
}

