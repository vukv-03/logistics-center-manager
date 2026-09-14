package rs.ac.singidunum.novisad.lcm.service.helperClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.helperClasses.AddressDTO;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.Address;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.City;
import rs.ac.singidunum.novisad.lcm.repository.helperClasses.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CityService cityService;
	
	public Iterable<Address> findAll() {
		return this.addressRepository.findAll();
	}
	
	public Address findById(Long id) {
		return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found."));
	}
	
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	public void delete(Address address) {
		this.addressRepository.delete(address);
	}
	
	public void deleteById(Long id) {
		this.addressRepository.deleteById(id);
	}
	
///////////////////////////////////////////////////////////////////////////////	
	
	public AddressDTO toDTO(Address address) {
		AddressDTO newAddressDTO = new AddressDTO(address.getId(), address.getStreet(), address.getBuildingNumber(), cityService.toDto(address.getCity()));
		return newAddressDTO;
	}
	
	public Address toAddress(AddressDTO addressDTO) {
		Address newAddress = new Address(addressDTO.getId(), addressDTO.getStreet(), addressDTO.getBuildingNumber(), findCity(addressDTO));
		return newAddress;
	}

///////////////////////////////////////////////////////////////////////////////

	private City findCity(AddressDTO addressDTO) {
		City city = cityService.findById(addressDTO.getCity().getId());
		return city;
	}
	

}
