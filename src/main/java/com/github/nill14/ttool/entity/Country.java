package com.github.nill14.ttool.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Countries")
public class Country {
	
	@Id
	@Column(name = "countryIsoCode")
	private String isoCode;
	
	@Column(name = "countryName")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "regionId", foreignKey = @ForeignKey(name = "FK_Country_RegionId"))
	private Region region;
	
	
	@OneToMany(mappedBy = "country")
	private List<Location> locations;
	
	
	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}


	
	
	
}
