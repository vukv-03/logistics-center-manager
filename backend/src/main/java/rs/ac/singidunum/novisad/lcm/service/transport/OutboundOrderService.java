package rs.ac.singidunum.novisad.lcm.service.transport;

import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrder;
import rs.ac.singidunum.novisad.lcm.repository.transport.OutboundOrderRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class OutboundOrderService extends GenericService<OutboundOrder, OutboundOrderRepository> {

	public OutboundOrderService(OutboundOrderRepository repository) {
		super(repository);
	}

}
