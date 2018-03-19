package it.consoft.rogueblaster.service;

import org.springframework.stereotype.Service;
import it.consoft.rogueblaster.model.UserModel;
import it.consoft.rogueblaster.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Override
	public UserModel save(UserModel user) {
		return userRepository.save(user);
	}
}
