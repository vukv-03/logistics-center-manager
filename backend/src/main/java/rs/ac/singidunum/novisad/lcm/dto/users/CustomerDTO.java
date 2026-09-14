package rs.ac.singidunum.novisad.lcm.dto.users;

public class CustomerDTO {
	private Long id;
	private String name;
	
	public CustomerDTO() {
		super();
	}

	public CustomerDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
