package rs.ac.singidunum.novisad.lcm.service.helperClasses;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.Address;
import rs.ac.singidunum.novisad.lcm.repository.helperClasses.AddressRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class AddressService  extends GenericService<Address, AddressRepository> {

	public AddressService(AddressRepository repository) {
		super(repository);
	}

}
