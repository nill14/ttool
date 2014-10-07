package com.github.nill14.ttool.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Users")
@GenericGenerator(name="system-uuid", strategy = "uuid")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(generator="system-uuid")
	private String userId;
	
	@Column(nullable=false, unique=true)
	private String username;
	
	@Column(name="passwd")
	private String passwd;
	
	@Column
	private String email;
	
	@Column(nullable = false)
	private boolean activated = false;
	
	@Column(nullable = false)
	private boolean disabled = false;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	
}
