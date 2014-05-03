package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.github.nill14.ttool.entity.security.User;
import com.github.nill14.ttool.sandbox.ColumnModel;
import com.github.nill14.ttool.service.IUserService;
import com.google.common.collect.Lists;

@Named("usersBean")
@Scope("session")
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(UsersBean.class);

	@Inject
	private IUserService userService;

	private List<ColumnModel> columns = new ArrayList<ColumnModel>();
	private UsersDataModel model;  
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
		
		this.users = Lists.newArrayList(userService.getAllUsers());
		
		model = new UsersDataModel(userService, this.users);
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


	public UsersDataModel getModel() {
		return model;
	}


	public void setModel(UsersDataModel model) {
		this.model = model;
	}



	
	

}