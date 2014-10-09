package com.github.nill14.ttool.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Employees")
@SequenceGenerator(name = "SEQ_Employees", sequenceName = "SEQ_Employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Employees")
	private int employeeId;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String email;

	@Column
	private String phoneNumber;

	@Column
	private Timestamp hireDate;
	
	@ManyToOne
//	@Enumerated(EnumType.STRING)
	@JoinColumn(name = "jobCode", foreignKey = @ForeignKey(name = "FK_Employee_JobCode"))
	private Job job;
	
	@Column
	private Double salary;
	
	@Column
	private Double commissionPercentage;
	
	@ManyToOne
	@JoinColumn(name = "managerId", foreignKey = @ForeignKey(name = "FK_Employee_ManagerId"))
	private Employee manager;
	
	@OneToMany(mappedBy = "manager")
	private List<Employee> employees;
	
	@ManyToOne
	@JoinColumn(name = "departmentId", foreignKey = @ForeignKey(name = "FK_Employee_DepartmentId"))
	private Department department;

	@OneToMany(mappedBy = "employee")
	@OrderBy("endDate")
	private List<JobHistory> jobHistory;
	
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getHireDate() {
		return hireDate;
	}

	public void setHireDate(Timestamp hireDate) {
		this.hireDate = hireDate;
	}


	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getCommissionPercentage() {
		return commissionPercentage;
	}

	public void setCommissionPercentage(Double commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	
	
	

	
}
