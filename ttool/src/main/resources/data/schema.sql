DROP ALL OBJECTS;


CREATE SEQUENCE PUBLIC.SEQ_LOCATIONS START WITH 1;            
CREATE SEQUENCE PUBLIC.SEQ_DEPARTMENTS START WITH 1;          
CREATE SEQUENCE PUBLIC.SEQ_EMPLOYEES START WITH 1;            
CREATE SEQUENCE PUBLIC.SEQ_REGIONS START WITH 1;  

-- counties table creation
	CREATE TABLE countries (
	CountryISOCode varchar2(2) NOT NULL,
	CountryName varchar2(40) default NULL,
	RegionID int default NULL
);
ALTER TABLE PUBLIC.COUNTRIES ADD CONSTRAINT PUBLIC.CONSTRAINT_F PRIMARY KEY(COUNTRYISOCODE); 

-- departments table creation
CREATE TABLE departments (
	DepartmentID int(11) NOT NULL,
	DepartmentName varchar(30) default NULL,
	ManagerID int(11) default NULL,
	LocationID int(11) default NULL
);
ALTER TABLE PUBLIC.DEPARTMENTS ADD CONSTRAINT PUBLIC.CONSTRAINT_9 PRIMARY KEY(DEPARTMENTID); 

-- employees table creation
CREATE TABLE employees (
	EmployeeID int(11) NOT NULL,
	FirstName varchar(20) default NULL,
	LastName varchar(25) default NULL,
	Email varchar(25) default NULL,
	PhoneNumber varchar(20) default NULL,
	HireDate datetime default NULL,
	JobCode varchar(10) default NULL,
	Salary double default NULL,
	CommissionPercentage double default NULL,
	ManagerID int(11) default NULL,
	DepartmentID int(11) default NULL
);
ALTER TABLE PUBLIC.EMPLOYEES ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(EMPLOYEEID); 

-- job_history table creation
CREATE TABLE job_history (
	EmployeeID int(11) NOT NULL,
	StartDate datetime default NULL,
	EndDate datetime default NULL,
	JobCode varchar(10) NOT NULL,
	DepartmentID int(11) default NULL
);
ALTER TABLE PUBLIC.JOB_HISTORY ADD CONSTRAINT PUBLIC.CONSTRAINT_95 PRIMARY KEY(EMPLOYEEID, JOBCODE);  
  
-- jobs table creation   
CREATE TABLE jobs (
	JobCode varchar(10) NOT NULL,
	JobTitle varchar(35) default NULL,
	MinSalary int(11) default NULL,
	MaxSalary int(11) default NULL
);
ALTER TABLE PUBLIC.JOBS ADD CONSTRAINT PUBLIC.CONSTRAINT_2 PRIMARY KEY(JOBCODE);


-- location tabke creation
CREATE TABLE locations (
	LocationID int(11) NOT NULL,
	StreetAddress varchar(40) default NULL,
	PostalCode varchar(12) default NULL,
	City varchar(30) default NULL,
	StateProvince varchar(25) default NULL,
	CountryISOCode varchar(2) default NULL
);
ALTER TABLE PUBLIC.LOCATIONS ADD CONSTRAINT PUBLIC.CONSTRAINT_5 PRIMARY KEY(LOCATIONID);

-- regions table creation
CREATE TABLE regions (
	RegionID int(11) NOT NULL,
	RegionName varchar(25) default NULL
);
ALTER TABLE PUBLIC.REGIONS ADD CONSTRAINT PUBLIC.CONSTRAINT_6 PRIMARY KEY(REGIONID); 

CREATE TABLE PUBLIC.USERS(
    USER_ID VARCHAR(255) NOT NULL,
    USERNAME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(255),
    PASSWD VARCHAR(255),
    ACTIVATED BOOLEAN NOT NULL DEFAULT FALSE,
    DISABLED BOOLEAN NOT NULL DEFAULT FALSE    
);              
ALTER TABLE PUBLIC.USERS ADD CONSTRAINT PUBLIC.CONSTRAINT_4D PRIMARY KEY(USER_ID);    

ALTER TABLE PUBLIC.COUNTRIES ADD CONSTRAINT PUBLIC.FK_COUNTRY_REGIONID FOREIGN KEY(REGIONID) REFERENCES PUBLIC.REGIONS(REGIONID) NOCHECK;     
ALTER TABLE PUBLIC.JOB_HISTORY ADD CONSTRAINT PUBLIC.FK_JOB_HISTORY_JOBCODE FOREIGN KEY(JOBCODE) REFERENCES PUBLIC.JOBS(JOBCODE) NOCHECK;     
--ALTER TABLE PUBLIC.JOB_HISTORY ADD CONSTRAINT PUBLIC.FK_JOB_HISTORY_EMPLOYEEID FOREIGN KEY(EMPLOYEEID) REFERENCES PUBLIC.EMPLOYEES(EMPLOYEEID) NOCHECK;       
ALTER TABLE PUBLIC.EMPLOYEES ADD CONSTRAINT PUBLIC.FK_EMPLOYEE_DEPARTMENTID FOREIGN KEY(DEPARTMENTID) REFERENCES PUBLIC.DEPARTMENTS(DEPARTMENTID) NOCHECK;    
ALTER TABLE PUBLIC.EMPLOYEES ADD CONSTRAINT PUBLIC.FK_EMPLOYEE_JOBCODE FOREIGN KEY(JOBCODE) REFERENCES PUBLIC.JOBS(JOBCODE) NOCHECK;          
--ALTER TABLE PUBLIC.DEPARTMENTS ADD CONSTRAINT PUBLIC.FK_DEPARTMENT_LOCATIONID FOREIGN KEY(LOCATIONID) REFERENCES PUBLIC.LOCATIONS(LOCATIONID) NOCHECK;        
--ALTER TABLE PUBLIC.DEPARTMENTS ADD CONSTRAINT PUBLIC.FK_DEPARTMENT_MANAGERID FOREIGN KEY(MANAGERID) REFERENCES PUBLIC.EMPLOYEES(EMPLOYEEID) NOCHECK;          
ALTER TABLE PUBLIC.LOCATIONS ADD CONSTRAINT PUBLIC.FK_LOCATION_COUNTRYISOCODE FOREIGN KEY(COUNTRYISOCODE) REFERENCES PUBLIC.COUNTRIES(COUNTRYISOCODE) NOCHECK;
--ALTER TABLE PUBLIC.EMPLOYEES ADD CONSTRAINT PUBLIC.FK_EMPLOYEE_MANAGERID FOREIGN KEY(MANAGERID) REFERENCES PUBLIC.EMPLOYEES(EMPLOYEEID) NOCHECK;              
ALTER TABLE PUBLIC.JOB_HISTORY ADD CONSTRAINT PUBLIC.FK_JOB_HISTORY_DEPARTMENTID FOREIGN KEY(DEPARTMENTID) REFERENCES PUBLIC.DEPARTMENTS(DEPARTMENTID) NOCHECK;               