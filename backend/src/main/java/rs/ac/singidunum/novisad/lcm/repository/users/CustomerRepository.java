package rs.ac.singidunum.novisad.lcm.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.users.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
