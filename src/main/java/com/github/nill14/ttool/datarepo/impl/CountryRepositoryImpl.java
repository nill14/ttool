package com.github.nill14.ttool.datarepo.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.github.nill14.ttool.datarepo.CountryRepositoryCustom;
import com.github.nill14.ttool.entity.Country;

public class CountryRepositoryImpl implements CountryRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Country> findAllWithRegions() {
		TypedQuery<Country> query = em.createQuery("from Country c join fetch c.region", Country.class);
		return query.getResultList();
	}

}
