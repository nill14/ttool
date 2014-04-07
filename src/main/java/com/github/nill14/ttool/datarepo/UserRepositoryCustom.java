package com.github.nill14.ttool.datarepo;

import java.util.Set;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepositoryCustom {

	Set<String> findAllUsernames();
	
}
