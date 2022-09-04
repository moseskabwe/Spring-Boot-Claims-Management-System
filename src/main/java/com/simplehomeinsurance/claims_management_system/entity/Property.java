package com.simplehomeinsurance.claims_management_system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="insured_property")
public class Property {
	
	@Id
	@Column(name="property_number")
	private String propertyNumber;
	
	@Column(name="property_address")
	private String propertyAddress;
	
	public Property () {
	
	}

	public String getPropertyNumber() {
		return propertyNumber;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyNumber(String propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
}
