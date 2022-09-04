package com.simplehomeinsurance.claims_management_system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplehomeinsurance.claims_management_system.dao.PolicyHolderDAO;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;

@Service
public class PolicyHolderService {	
	private PolicyHolderDAO policyHolderDAO;
	
	@Autowired
	public PolicyHolderService(PolicyHolderDAO thePolicyHolderDAO) {
		policyHolderDAO = thePolicyHolderDAO;
	}
	
	@Transactional
	public List<PolicyHolder> getPolicyHolderList() {		
		return policyHolderDAO.getPolicyHolderList();
	}
	
	@Transactional
	public PolicyHolder getPolicyHolder(String policyHolderNumber) {		
		return policyHolderDAO.getPolicyHolder(policyHolderNumber);
	}
	
	@Transactional
	public void savePolicyHolder(PolicyHolder policyHolder) {	
		policyHolderDAO.savePolicyHolder(policyHolder);
	}
	
	@Transactional
	public List<PolicyHolder> searchPolicyHolders(String searchTerm) {
		return policyHolderDAO.searchPolicyHolders(searchTerm);
	}
}
