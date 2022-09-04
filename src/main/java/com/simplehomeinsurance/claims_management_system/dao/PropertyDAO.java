package com.simplehomeinsurance.claims_management_system.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Property;

@Repository
public class PropertyDAO {	
	private EntityManager entityManager;
	
	public PropertyDAO(EntityManager em) {
		entityManager = em;
	}
	
	public Property getProperty(String propertyNumber) {	
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Property.class, propertyNumber);
	}
}
