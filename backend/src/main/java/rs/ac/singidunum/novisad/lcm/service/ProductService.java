package rs.ac.singidunum.novisad.lcm.service;

import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.repository.ProductRepository;

public class ProductService extends GenericService<Product, ProductRepository> {
	
	public ProductService(ProductRepository repository) {
		super(repository);
	}
}
