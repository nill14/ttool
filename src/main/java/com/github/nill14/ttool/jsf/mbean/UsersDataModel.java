package com.github.nill14.ttool.jsf.mbean;  
  
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import com.github.nill14.ttool.entity.security.User;
import com.github.nill14.ttool.service.IUserService;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
  
/** 
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database. 
 */  
public class UsersDataModel extends LazyDataModel<User> implements SelectableDataModel<User>, Serializable {  
      
	private static final long serialVersionUID = 1L;
	
	private IUserService userService;

	private List<User> datasource;  
	private Map<String, User> index;
      
    public UsersDataModel(IUserService userService, List<User> datasource) {  
        this.userService = userService;
		this.datasource = datasource;
        this.index = Maps.uniqueIndex(this.datasource, new Function<User, String>() {

			@Override
			public String apply(User input) {
				return input.getUserId();
			}
		});
    }  
      
    @Override
    public User getRowData(String rowKey) {
    	return index.get(rowKey);
    }
    
    @Override
    public Object getRowKey(User object) {
    	return object.getUserId();
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
    public List<User> load(int first, int pageSize, String sortField,
    		SortOrder sortOrder, Map<String, String> filters) {
    	
    	int page = first / pageSize;
    	this.setRowCount(userService.getUsersCount());  

    	return userService.getPage(page, pageSize);
    }
    
}  