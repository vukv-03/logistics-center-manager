package rs.ac.singidunum.novisad.lcm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.Address;

@Entity
public class FulfilmentCenter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long numberOfAvailableDocks;
	
	@ManyToOne()
	private Address address;
	
	@Column(nullable = false)
	private String name;

	public FulfilmentCenter() {
		super();
	}

	public FulfilmentCenter(Long id, Address address, Long numberOfAvailableDocks, String name) {
		super();
		this.id = id;
		this.address = address;
		this.numberOfAvailableDocks = numberOfAvailableDocks;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getNumberOfAvailableDocks() {
		return numberOfAvailableDocks;
	}

	public void setNumberOfAvailableDocks(Long numberOfAvailableDocks) {
		this.numberOfAvailableDocks = numberOfAvailableDocks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
