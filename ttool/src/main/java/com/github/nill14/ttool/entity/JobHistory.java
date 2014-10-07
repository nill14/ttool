package com.github.nill14.ttool.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Job_History")
public class JobHistory {

	@EmbeddedId
	private JobHistoryId jobHistoryId;
	
	@ManyToOne
	@JoinColumn(name = "departmentId", foreignKey = @ForeignKey(name = "FK_Job_History_DepartmentId"))
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "employeeId", foreignKey = @ForeignKey(name = "FK_Job_History_EmployeeId"), 
			insertable = false, updatable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "jobCode", foreignKey = @ForeignKey(name = "FK_Job_History_jobCode"), 
			insertable = false, updatable = false)
	private Job job;	
	
	@Column
	private Timestamp startDate;

	@Column
	private Timestamp endDate;


	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	
	
}

@Embeddable
class JobHistoryId implements Serializable {
	private static final long serialVersionUID = -1163897562342305321L;

	
	private int employeeId;
	
	private String jobCode;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
		result = prime * result + ((jobCode == null) ? 0 : jobCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobHistoryId other = (JobHistoryId) obj;
		if (employeeId != other.employeeId)
			return false;
		if (jobCode == null) {
			if (other.jobCode != null)
				return false;
		} else if (!jobCode.equals(other.jobCode))
			return false;
		return true;
	}
	
	
}