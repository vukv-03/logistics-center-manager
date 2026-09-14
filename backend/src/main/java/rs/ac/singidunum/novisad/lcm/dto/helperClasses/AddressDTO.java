package rs.ac.singidunum.novisad.lcm.dto.helperClasses;

public class AddressDTO {
	private Long id;
	private String street;
	private Long buildingNumber;
	private CityDTO city;
	
	public AddressDTO() {
		super();
	}

	public AddressDTO(Long id, String street, Long buildingNumber, CityDTO city) {
		super();
		this.id = id;
		this.street = street;
		this.buildingNumber = buildingNumber;
		this.city = city;
	}

	public Long getId() {
		return id;
	}
	
	public String getStreet() {
		return street;
	}

	public Long getBuildingNumber() {
		return buildingNumber;
	}

	public CityDTO getCity() {
		return city;
	}
}
