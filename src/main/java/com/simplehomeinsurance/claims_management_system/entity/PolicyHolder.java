package com.simplehomeinsurance.claims_management_system.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="policyholder")
public class PolicyHolder {
	
	@Id
	@Column(name="id")
	private String policyHolderNumber;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="address")
	private String address;
	
	@OneToMany(mappedBy = "policyHolder", fetch = FetchType.EAGER)
	private Set<Policy> policies;
	
	@OneToMany(mappedBy = "policyHolder", fetch = FetchType.EAGER)
	private Set<Claim> claims;
	
	public PolicyHolder() {
		
	}

	public String getPolicyHolderNumber() {
		return policyHolderNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public List<Policy> getPolicies() {
		if (this.policies == null) {
            this.policies = new HashSet<>();
        }
		return new ArrayList<>(this.policies);
	}

	public List<Claim> getClaims() {
		if (this.claims == null) {
            this.claims = new HashSet<>();
        }
		return new ArrayList<>(this.claims);
	}
	
	public void addClaim(Claim claim) {
		if (claims == null) {
			claims = new HashSet<>();
		}
		claims.add(claim);		
		claim.setPolicyHolder(this);
	}
}
