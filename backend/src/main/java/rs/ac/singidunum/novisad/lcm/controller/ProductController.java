package rs.ac.singidunum.novisad.lcm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.novisad.lcm.dto.ProductDTO;
import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping(path = "")
	public List<ProductDTO> getAll() {
		ArrayList<ProductDTO> productDTO = new ArrayList<ProductDTO>();
		
		for (Product p : this.service.findAll()) {
			productDTO.add(this.service.toDTO(p));
		}
		return productDTO;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
		Product product = this.service.findById(id);
		ProductDTO productDTO = this.service.toDTO(product);
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}
	
	@PostMapping(path = "")
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO newProductDTO) {
		ProductDTO newProduct = this.service.createProduct(newProductDTO);
		
		return new ResponseEntity<ProductDTO>(newProduct, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO newProductDTO) {
		ProductDTO result = this.service.changeProduct(id, newProductDTO);
		
		return new ResponseEntity<ProductDTO>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ProductDTO> deleteById(@PathVariable Long id) {
		this.service.deleteById(id);
		
		return new ResponseEntity<ProductDTO>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search")
	public List<ProductDTO> searchProducts(@RequestParam String name) {			
	    List<ProductDTO> result = new ArrayList<>();

	    for (Product product : service.searchProducts(name)) {
	        result.add(service.toDTO(product));
	    }
	    return result;
	}
}
