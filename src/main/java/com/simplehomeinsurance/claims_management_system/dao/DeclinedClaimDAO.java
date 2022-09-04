package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.DeclinedClaim;

@Repository
public class DeclinedClaimDAO {	
	private EntityManager entityManager;
	
	@Autowired
	public DeclinedClaimDAO(EntityManager em) {
		entityManager = em;
	}

	public List<DeclinedClaim> getDeclinedClaimList() {
		Session currentSession = entityManager.unwrap(Session.class);		
		Query<DeclinedClaim> theQuery = currentSession.createQuery("from DeclinedClaim", 
																	DeclinedClaim.class);			
		return theQuery.getResultList();
	}

	public DeclinedClaim getDeclinedClaim(String declinedClaimNumber) {		
		Session currentSession = entityManager.unwrap(Session.class);		
		return currentSession.get(DeclinedClaim.class, declinedClaimNumber);
	}

	public void saveDeclinedClaim(DeclinedClaim declinedClaim) {		
		Session currentSession = entityManager.unwrap(Session.class);		
		currentSession.saveOrUpdate(declinedClaim);
	}
}
