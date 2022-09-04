package com.simplehomeinsurance.claims_management_system.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.entity.ClaimPayment;
import com.simplehomeinsurance.claims_management_system.entity.User;
import com.simplehomeinsurance.claims_management_system.service.ClaimPaymentService;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;
import com.simplehomeinsurance.claims_management_system.service.UserService;
import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@Controller
@RequestMapping("dashboard")
public class ClaimPaymentController {	
	
	private ClaimPaymentService claimPaymentService;	
	@Autowired
	private ClaimService claimService;	
	@Autowired
	private UserService userService;
	
	@Autowired
	public ClaimPaymentController(ClaimPaymentService theClaimPaymentService) {
		claimPaymentService = theClaimPaymentService;
	}
	
	@ModelAttribute("user")
	public User findUser(Principal principal) {
		return this.userService.getUserbyUsername(principal.getName());
	}
	
	@GetMapping("/showPayments")
	public String showPayments(Model theModel) {	
		List<ClaimPayment> claimPaymentList = claimPaymentService.getClaimPaymentList();	
		theModel.addAttribute("claimPayments", claimPaymentList);	
		return "payments";	
	}

	@GetMapping("/finaliseClaim")
	public String finaliseClaim(@ModelAttribute("claimNumber") String claimNumber, 
								Model model) {	
		Claim claim = claimService.getClaim(claimNumber);	
		ClaimPayment payment = new ClaimPayment();	
		model.addAttribute("payment", payment)
			 .addAttribute("claim", claim);	
		return "finalise-claim";
	}

	@PostMapping("/finaliseClaim")
	public String makePayment(@ModelAttribute("claimNumber") String claimNumber, 
							  @Valid @ModelAttribute("payment") ClaimPayment payment,
							  BindingResult bindingResult, Model model) {
		Claim claim = claimService.getClaim(claimNumber);
		if (bindingResult.hasErrors()) {
			model.addAttribute("payment", payment)
				 .addAttribute("claim", claim);
			return "finalise-claim";
		} else {
			claim.addPayment(payment);
			claim.setStatus("Finalised");
			claimService.updateClaim(claim);
			Date date = new Date();
			payment.setClaim(claim);
			payment.setPaymentDate(DateUtils.formatDate(date));
			claimPaymentService.saveClaimPayment(payment);
			return "redirect:/dashboard/listClaims/showClaimDetails?claimNumber=" + claimNumber;
		}
	}
}
