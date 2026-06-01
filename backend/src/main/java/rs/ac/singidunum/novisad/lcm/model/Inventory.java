package rs.ac.singidunum.novisad.lcm.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long quantityInStock;
	private Long minimumStockLevel;
	
	@OneToMany(mappedBy = "inventory")
	private List<Product> productsInInventory;

	public Inventory() {
		super();
	}

	public Inventory(Long id, Long quantityInStock, Long minimumStockLevel, List<Product> productsInInventory) {
		super();
		this.id = id;
		this.quantityInStock = quantityInStock;
		this.minimumStockLevel = minimumStockLevel;
		this.productsInInventory = productsInInventory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Long quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public Long getMinimumStockLevel() {
		return minimumStockLevel;
	}

	public void setMinimumStockLevel(Long minimumStockLevel) {
		this.minimumStockLevel = minimumStockLevel;
	}

	public List<Product> getProductsInInventory() {
		return productsInInventory;
	}

	public void setProductsInInventory(List<Product> productsInInventory) {
		this.productsInInventory = productsInInventory;
	}
}
