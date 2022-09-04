package com.simplehomeinsurance.claims_management_system.entity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.simplehomeinsurance.claims_management_system.utils.DateUtils;
import com.simplehomeinsurance.claims_management_system.utils.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="claim")
public class Claim {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claims_sequence")
    @GenericGenerator(
        name = "claims_sequence",
        strategy = "com.simplehomeinsurance.claims_management_system.utils"
        		 + ".StringPrefixedSequenceIdGenerator",
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "49"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, 
            			value = "CM"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, 
            			value = "%06d") })
	@Column(name="claim_number")
	private String claimNumber;
	
	@ManyToOne
	@JoinColumn(name="adjuster_number")
	private User adjuster;
	
	@ManyToOne
	@JoinColumn(name="policyholder_number")
	private PolicyHolder policyHolder;
	
	@ManyToOne
	@JoinColumn(name="policy_number")
	private Policy policy;
	
	@Column(name="loss_type")
	private String lossType;
	
	@NotNull(message="Required")
	@Column(name="incident_date")
	@Temporal(TemporalType.DATE)
	private Date incidentDate;
	
	@NotNull(message="Required")
	@Column(name="filing_date")
	@Temporal(TemporalType.DATE)
	private Date filingDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="notes")
	private String notes;
	
	@OneToOne(mappedBy = "claim")
	private DeclinedClaim declinedClaim;
	
	@OneToMany(mappedBy = "claim", fetch = FetchType.EAGER)
	private Set<ClaimPayment> payments;
	
	public Claim() {	
		
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	
	public User getAdjuster() {
		return adjuster;
	}

	public void setAdjuster(User user) {
		this.adjuster = user;
	}

	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}

	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}
	
	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getIncidentDate() {
		return DateUtils.formatDate(incidentDate);
	}

	public void setIncidentDate(String incidentDate) {
		try {
			this.incidentDate = DateUtils.parseDate(incidentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getFilingDate() {
		return DateUtils.formatDate(filingDate);
	}

	public void setFilingDate(String filingDate) {
		try {
			this.filingDate = DateUtils.parseDate(filingDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getLossType() {
		return lossType;
	}

	public void setLossType(String lossType) {
		this.lossType = lossType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public DeclinedClaim getDeclinedClaim() {
		return declinedClaim;
	}

	public void setDeclinedClaim(DeclinedClaim declinedClaim) {
		this.declinedClaim = declinedClaim;
	}

	public List<ClaimPayment> getPayments() {
		if (payments == null) {
			this.payments = new HashSet<>();
		}
		return new ArrayList<>(this.payments);
	}
	
	public void addPayment(ClaimPayment payment) {
		if (payments == null) {
			payments = new HashSet<>();
		}
		payments.add(payment);
		payment.setClaim(this);
	}

	@Override
	public String toString() {
		return "Claim [claimNumber=" + claimNumber + ", adjuster=" + adjuster + ", policyHolder=" 
				+ policyHolder + ", lossType=" + lossType + ", incidentDate=" + incidentDate 
				+ ", filingDate=" + filingDate + ", status=" + status + ", notes=" + notes + "]";
	}	
}
