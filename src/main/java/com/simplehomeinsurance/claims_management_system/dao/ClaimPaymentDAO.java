package com.simplehomeinsurance.claims_management_system.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;

@Repository
public class ClaimPaymentDAO {
	private EntityManager entityManager;
	
	@Autowired
	public ClaimPaymentDAO(EntityManager em) {
		entityManager = em;
	}
	
	public List<ClaimPayment> getClaimPaymentList() {
		Session currentSession = entityManager.unwrap(Session.class);	
		Query<ClaimPayment> theQuery = currentSession
				.createQuery("from ClaimPayment order by paymentDate desc", ClaimPayment.class);				
		return theQuery.getResultList();
	}

	public ClaimPayment getClaimPayment(int paymentNumber) {
		Session currentSession = entityManager.unwrap(Session.class);		
		return currentSession.get(ClaimPayment.class, paymentNumber);
	}

	public void saveClaimPayment(ClaimPayment claimPayment) {
		Session currentSession = entityManager.unwrap(Session.class);		
		currentSession.saveOrUpdate(claimPayment);
	}	
}
