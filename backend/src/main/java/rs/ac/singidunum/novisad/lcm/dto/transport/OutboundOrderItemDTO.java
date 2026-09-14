package rs.ac.singidunum.novisad.lcm.dto.transport;

import rs.ac.singidunum.novisad.lcm.dto.MinimalProductDTO;

public class OutboundOrderItemDTO {
	private Long id;
	private MinimalProductDTO product;
	private Long quantity;
	
	public OutboundOrderItemDTO() {
		super();
	}

	public OutboundOrderItemDTO(Long id, MinimalProductDTO product, Long quantity) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public MinimalProductDTO getProduct() {
		return product;
	}
	
	public Long getQuantity() {
		return quantity;
	}
}
