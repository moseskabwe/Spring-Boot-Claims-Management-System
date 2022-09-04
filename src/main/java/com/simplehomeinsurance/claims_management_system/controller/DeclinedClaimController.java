package com.simplehomeinsurance.claims_management_system.controller;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.DeclinedClaim;
import com.simplehomeinsurance.claims_management_system.entity.User;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.service.DeclinedClaimService;
import com.simplehomeinsurance.claims_management_system.service.UserService;
import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@Controller
public class DeclinedClaimController {	
	@Autowired
	private DeclinedClaimService declinedClaimService;	
	@Autowired
	private ClaimService claimService;	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User findUser(Principal principal) {
		return this.userService.getUserbyUsername(principal.getName());
	}
	
	@InitBinder
	// Used to remove leading and ending whitespace from the Reason text field 
	// to ensure that it's never empty.
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/declineClaim")
	public String declineClaim(@ModelAttribute("claimNumber") String claimNumber, 
								Model model) {		
		Claim claim = claimService.getClaim(claimNumber);		
		DeclinedClaim declinedClaim = new DeclinedClaim();
		model.addAttribute("declinedClaim", declinedClaim)
			 .addAttribute("claim", claim);		
		return "decline-claim";
	}

	@PostMapping("/declineClaim")
	public String saveDeclinedClaim(@ModelAttribute("claimNumber") String claimNumber, 
									@Valid @ModelAttribute("declinedClaim") 
									DeclinedClaim declinedClaim,
									BindingResult bindingResult, Model model) {
		Claim claim = claimService.getClaim(claimNumber);
		
		System.out.println(claim.toString());
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("declinedClaim", declinedClaim)
				 .addAttribute("claim", claim);			
			return "decline-claim";	
		} else {
			claim.setDeclinedClaim(declinedClaim);			
			claim.setStatus("Declined");
			Date date = new Date();  			
			declinedClaim.setClaim(claim);			
			declinedClaim.setDeclinedDate(DateUtils.formatDate(date));
			declinedClaimService.saveDeclinedClaim(declinedClaim);
			claimService.updateClaim(claim);	
			return "redirect:/dashboard/listClaims/showClaimDetails?claimNumber=" + claimNumber;
		}
	}
}
