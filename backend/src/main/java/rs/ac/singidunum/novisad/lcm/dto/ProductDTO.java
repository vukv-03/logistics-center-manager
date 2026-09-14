package rs.ac.singidunum.novisad.lcm.dto;

import java.math.BigDecimal;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.enums.MeasurementUnit;

public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private MeasurementUnit measurementUnit;
	private Long quantityInStock;
	private BigDecimal amountOfProduct;
	private Long productOwnerId;
	private Long productTypeId;
	
	public ProductDTO() {
		super();
	}
	
	public ProductDTO(Long id, String name, String description, MeasurementUnit measurementUnit, Long quantityInStock,
			BigDecimal amountOfProduct, Long productOwnerId, Long productTypeId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.measurementUnit = measurementUnit;
		this.quantityInStock = quantityInStock;
		this.amountOfProduct = amountOfProduct;
		this.productOwnerId = productOwnerId;
		this.productTypeId = productTypeId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}

	public Long getQuantityInStock() {
		return quantityInStock;
	}

	public BigDecimal getAmountOfProduct() {
		return amountOfProduct;
	}

	public Long getProductOwnerId() {
		return productOwnerId;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}
}
