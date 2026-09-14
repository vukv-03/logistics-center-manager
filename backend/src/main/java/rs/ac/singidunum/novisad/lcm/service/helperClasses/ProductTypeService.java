package rs.ac.singidunum.novisad.lcm.service.helperClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.helperClasses.ProductTypeDTO;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.ProductType;
import rs.ac.singidunum.novisad.lcm.repository.helperClasses.ProductTypeRepository;

@Service
public class ProductTypeService {
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	public Iterable<ProductType> findAll() {
		return this.productTypeRepository.findAll();
	}
	
	public ProductType findById(Long id) {
		return productTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found."));
	}
	
	public ProductType save(ProductType productType) {
		return productTypeRepository.save(productType);
	}
	
	public void delete(ProductType productType) {
		this.productTypeRepository.delete(productType);
	}
	
	public void deleteById(Long id) {
		this.productTypeRepository.deleteById(id);
	}
	
///////////////////////////////////////////////////////////////////////////////	

	public ProductTypeDTO toDTO(ProductType productType) {
		ProductTypeDTO newProductType = new ProductTypeDTO(productType.getId(), productType.getType());
		return newProductType;
	}

///////////////////////////////////////////////////////////////////////////////	
}
