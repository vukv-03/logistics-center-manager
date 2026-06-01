package rs.ac.singidunum.novisad.lcm.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public class GenericService<T, R extends JpaRepository<T, Long>> {
	protected final R repository;
	
	protected GenericService(R repository) {
		this.repository = repository;
	}
	
	public Iterable<T> findAll() {
		return this.repository.findAll();
	}
	
	public Optional<T> findById(Long id) {
		return repository.findById(id);
	}
	
	public T save(T t) {
		return repository.save(t);
	}
	
	public void delete(T t) {
		this.repository.delete(t);
	}
	
	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}
}
