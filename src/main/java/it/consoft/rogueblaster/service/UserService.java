package it.consoft.rogueblaster.service;

import it.consoft.rogueblaster.model.User;

public interface UserService {

	User saveUser(User user);

	User findByUsername(String username);

}
