package com.github.nill14.ttool.datarepo.custom;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;

import org.springframework.stereotype.Repository;

import com.github.nill14.ttool.sandbox.ColumnModel;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Repository
public class TableRepository {

	@PersistenceContext
	private EntityManager em;
	
	private Map<String,EntityType<?>> index;
	
	@PostConstruct
	public void init() {
		Set<EntityType<?>> entities = em.getMetamodel().getEntities();
		index = Maps.uniqueIndex(entities, new Function<EntityType<?>, String>() {

			@Override
			public String apply(EntityType<?> input) {
				return input.getName();
			}
		});
	}
	
	public List<String> findTables() {
		return FluentIterable.from(index.keySet()).toList();
	}
	
	public EntityType<?> findEntityType(String tableName) {
		return index.get(tableName);
	}
	
	public List<ColumnModel> findColumns(EntityType<?> entityType) {
		return FluentIterable.from(entityType.getAttributes())
				.filter(new Predicate<Attribute<?, ?>>() {

					@Override
					public boolean apply(Attribute<?, ?> input) {
						return !input.isCollection();
					}
				})
				.transform(new Function<Attribute<?, ?>, ColumnModel>() {

			@Override
			public ColumnModel apply(Attribute<?, ?> input) {
				String header = input.getName();
				String property = input.getName();
				boolean editable = !input.isAssociation() && !input.isCollection();
				return new ColumnModel(header, property, editable);
			}
		}).toList();
	}
	
}
