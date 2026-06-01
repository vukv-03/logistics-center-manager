package rs.ac.singidunum.novisad.lcm.service;

import rs.ac.singidunum.novisad.lcm.model.Inventory;
import rs.ac.singidunum.novisad.lcm.repository.InventoryRepository;

public class InventoryService extends GenericService<Inventory, InventoryRepository> {

	public InventoryService(InventoryRepository repository) {
		super(repository);
	}

}
