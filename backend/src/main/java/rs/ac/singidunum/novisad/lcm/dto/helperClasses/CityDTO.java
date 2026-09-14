package rs.ac.singidunum.novisad.lcm.dto.helperClasses;

public class CityDTO {
	private Long id;
	private String name;
	private Long zipCode;
	
	public CityDTO() {
		super();
	}
	
	public CityDTO(Long id, String name, Long zipCode) {
		super();
		this.id = id;
		this.name = name;
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Long getZipCode() {
		return zipCode;
	}
}
