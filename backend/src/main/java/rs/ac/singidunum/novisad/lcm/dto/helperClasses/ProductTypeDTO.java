package rs.ac.singidunum.novisad.lcm.dto.helperClasses;

public class ProductTypeDTO {
	private Long id;
	private String type;
	
	public ProductTypeDTO() {
		super();
	}

	public ProductTypeDTO(Long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}
}
