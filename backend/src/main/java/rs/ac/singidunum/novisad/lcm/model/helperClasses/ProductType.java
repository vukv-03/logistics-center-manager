package rs.ac.singidunum.novisad.lcm.model.helperClasses;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import rs.ac.singidunum.novisad.lcm.model.Product;

@Entity
public class ProductType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String type;
	
	@OneToMany(mappedBy = "productType")
	private List<Product> products;

	public ProductType() {
		super();
	}

	public ProductType(Long id, String type, List<Product> products) {
		super();
		this.id = id;
		this.type = type;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
