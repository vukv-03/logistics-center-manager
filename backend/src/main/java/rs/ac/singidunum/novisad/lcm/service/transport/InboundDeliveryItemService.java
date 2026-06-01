package rs.ac.singidunum.novisad.lcm.service.transport;

import rs.ac.singidunum.novisad.lcm.model.transport.InboundDeliveryItem;
import rs.ac.singidunum.novisad.lcm.repository.transport.InboundDeliveryItemRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class InboundDeliveryItemService extends GenericService<InboundDeliveryItem, InboundDeliveryItemRepository> {

	public InboundDeliveryItemService(InboundDeliveryItemRepository repository) {
		super(repository);
	}

}
