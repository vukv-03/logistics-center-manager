package rs.ac.singidunum.novisad.lcm.service.helperClasses;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.Country;
import rs.ac.singidunum.novisad.lcm.repository.helperClasses.CountryRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class CountryService extends GenericService<Country, CountryRepository> {

	public CountryService(CountryRepository repository) {
		super(repository);
	}

}
