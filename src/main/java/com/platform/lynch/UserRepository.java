package com.platform.lynch;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<GenericUser, String> {
	
	Optional<GenericUser> findById(String id);
	
}