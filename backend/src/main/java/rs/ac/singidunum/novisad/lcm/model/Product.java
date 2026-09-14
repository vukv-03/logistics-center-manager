package rs.ac.singidunum.novisad.lcm.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.ProductType;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.MeasurementUnit;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	private String description;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MeasurementUnit measurementUnit;
	private Long quantityInStock;
	
	@Column(nullable = false)
	private BigDecimal amountOfProduct;
	
	@ManyToOne()
	private Customer productOwner;
	
	@ManyToOne()
	private ProductType productType;
	
	@PrePersist
	private void onCreate() {
		quantityInStock = 0L;
	}

	public Product() {
		super();
	}

	public Product(String name, String description, MeasurementUnit measurementUnit, Long quantityInStock, BigDecimal amountOfProduct,
			Customer productOwner, ProductType productType) {
		super();
		this.name = name;
		this.description = description;
		this.measurementUnit = measurementUnit;
		this.quantityInStock = quantityInStock;
		this.amountOfProduct = amountOfProduct;
		this.productOwner = productOwner;
		this.productType = productType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public Long getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(Long quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public BigDecimal getAmountOfProduct() {
		return amountOfProduct;
	}

	public void setAmountOfProduct(BigDecimal amountOfProduct) {
		this.amountOfProduct = amountOfProduct;
	}

	public Customer getProductOwner() {
		return productOwner;
	}

	public void setProductOwner(Customer productOwner) {
		this.productOwner = productOwner;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
}
 