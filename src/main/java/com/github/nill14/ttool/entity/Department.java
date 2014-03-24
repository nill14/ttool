package com.github.nill14.ttool.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Departments")
@SequenceGenerator(name = "SEQ_Departments", sequenceName = "SEQ_Departments")
public class Department {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Departments")
	private int departmentId;
	
	@Column(name = "departmentName")
	private String name;
	
	@OneToOne
	@JoinColumn(name = "managerId", nullable = true,
			foreignKey = @ForeignKey(name = "none"))
	private Employee manager;
	
	@ManyToOne
	@JoinColumn(name = "locationId", nullable = true, 
			foreignKey = @ForeignKey(name = "FK_Department_LocationId"))
	private Location location;
	
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
	
}
