package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.ClaimPaymentDAO;
import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;

@Service
public class ClaimPaymentService {	
	private ClaimPaymentDAO claimPaymentDAO;
	
	@Autowired
	public ClaimPaymentService(ClaimPaymentDAO theClaimPaymentDAO) {
		claimPaymentDAO = theClaimPaymentDAO;
	}
	
	@Transactional
	public List<ClaimPayment> getClaimPaymentList(){		
		return claimPaymentDAO.getClaimPaymentList();
	}
	
	@Transactional
	public ClaimPayment getClaimPayment(int paymentNumber){
		return claimPaymentDAO.getClaimPayment(paymentNumber);
	}
	
	@Transactional
	public void saveClaimPayment(ClaimPayment claimPayment) {
		claimPaymentDAO.saveClaimPayment(claimPayment);
	}	
}
