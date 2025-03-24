package com.app.Hospital.Management.System.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Hospital.Management.System.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
	User findByEmail(String email);
}
