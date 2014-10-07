package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.metamodel.EntityType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.github.nill14.ttool.datarepo.UserRepository;
import com.github.nill14.ttool.entity.security.User;
import com.github.nill14.ttool.sandbox.ColumnModel;
import com.github.nill14.ttool.service.ITableService;
import com.google.common.base.Function;

@Named("usersBean")
@Scope("session")
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UsersBean.class);

	@Inject
	private ITableService tableService;
	
	@Inject 
	private UserRepository userRepository;

	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private JpaRepositoryDataModel model;  
	private List<User> users;
	private User selectedUser;

    
  
	
	@PostConstruct
	public void init() {
		columns.add(new ColumnModel("User ID", "userId", false));
		columns.add(new ColumnModel("Username", "username", false));
		columns.add(new ColumnModel("Password", "passwd", false));
		columns.add(new ColumnModel("E-mail", "email", true));
		columns.add(new ColumnModel("Activated", "activated", true));
		columns.add(new ColumnModel("Disabled", "disabled", true));
		
		
		EntityType<?> entityType = tableService.getEntityType("User");
		model = tableService.getDataModel(entityType);
		model.setKeyFunction((Function) new Function<User, String>() {

			@Override
			public String apply(User input) {
				return input.getUserId();
			}
		});
	}
	
	
	public List<User> getUsers() {
		return users;
	}
	
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	public List<ColumnModel> getColumns() {
		return columns;
	}


	public User getSelectedUser() {
		return selectedUser;
	}


	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}


	public JpaRepositoryDataModel getModel() {
		return model;
	}


	public void setModel(JpaRepositoryDataModel model) {
		this.model = model;
	}



	
	

}