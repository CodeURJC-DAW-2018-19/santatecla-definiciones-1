package com.grupo5.definiciones.repositories;

import org.springframework.data.repository.CrudRepository;
import com.grupo5.definiciones.model.*;

public interface UserRepository extends CrudRepository<User,Long>{

	User findByName(String name);
	
}
