package rs.ac.singidunum.novisad.lcm.service.helperClasses;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.TimeSlot;
import rs.ac.singidunum.novisad.lcm.repository.helperClasses.TimeSlotRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class TimeSlotService extends GenericService<TimeSlot, TimeSlotRepository> {

	public TimeSlotService(TimeSlotRepository repository) {
		super(repository);
	}

}
