package rs.ac.singidunum.novisad.lcm.service;

import rs.ac.singidunum.novisad.lcm.model.FulfilmentCenter;
import rs.ac.singidunum.novisad.lcm.repository.FulfilmentCenterRepository;

public class FulfilmentCenterService extends GenericService<FulfilmentCenter, FulfilmentCenterRepository> {

	public FulfilmentCenterService(FulfilmentCenterRepository repository) {
		super(repository);
	}

}
