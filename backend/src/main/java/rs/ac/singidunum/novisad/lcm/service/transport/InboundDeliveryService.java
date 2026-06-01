package rs.ac.singidunum.novisad.lcm.service.transport;

import rs.ac.singidunum.novisad.lcm.model.transport.InboundDelivery;
import rs.ac.singidunum.novisad.lcm.repository.transport.InboundDeliveryRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class InboundDeliveryService extends GenericService<InboundDelivery, InboundDeliveryRepository> {

	public InboundDeliveryService(InboundDeliveryRepository repository) {
		super(repository);
	}

}
