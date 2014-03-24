package com.github.nill14.ttool.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Locations")
@SequenceGenerator(name = "SEQ_Locations", sequenceName = "SEQ_Locations")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Locations")
	private int locationId;
	
	@Column
	private String streetAddress;
	
	@Column
	private String postalCode;
	
	@Column
	private String city;
	
	@Column
	private String stateProvince;
	
	@ManyToOne
	@JoinColumn(name = "countryIsoCode", foreignKey = @ForeignKey(name = "FK_Location_CountryIsoCode"))
	private Country country;

	@OneToMany(mappedBy = "location")
	private List<Department> departments;
	
	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}
	


	
	
	
}
