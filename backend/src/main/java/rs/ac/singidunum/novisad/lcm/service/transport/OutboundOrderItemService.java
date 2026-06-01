package rs.ac.singidunum.novisad.lcm.service.transport;

import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrderItem;
import rs.ac.singidunum.novisad.lcm.repository.transport.OutboundOrderItemRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class OutboundOrderItemService extends GenericService<OutboundOrderItem, OutboundOrderItemRepository> {

	public OutboundOrderItemService(OutboundOrderItemRepository repository) {
		super(repository);
	}

}
