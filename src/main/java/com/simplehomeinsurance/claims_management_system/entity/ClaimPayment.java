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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.simplehomeinsurance.claims_management_system.utils.DateUtils;

@Entity
@Table(name="claim_payment")
public class ClaimPayment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int paymentNumber;
	
	@OneToOne
	@JoinColumn(name="claim_number")
	private Claim claim;
	
	@DecimalMin(value = "1", message="Must be larger than 0")
	@Digits(integer=7, fraction=2, message="Invalid amount")
	@Column(name="payment_amount")
	private double paymentAmount;
	
	@Column(name="payment_date")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	
	@Column(name="notes")
	private String notes;
	
	public ClaimPayment() {
		
	}

	public int getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(int paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public String getPaymentDate() {
		return DateUtils.formatDate(paymentDate);
	}

	public void setPaymentDate(String paymentDate) {
		try {
			this.paymentDate = DateUtils.parseDate(paymentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "ClaimPayment [paymentNumber=" + paymentNumber + ", claim=" + claim 
				+ ", paymentAmount=" + paymentAmount + ", paymentDate=" + paymentDate + "]";
	}
}
