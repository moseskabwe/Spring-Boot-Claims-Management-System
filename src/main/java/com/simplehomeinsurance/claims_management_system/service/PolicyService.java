package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.PolicyDAO;
import com.simplehomeinsurance.claims_management_system.entity.Policy;

@Service
public class PolicyService {
	private PolicyDAO policyDAO;
	
	@Autowired
	public PolicyService(PolicyDAO thePolicyDAO) {
		policyDAO = thePolicyDAO;
	}
	
	@Transactional
	public Policy getPolicy(String policyNumber) {		
		return policyDAO.getPolicy(policyNumber);
	}
	
	@Transactional
	public List<Policy> getPolicies() {	
		return policyDAO.getPolicyList();
	}
}
