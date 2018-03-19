package it.consoft.rogueblaster.repository;

import org.springframework.data.repository.CrudRepository;

import it.consoft.rogueblaster.model.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
	
}
