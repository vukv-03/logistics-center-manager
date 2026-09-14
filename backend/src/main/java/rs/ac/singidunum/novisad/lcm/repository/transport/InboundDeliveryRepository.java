package rs.ac.singidunum.novisad.lcm.repository.transport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.transport.InboundDelivery;

@Repository
public interface InboundDeliveryRepository extends JpaRepository<InboundDelivery, Long> {
	
	boolean existsByArrivalDateAndDockingTime(LocalDate arrivalDate, LocalTime dockingTime);
	
	List<InboundDelivery> findByCustomerDeliveryNameContainingIgnoreCase(String customer);
}
