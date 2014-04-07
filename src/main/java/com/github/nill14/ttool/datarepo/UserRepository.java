package com.github.nill14.ttool.datarepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.entity.security.User;

public interface UserRepository extends UserRepositoryCustom, JpaRepository<User, String> {

	User findByUsername(String username);
}
