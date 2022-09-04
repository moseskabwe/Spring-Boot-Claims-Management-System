package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.DeclinedClaimDAO;
import com.simplehomeinsurance.claims_management_system.entity.DeclinedClaim;

@Service
public class DeclinedClaimService {	
	private DeclinedClaimDAO declinedClaimDAO;
	
	@Autowired
	public DeclinedClaimService(DeclinedClaimDAO theDeclinedClaimDAO) {
		declinedClaimDAO = theDeclinedClaimDAO;
	}
	
	@Transactional
	public List<DeclinedClaim> getDeclinedClaimList() {		
		return declinedClaimDAO.getDeclinedClaimList();
	}
	
	@Transactional
	public DeclinedClaim getDeclinedClaim(String declinedClaimNumber) {		
		return declinedClaimDAO.getDeclinedClaim(declinedClaimNumber);
	}
	
	@Transactional
	public void saveDeclinedClaim(DeclinedClaim declinedClaim) {		
		declinedClaimDAO.saveDeclinedClaim(declinedClaim);
	}
}
