package com.github.nill14.ttool.entity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Regions")
@SequenceGenerator(name = "SEQ_Regions", sequenceName = "SEQ_Regions")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_Regions")
	private int regionId;

	@NotNull
	@Column(name = "regionName")
	private String name;
	
	
	@OneToMany(mappedBy = "region")
	@MapKey(name = "isoCode")
	private Map<String, Country> countries;

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String regionName) {
		this.name = regionName;
	}

	public Map<String, Country> getCountries() {
		return countries;
	}

	public void setCountries(Map<String, Country> countries) {
		this.countries = countries;
	}
	
	
}
