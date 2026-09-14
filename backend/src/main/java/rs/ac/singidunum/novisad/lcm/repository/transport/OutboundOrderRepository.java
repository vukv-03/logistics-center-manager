package rs.ac.singidunum.novisad.lcm.repository.transport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrder;

@Repository
public interface OutboundOrderRepository extends JpaRepository<OutboundOrder, Long> {

	boolean existsByShippingDateAndShippingTime(LocalDate shippingDate, LocalTime shippingTime);
	
	List<OutboundOrder> findByCustomerOrderNameContainingIgnoreCase(String customer);
}
