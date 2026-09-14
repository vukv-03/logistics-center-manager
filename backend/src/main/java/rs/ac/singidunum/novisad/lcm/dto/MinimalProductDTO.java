package rs.ac.singidunum.novisad.lcm.dto;

public class MinimalProductDTO {
	private Long id;
	private String name;
	private String description;
	
	public MinimalProductDTO() {
		super();
	}

	public MinimalProductDTO(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
}
