package com.simplehomeinsurance.claims_management_system.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.PropertyDAO;
import com.simplehomeinsurance.claims_management_system.entity.Property;

@Service
public class PropertyService {
	@Autowired
	private PropertyDAO propertyDAO;
	
	public PropertyService(PropertyDAO thePropertyDAO) {
		propertyDAO = thePropertyDAO;
	}
	
	@Transactional
	public Property getProperty(String propertyNumber) {		
		return propertyDAO.getProperty(propertyNumber);
	}
}
