package rs.ac.singidunum.novisad.lcm.service.helperClasses;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.City;
import rs.ac.singidunum.novisad.lcm.repository.helperClasses.CityRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class CityService extends GenericService<City, CityRepository> {

	public CityService(CityRepository repository) {
		super(repository);
	}

}
