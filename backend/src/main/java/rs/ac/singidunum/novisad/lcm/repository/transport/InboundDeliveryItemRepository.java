package rs.ac.singidunum.novisad.lcm.repository.transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.transport.InboundDeliveryItem;

@Repository
public interface InboundDeliveryItemRepository extends JpaRepository<InboundDeliveryItem, Long> {

}
