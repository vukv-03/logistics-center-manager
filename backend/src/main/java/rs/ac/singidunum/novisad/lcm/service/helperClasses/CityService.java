package rs.ac.singidunum.novisad.lcm.service.helperClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.dto.helperClasses.CityDTO;
import rs.ac.singidunum.novisad.lcm.dto.helperClasses.CreateCityDTO;
import rs.ac.singidunum.novisad.lcm.model.helperClasses.City;
import rs.ac.singidunum.novisad.lcm.repository.helperClasses.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	public Iterable<City> findAll() {
		return this.cityRepository.findAll();
	}
	
	public City findById(Long id) {
		return cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found."));
	}
	
	public City save(City city) {
		return cityRepository.save(city);
	}
	
	public void delete(City city) {
		this.cityRepository.delete(city);
	}
	
	public void deleteById(Long id) {
		this.cityRepository.deleteById(id);
	}
	
///////////////////////////////////////////////////////////////////////////////	
	
	public CityDTO toDto(City city) {
		CityDTO newCityDTO = new CityDTO(city.getId(), city.getName(), city.getZipCode());
		return newCityDTO;
	}
	
	public City toCity(CreateCityDTO cityDTO) {
		City newCity = new City(cityDTO.getId(), cityDTO.getName(), cityDTO.getZipCode(), cityDTO.getAdresses(), cityDTO.getCountry());
		return newCity;
	}

///////////////////////////////////////////////////////////////////////////////
}
