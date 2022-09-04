package com.simplehomeinsurance.claims_management_system.entity;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@Entity
@Table(name="declined_claims")
public class DeclinedClaim {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int declinedClaimNumber;
	
	@OneToOne
	@JoinColumn(name="claim_number")
	private Claim claim;
	
	@Column(name="declined_date")
	@Temporal(TemporalType.DATE)
	private Date declinedDate;
	
	@NotNull(message="Required")
	@Column(name="reason")
	private String reason;
	
	public DeclinedClaim() {
		
	}

	public int getDeclinedClaimNumber() {
		return declinedClaimNumber;
	}

	public void setDeclinedClaimNumber(int declinedClaimNumber) {
		this.declinedClaimNumber = declinedClaimNumber;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public String getDeclinedDate() {
		return DateUtils.formatDate(declinedDate);
	}

	public void setDeclinedDate(String declinedDate) {
		try {
			this.declinedDate = DateUtils.parseDate(declinedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
