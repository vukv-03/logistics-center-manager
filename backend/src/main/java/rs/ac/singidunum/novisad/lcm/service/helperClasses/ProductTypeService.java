package rs.ac.singidunum.novisad.lcm.service.helperClasses;

import rs.ac.singidunum.novisad.lcm.model.helperClasses.ProductType;
import rs.ac.singidunum.novisad.lcm.repository.helperClasses.ProductTypeRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class ProductTypeService extends GenericService<ProductType, ProductTypeRepository>{

	public ProductTypeService(ProductTypeRepository repository) {
		super(repository);
	}

}
