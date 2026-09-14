package rs.ac.singidunum.novisad.lcm.model.transport;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import rs.ac.singidunum.novisad.lcm.model.Product;

@Entity
public class InboundDeliveryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long quantity;
	
	@ManyToOne()
	private Product product;
	
	@ManyToOne()
	private InboundDelivery inboundDelivery;
	
	public InboundDeliveryItem() {
		super();
	}

	public InboundDeliveryItem(Long id, Product product, Long quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public InboundDelivery getInboundDelivery() {
		return inboundDelivery;
	}

	public void setInboundDelivery(InboundDelivery inboundDelivery) {
		this.inboundDelivery = inboundDelivery;
	}
}
