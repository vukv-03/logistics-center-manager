package rs.ac.singidunum.novisad.lcm.controller.helperClasses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.singidunum.novisad.lcm.dto.helperClasses.ProductTypeDTO;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.ProductType;
import rs.ac.singidunum.novisad.lcm.service.helperClasses.ProductTypeService;

@RestController
@RequestMapping(path = "/api/product_types")
public class ProductTypeController {
	@Autowired
	private ProductTypeService service;
	
	@GetMapping(path = "")
	public List<ProductTypeDTO> getAll() {
		ArrayList<ProductTypeDTO> productTypeDTO = new ArrayList<ProductTypeDTO>();
		
		for (ProductType pt : this.service.findAll()) {
			productTypeDTO.add(this.service.toDTO(pt));
		}
		return productTypeDTO;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ProductTypeDTO> getById(@PathVariable Long id) {
		ProductType productType = this.service.findById(id);
		ProductTypeDTO productTypeDTO = this.service.toDTO(productType);
		return new ResponseEntity<ProductTypeDTO>(productTypeDTO, HttpStatus.OK);
	}
}
