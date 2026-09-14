package rs.ac.singidunum.novisad.lcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.MinimalProductDTO;
import rs.ac.singidunum.novisad.lcm.dto.ProductDTO;
import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.ProductType;
import rs.ac.singidunum.novisad.lcm.model.transport.InboundDeliveryItem;
import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrderItem;
import rs.ac.singidunum.novisad.lcm.model.users.Customer;
import rs.ac.singidunum.novisad.lcm.repository.ProductRepository;
import rs.ac.singidunum.novisad.lcm.service.helperClasses.ProductTypeService;
import rs.ac.singidunum.novisad.lcm.service.users.CustomerService;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductTypeService productService;
	
	public Iterable<Product> findAll() {
		return this.productRepository.findAll();
	}
	
	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found."));
	}
	
	public Product save(Product prod) {
		return productRepository.save(prod);
	}
	
	public void delete(Product prod) {
		this.productRepository.delete(prod);
	}
	
	public void deleteById(Long id) {
		this.productRepository.deleteById(id);
	}

///////////////////////////////////////////////////////////////////////////////	

	public ProductDTO toDTO(Product product) {
		ProductDTO newProduct = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getMeasurementUnit(), product.getQuantityInStock(), product.getAmountOfProduct(), product.getProductOwner().getId(), product.getProductType().getId());
		return newProduct;
	}
	
	public MinimalProductDTO toMinimalDTO(Product product) {
		MinimalProductDTO newProduct = new MinimalProductDTO(product.getId(), product.getName(), product.getDescription());
		return newProduct;
	}
	
	public Product toProduct(ProductDTO productDTO) {
		Product newProduct = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getMeasurementUnit(), productDTO.getQuantityInStock(), productDTO.getAmountOfProduct(), findProductOwner(productDTO.getProductOwnerId()), findProductType(productDTO.getProductTypeId()));
		return newProduct;
	}
	
///////////////////////////////////////////////////////////////////////////////

	public ProductDTO createProduct(ProductDTO productDTO) {
		isProductNameUnique(productDTO);
		Product newProduct = toProduct(productDTO);
		Product np = this.productRepository.save(newProduct);
		ProductDTO newProductDTO = toDTO(np);
		
		return newProductDTO;
	}
	
	private void isProductNameUnique(ProductDTO productDTO) {
		for (Product p : findAll()) {
			if (productDTO.getName().equals(p.getName())) {
				throw new RuntimeException("Product with that name already exists!");
			}
		}
	}
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	

	public void increaseQuantity(List<InboundDeliveryItem> items) {
		for (InboundDeliveryItem item : items) {
			Long quantity = item.getQuantity();
			Product product = item.getProduct();
			
			Long currentQuantity = product.getQuantityInStock();
			product.setQuantityInStock(currentQuantity + quantity);
		}
	}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	
	public void decreaseQuantity(List<OutboundOrderItem> items) {
		for (OutboundOrderItem item : items) {
			Long quantity = item.getQuantity();
			Product product = item.getProduct();
			
			Long currentQuantity = product.getQuantityInStock();
			product.setQuantityInStock(currentQuantity - quantity);
		}
	}
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	
	public Customer findProductOwner(Long productOwenerId) {
		Customer customer = customerService.findById(productOwenerId);
		return customer;
	}
	
	public ProductType findProductType(Long productTypeId) {
		ProductType productType = productService.findById(productTypeId);
		return productType;
	}
		
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
		
	public ProductDTO changeProduct(Long id, ProductDTO productDTO) {
		Product product = findById(id);
		isProductNameUnique(productDTO);
		
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setMeasurementUnit(productDTO.getMeasurementUnit());
		product.setAmountOfProduct(productDTO.getAmountOfProduct());
		product.setProductOwner(findProductOwner(productDTO.getProductOwnerId()));
		product.setProductType(findProductType(productDTO.getProductTypeId()));
		
		Product newProduct = save(product);
		ProductDTO result = toDTO(newProduct);
		return result;
	}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public List<Product> searchProducts(String name) {
	    return productRepository.findByNameContainingIgnoreCase(name);
	}
	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>		
}