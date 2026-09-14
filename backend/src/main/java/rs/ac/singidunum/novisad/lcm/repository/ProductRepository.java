package rs.ac.singidunum.novisad.lcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByProductOwner(Customer productOwner);
	
	List<Product> findByNameContainingIgnoreCase(String name);
}
