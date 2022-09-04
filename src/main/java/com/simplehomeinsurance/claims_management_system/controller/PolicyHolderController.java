package com.simplehomeinsurance.claims_management_system.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.Policy;
import com.simplehomeinsurance.claims_management_system.entity.PolicyHolder;
import com.simplehomeinsurance.claims_management_system.entity.User;
import com.simplehomeinsurance.claims_management_system.service.PolicyHolderService;
import com.simplehomeinsurance.claims_management_system.service.UserService;

@Controller
public class PolicyHolderController {
	@Autowired
	private PolicyHolderService policyHolderService;	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User findUser(Principal principal) {
		return this.userService.getUserbyUsername(principal.getName());
	}
	
	@GetMapping("dashboard/searchPolicyholders")
	public String searchPolicyholders(Model model) {		
		return "search-policyholders";
	}
	
	@RequestMapping("dashboard/policyholders")
	public String showPolicyholdersResults(@RequestParam("searchTerm") String searchTerm,
										   Model theModel) {	
		List<PolicyHolder> policyHolderList = policyHolderService.searchPolicyHolders(searchTerm);
		theModel.addAttribute("policyholderList", policyHolderList);		
		return "policyholders-results";
	}
	
	@GetMapping("/policyholders/showPolicyholderDetails")
	public String showPolicyholderDetails(@ModelAttribute("policyHolderNumber") 
										  String policyholderNumber, 
										  Model theModel) {
		PolicyHolder policyholder = policyHolderService.getPolicyHolder(policyholderNumber);
		List<Claim> claimList = policyholder.getClaims();
		theModel.addAttribute("policyholder", policyholder)
				.addAttribute("claimList", claimList);
		return "policyholder-details";
	}
	
	@GetMapping("/policyholders/showPolicyDetails")
	public String showPolicyDetails(@ModelAttribute("policyHolderNumber") 
									String policyholderNumber,
									Model theModel) {
		PolicyHolder policyholder = policyHolderService.getPolicyHolder(policyholderNumber);
		List<Policy> policyList = policyholder.getPolicies();
		theModel.addAttribute("policyholder", policyholder)
				.addAttribute("policyList", policyList);		
		return "policies";
	}
}
