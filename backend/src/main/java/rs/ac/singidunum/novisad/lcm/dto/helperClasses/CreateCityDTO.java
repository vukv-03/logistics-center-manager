package rs.ac.singidunum.novisad.lcm.dto.helperClasses;

import java.util.List;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.Address;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.Country;

public class CreateCityDTO {
	private Long id;
	private String name;
	private Long zipCode;
	private List<Address> adresses;
	private Country country;
	
	public CreateCityDTO() {
		super();
	}

	public CreateCityDTO(Long id, String name, Long zipCode, List<Address> adresses, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.zipCode = zipCode;
		this.adresses = adresses;
		this.country = country;
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

	public List<Address> getAdresses() {
		return adresses;
	}

	public Country getCountry() {
		return country;
	}
}
