-- Drop celej DB
DROP ALL OBJECTS;

-- counties table creation
	CREATE TABLE countries (
	CountryISOCode varchar2(2) default NULL,
	CountryName varchar2(40) default NULL,
	RegionID int default NULL
);

-- departments table creation
CREATE TABLE departments (
	DepartmentID int(11) default NULL,
	DepartmentName varchar(30) default NULL,
	ManagerID int(11) default NULL,
	LocationID int(11) default NULL
);

-- employees table creation
CREATE TABLE employees (
	EmployeeID int(11) default NULL,
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

-- job_history table creation
CREATE TABLE job_history (
	EmployeeID int(11) default NULL,
	StartDate datetime default NULL,
	EndDate datetime default NULL,
	JobCode varchar(10) default NULL,
	DepartmentID int(11) default NULL
);
  
-- jobs table creation   
CREATE TABLE jobs (
	JobCode varchar(10) default NULL,
	JobTitle varchar(35) default NULL,
	MinSalary int(11) default NULL,
	MaxSalary int(11) default NULL
);


-- location tabke creation
CREATE TABLE locations (
	LocationID int(11) default NULL,
	StreetAddress varchar(40) default NULL,
	PostalCode varchar(12) default NULL,
	City varchar(30) default NULL,
	StateProvince varchar(25) default NULL,
	CountryISOCode varchar(2) default NULL
);

-- regions table creation
CREATE TABLE regions (
	RegionID int(11) default NULL,
	RegionName varchar(25) default NULL
);