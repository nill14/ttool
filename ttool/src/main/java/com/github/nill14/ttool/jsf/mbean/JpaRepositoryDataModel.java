package com.github.nill14.ttool.jsf.mbean;  
  
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
  
/** 
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database. 
 */  
@Component
public class JpaRepositoryDataModel extends LazyDataModel<Object> implements SelectableDataModel<Object>, Serializable {  
      
	private static final long serialVersionUID = 1L;
	
	private JpaRepository<Object, ? extends Serializable> repository;
	private Function<Object, ? extends Serializable> keyFunction;

	private Map<? extends Serializable, Object> index;

    
    public JpaRepositoryDataModel() {
		
	}
	
    
    public JpaRepository<Object, ? extends Serializable> getRepository() {
		return repository;
	}

	public void setRepository(JpaRepository<? extends Object, ? extends Serializable> repository) {
		if (repository == null) {
			throw new IllegalArgumentException("Repository must not be null.");
		}
		
		this.repository = (JpaRepository<Object, ? extends Serializable>) repository;
	}

	public Function<Object, ? extends Serializable> getKeyFunction() {
		return keyFunction;
	}

	public void setKeyFunction(Function<Object, ? extends Serializable> keyFunction) {
		if (keyFunction == null) {
			throw new IllegalArgumentException("KeyFunction must not be null.");
		}
		
		this.keyFunction = keyFunction;
	}

	@Override
    public Object getRowData(String rowKey) {
    	return index.get(rowKey);
    }
    
    @Override
    public Object getRowKey(Object rowData) {
    	return keyFunction.apply(rowData);
    }
    
    @Override
    public void setWrappedData(Object list) {
    	if (list instanceof ImmutableList) {
    		super.setWrappedData(Lists.newArrayList((List<?>) list));
    	} else {
    		super.setWrappedData(list);
    	}
    }
    
    @Override
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @Transactional
    public List<Object> load(int first, int pageSize, String sortField,
    		SortOrder sortOrder, Map<String, String> filters) {
    	
    	
    	
    	int count = (int) repository.count();
		this.setRowCount(count);  

		int page = first / pageSize;
		Pageable pageable = new PageRequest(page, pageSize);

		ImmutableList<Object> list = ImmutableList.copyOf(repository.findAll(pageable));
		
		index = Maps.uniqueIndex(list, keyFunction);
		
		return list;
    }
    
    
}  