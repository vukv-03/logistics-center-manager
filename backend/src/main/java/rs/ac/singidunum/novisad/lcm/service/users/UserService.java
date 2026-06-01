package rs.ac.singidunum.novisad.lcm.service.users;

import rs.ac.singidunum.novisad.lcm.model.users.User;
import rs.ac.singidunum.novisad.lcm.repository.users.UserRepository;
import rs.ac.singidunum.novisad.lcm.service.GenericService;

public class UserService extends GenericService<User, UserRepository>{

	public UserService(UserRepository repository) {
		super(repository);
	}

}
