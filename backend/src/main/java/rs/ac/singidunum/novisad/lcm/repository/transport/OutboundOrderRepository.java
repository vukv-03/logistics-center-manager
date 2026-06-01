package rs.ac.singidunum.novisad.lcm.repository.transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrder;

@Repository
public interface OutboundOrderRepository extends JpaRepository<OutboundOrder, Long> {

}
