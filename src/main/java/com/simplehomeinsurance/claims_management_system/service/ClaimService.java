package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.ClaimDAO;
import com.simplehomeinsurance.claims_management_system.entity.Claim;

@Service
public class ClaimService {	
	private ClaimDAO claimDAO;
	
	@Autowired
	public ClaimService(ClaimDAO theClaimDAO) {
		claimDAO = theClaimDAO;
	}
	
	@Transactional
	public List<Claim> getClaimsList() {	
		return claimDAO.getClaimsList();
	}
	
	@Transactional
	public List<Claim> getMyClaims(int userId) {		
		return claimDAO.getMyClaims(userId);
	}
	
	@Transactional
	public Claim getClaim(String claimNumber) {		
		return claimDAO.getClaim(claimNumber);
	}
	
	@Transactional
	public void saveClaim(Claim theClaim) {		
		claimDAO.saveClaim(theClaim);
	}
	
	@Transactional
	public void updateClaim(Claim theClaim) {		
		claimDAO.updateClaim(theClaim);
	}
	
	@Transactional
	public List<Claim> searchClaims(String searchTerm) {		
		return claimDAO.getClaimsList();
	}

	@Transactional
	public List<Claim> getDashboardClaimsList() {		
		return claimDAO.getDashboardClaimsList();
	}
	
	@Transactional
	public Long getNumberOfFireClaims() {		
		return claimDAO.getNumberOfFireClaims();
	}
	
	@Transactional
	public Long getNumberOfDamageClaims() {		
		return claimDAO.getNumberOfDamageClaims();
	}
	
	@Transactional
	public Long getNumberOfTheftClaims() {
		return claimDAO.getNumberOfTheftClaims();
	}
	
	@Transactional
	public Long getNumberOfNewClaims() {
		return claimDAO.getNumberOfNewClaims();
	}
	
	@Transactional
	public Long getNumberOfClaimsInProgress() {
		return claimDAO.getNumberOfClaimsInProgress();
	}
	
	@Transactional
	public Long getNumberOfFinalisedClaims() {
		return claimDAO.getNumberOfFinalisedClaims();
	}
	
	@Transactional
	public Long getNumberTotalClaims() {
		return claimDAO.getNumberTotalClaims();
	}
	
	@Transactional
	public List<Claim> getMyOutstandingClaims(int userId) {		
		return claimDAO.getMyOutstandingClaims(userId);
	}
}
