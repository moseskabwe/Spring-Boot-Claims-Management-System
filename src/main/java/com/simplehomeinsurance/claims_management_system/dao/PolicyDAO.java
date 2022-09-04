package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.Policy;

@Repository
public class PolicyDAO {
	private EntityManager entityManager;
	
	public PolicyDAO(EntityManager em) {
		entityManager = em;
	}
	
	public Policy getPolicy(String policyNumber) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.get(Policy.class, policyNumber);
	}
	
	public List<Policy> getPolicyList() {
		Session currentSession = entityManager.unwrap(Session.class);		
		Query<Policy> theQuery = currentSession.createQuery("from Policy", Policy.class);		
		return theQuery.getResultList();
	}
}
