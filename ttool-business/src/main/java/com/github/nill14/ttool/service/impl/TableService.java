package com.github.nill14.ttool.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Service;

import com.github.nill14.ttool.datarepo.custom.TableRepository;
import com.github.nill14.ttool.sandbox.ColumnModel;
import com.github.nill14.ttool.service.ITableService;
import com.google.common.collect.ImmutableList;

@Service
public class TableService implements ITableService, ApplicationContextAware {

	@Inject
	private TableRepository repo;
	private ApplicationContext applicationContext; 
	private Repositories repositories;
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
		this.applicationContext = applicationContext;
	}
	
	@PostConstruct
	public void init() {
		repositories = new Repositories(applicationContext);
	}
	
	@Override
	public List<String> getTableNames() {
		return ImmutableList.copyOf(repo.findTables());
	}
	
	@Override
	public EntityType<?> getEntityType(String tableName) {
		return repo.findEntityType(tableName);
	}
	
	@Override
	public List<ColumnModel> getTableColumns(EntityType<?> entityType) {
		if (entityType == null) {
			throw new IllegalArgumentException("entityType must not be null.");
		}
		
		return ImmutableList.copyOf(repo.findColumns(entityType));
	}

	@Override
	public <T> JpaRepository<T, ? extends Serializable> getRepository(EntityType<T> entityType) {
		Class<T> domainClass = entityType.getJavaType();
		JpaRepository<T, ?> repository = (JpaRepository<T, ?>) repositories.getRepositoryFor(domainClass);
		Validate.isTrue(repository != null, "Repository %s is missing.", entityType.getName());
		return repository;
	}



}
