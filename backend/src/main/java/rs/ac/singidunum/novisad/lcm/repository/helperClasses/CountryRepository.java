package rs.ac.singidunum.novisad.lcm.repository.helperClasses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
