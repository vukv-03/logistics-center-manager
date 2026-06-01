package rs.ac.singidunum.novisad.lcm.repository.transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.transport.InboundDelivery;

@Repository
public interface InboundDeliveryRepository extends JpaRepository<InboundDelivery, Long> {

}
