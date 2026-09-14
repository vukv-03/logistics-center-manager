package rs.ac.singidunum.novisad.lcm.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.singidunum.novisad.lcm.model.users.User;
import rs.ac.singidunum.novisad.lcm.repository.users.UserRepository;

@Service
public class UserService{
	@Autowired
	private UserRepository userRepository;
	
	public Iterable<User> findAll() {
		return this.userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void delete(User user) {
		this.userRepository.delete(user);
	}
	
	public void deleteById(Long id) {
		this.userRepository.deleteById(id);
	}

}
