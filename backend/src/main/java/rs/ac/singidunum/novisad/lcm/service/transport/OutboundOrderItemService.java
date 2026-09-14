package rs.ac.singidunum.novisad.lcm.service.transport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.transport.OutboundOrderItemDTO;
import rs.ac.singidunum.novisad.lcm.model.Product;
import rs.ac.singidunum.novisad.lcm.model.transport.OutboundOrderItem;
import rs.ac.singidunum.novisad.lcm.repository.transport.OutboundOrderItemRepository;
import rs.ac.singidunum.novisad.lcm.service.ProductService;

@Service
public class OutboundOrderItemService {
	@Autowired
	private OutboundOrderItemRepository outboundOrderItemRepository;
	@Autowired
	private ProductService productService;
	
	public Iterable<OutboundOrderItem> findAll() {
		return this.outboundOrderItemRepository.findAll();
	}
	
	public OutboundOrderItem findById(Long id) {
		return outboundOrderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Outbound order item not found."));
	}
	
	public OutboundOrderItem save(OutboundOrderItem item) {
		return outboundOrderItemRepository.save(item);
	}
	
	public void delete(OutboundOrderItem item) {
		this.outboundOrderItemRepository.delete(item);
	}
	
	public void deleteById(Long id) {
		this.outboundOrderItemRepository.deleteById(id);
	}
	
///////////////////////////////////////////////////////////////////////////////	
	
	public OutboundOrderItemDTO toDTO(OutboundOrderItem item) {
		OutboundOrderItemDTO newOutboundOrderItemDTO = new OutboundOrderItemDTO(item.getId(), productService.toMinimalDTO(item.getProduct()),  item.getQuantity());
		return newOutboundOrderItemDTO;
	}
	
	public List<OutboundOrderItemDTO> listToDTO(List<OutboundOrderItem> items) {
		ArrayList<OutboundOrderItemDTO> listItemsDTO = new ArrayList<OutboundOrderItemDTO>();
		
		for (OutboundOrderItem oi : items) {
			OutboundOrderItemDTO newOutboundOrderItemDTO = new OutboundOrderItemDTO(oi.getId(), productService.toMinimalDTO(oi.getProduct()), oi.getQuantity());
			listItemsDTO.add(newOutboundOrderItemDTO);
		}
		return listItemsDTO;
	}
	
	public OutboundOrderItem toOrder(OutboundOrderItemDTO itemDTO) {
		OutboundOrderItem newOutboundOrderItem = new OutboundOrderItem(itemDTO.getId(), findProduct(itemDTO.getProduct().getId()), itemDTO.getQuantity());
		return newOutboundOrderItem;
	}
	
	public List<OutboundOrderItem> listToOrder(List<OutboundOrderItemDTO> itemsDTO) {
		ArrayList<OutboundOrderItem> listItems = new ArrayList<OutboundOrderItem>();
		
		for (OutboundOrderItemDTO oi : itemsDTO) {
			OutboundOrderItem newOutboundOrderItem = new OutboundOrderItem(oi.getId(), findProduct(oi.getProduct().getId()), oi.getQuantity());
			listItems.add(newOutboundOrderItem);
		}
		return listItems;
	}

///////////////////////////////////////////////////////////////////////////////

	private Product findProduct(Long productId) {
		Product product = this.productService.findById(productId);
		return product;
	}
	
	
}
