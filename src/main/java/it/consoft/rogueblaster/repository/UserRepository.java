package it.consoft.rogueblaster.repository;

import org.springframework.data.repository.CrudRepository;

import it.consoft.rogueblaster.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByUsername(String username);

}
