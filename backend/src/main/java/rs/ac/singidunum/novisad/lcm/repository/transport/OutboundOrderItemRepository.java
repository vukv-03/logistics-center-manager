package rs.ac.singidunum.novisad.lcm.repository.transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrderItem;

@Repository
public interface OutboundOrderItemRepository extends JpaRepository<OutboundOrderItem, Long> {
	
}
