package com.github.nill14.ttool.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "regions")
public class Region {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "RegionId")
	private long regionId;

	@NotNull
	@Column(name = "RegionName")
	private String name;
	
	
	@OneToMany
	@JoinColumn(name = "RegionId")
	private Collection<Country> countries;
	
	
	

	public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String regionName) {
		this.name = regionName;
	}

	public Collection<Country> getCountries() {
		return countries;
	}

	public void setCountries(Collection<Country> countries) {
		this.countries = countries;
	}
	
	
	
}
