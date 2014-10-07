package com.github.nill14.ttool.datarepo.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.github.nill14.ttool.datarepo.UserRepositoryCustom;
import com.google.common.collect.Sets;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Set<String> findAllUsernames() {
		TypedQuery<String> query = em.createQuery("select u.username from User u", String.class);
		List<String> resultList = query.getResultList();
		return Sets.newHashSet(resultList);
	}
	

}
