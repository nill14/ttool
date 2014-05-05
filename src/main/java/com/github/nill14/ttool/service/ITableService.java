package com.github.nill14.ttool.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.EntityType;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.nill14.ttool.jsf.mbean.JpaRepositoryDataModel;
import com.github.nill14.ttool.sandbox.ColumnModel;

public interface ITableService {
	
	List<String> getTableNames();

	List<ColumnModel> getTableColumns(EntityType<?> entityType);

	EntityType<?> getEntityType(String tableName);

	<T> JpaRepository<T, ? extends Serializable> getRepository(EntityType<T> entityType);

	<T> JpaRepositoryDataModel getDataModel(EntityType<T> entityType);

}
