package com.simplehomeinsurance.claims_management_system.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.simplehomeinsurance.claims_management_system.entity.User;
import com.simplehomeinsurance.claims_management_system.entity.Claim;
import com.simplehomeinsurance.claims_management_system.service.UserService;
import com.simplehomeinsurance.claims_management_system.service.ClaimService;

@Controller
public class HomeController {
	@Autowired
	private ClaimService claimService;
	@Autowired
	private UserService userService;

	@GetMapping("dashboard")
	public String showDashboard(HttpServletRequest request, Model model, Principal principal) {	
		List<Claim> dashboardClaims = claimService.getDashboardClaimsList();	
		User user = userService.getUserbyUsername(principal.getName());
		
		// Get statistics to display on the dashboard.
		Long numberFire = claimService.getNumberOfFireClaims();
		Long numberDamage = claimService.getNumberOfDamageClaims();
		Long numberTheft = claimService.getNumberOfTheftClaims();
		Long numberNewClaims = claimService.getNumberOfNewClaims();
		Long numberInProgress = claimService.getNumberOfClaimsInProgress();
		Long numberFinalised = claimService.getNumberOfFinalisedClaims();
		Long numberTotal = claimService.getNumberTotalClaims();
		int finalisedAverage = (int) (Math.round(((double) numberFinalised
										/ numberTotal) * 10000.0)/100.0);			
		ArrayList<Long> stats = new ArrayList<>();
		stats.add(numberFire);
		stats.add(numberDamage);
		stats.add(numberTheft);
		stats.add(numberNewClaims);
		stats.add(numberInProgress);
		
		if (request.isUserInRole("ROLE_ADJUSTER")) {			
			List<Claim> myClaims = claimService.getMyOutstandingClaims(user.getUserId());		
			model.addAttribute("myClaims", myClaims);
		}
		model.addAttribute("dashboardClaimsList", dashboardClaims)
			 .addAttribute("stats", stats)
			 .addAttribute("finalisedAverage", finalisedAverage)
			 .addAttribute("user", user);	
		return "dashboard";
	}
}
