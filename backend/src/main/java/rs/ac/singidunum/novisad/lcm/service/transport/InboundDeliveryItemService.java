package rs.ac.singidunum.novisad.lcm.service.transport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.transport.InboundDeliveryItemDTO;
import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.model.transport.InboundDeliveryItem;
import rs.ac.singidunum.novisad.lcm.repository.transport.InboundDeliveryItemRepository;
import rs.ac.singidunum.novisad.lcm.service.ProductService;

@Service
public class InboundDeliveryItemService{
	@Autowired
	private InboundDeliveryItemRepository inboundDeliveryItemRepository;
	@Autowired
	private ProductService productService;
	
	public Iterable<InboundDeliveryItem> findAll() {
		return this.inboundDeliveryItemRepository.findAll();
	}
	
	public InboundDeliveryItem findById(Long id) {
		return inboundDeliveryItemRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
	}
	
	public InboundDeliveryItem save(InboundDeliveryItem item) {
		return inboundDeliveryItemRepository.save(item);
	}
	
	public void delete(InboundDeliveryItem item) {
		this.inboundDeliveryItemRepository.delete(item);
	}
	
	public void deleteById(Long id) {
		this.inboundDeliveryItemRepository.deleteById(id);
	}
	
///////////////////////////////////////////////////////////////////////////////	
	
	public InboundDeliveryItemDTO toDTO(InboundDeliveryItem item) {
		InboundDeliveryItemDTO newInboundDeliveryItemDTO = new InboundDeliveryItemDTO(item.getId(), productService.toMinimalDTO(item.getProduct()),  item.getQuantity());
		return newInboundDeliveryItemDTO;
	}
	
	public List<InboundDeliveryItemDTO> listToDTO(List<InboundDeliveryItem> items) {
		ArrayList<InboundDeliveryItemDTO> listItemsDTO = new ArrayList<InboundDeliveryItemDTO>();
		
		for (InboundDeliveryItem ii : items) {
			InboundDeliveryItemDTO newInboundDeliveryItemDTO = new InboundDeliveryItemDTO(ii.getId(), productService.toMinimalDTO(ii.getProduct()), ii.getQuantity());
			listItemsDTO.add(newInboundDeliveryItemDTO);
		}
		return listItemsDTO;
	}
	
	public InboundDeliveryItem toDelivery(InboundDeliveryItemDTO itemDTO) {
		InboundDeliveryItem newInboundDeliveryItem = new InboundDeliveryItem(itemDTO.getId(), findProduct(itemDTO.getProduct().getId()), itemDTO.getQuantity());
		return newInboundDeliveryItem;
	}
	
	public List<InboundDeliveryItem> listToDelivery(List<InboundDeliveryItemDTO> itemsDTO) {
		ArrayList<InboundDeliveryItem> listItems = new ArrayList<InboundDeliveryItem>();
		
		for (InboundDeliveryItemDTO ii : itemsDTO) {
			InboundDeliveryItem newInboundDeliveryItem = new InboundDeliveryItem(ii.getId(), findProduct(ii.getProduct().getId()), ii.getQuantity());
			listItems.add(newInboundDeliveryItem);
		}
		return listItems;
	}

///////////////////////////////////////////////////////////////////////////////

	private Product findProduct(Long productId) {
		Product product = this.productService.findById(productId);
		return product;
	}
}
