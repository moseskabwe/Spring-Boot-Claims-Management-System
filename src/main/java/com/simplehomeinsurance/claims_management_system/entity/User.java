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
@Table(name="users")
public class User {
	
	@Id
	@Column(name="id")
	private int userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="username")
	private String username;
	
	@OneToMany(mappedBy = "adjuster", fetch = FetchType.EAGER)
	private Set<Claim> claims;
	
	public User() {
		
	}

	public int getUserId() {
		return userId;
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
	
	public String getUsername() {
		return username;
	}

	public List<Claim> getClaims() {
		if (claims == null) {
			this.claims = new HashSet<>();
		}
		return new ArrayList<Claim>(this.claims);
	}
	
	public void addClaim(Claim claim) {
		if (claims == null) {
			claims = new HashSet<>();
		}
		claims.add(claim);
		claim.setAdjuster(this);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName 
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", username=" + username 
				+ "]";
	}
}
