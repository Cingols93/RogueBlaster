package it.consoft.rogueblaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.consoft.rogueblaster.model.User;
import it.consoft.rogueblaster.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}

