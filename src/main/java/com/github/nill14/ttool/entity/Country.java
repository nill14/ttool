package com.github.nill14.ttool.entity;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
	
	@Id
	@Column(name = "CountryIsoCode")
	private String isoCode;
	
	@Column(name = "CountryName")
	private String name;
	
//	@Column(name = "RegionId")
//	private Integer regionId;

	@OneToOne
	@JoinColumn(name = "RegionId")
	private Region region;
	
	
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


	
	
	
}
