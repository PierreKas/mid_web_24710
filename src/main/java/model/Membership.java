package model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Membership")
public class Membership {
    @Column(name = "expiring_time")
    private Date expiring_time;
    
    @Column(name = "membership_code")
    private String membership_code;
    
    @Id
    @Column(name = "membership_id")
    private UUID membership_id=UUID.randomUUID();
    
    @ManyToOne
    @JoinColumn(name = "membership_type_id")
    private membership_type membershipType;
    
    public Membership(Date expiring_time, String membership_code, UUID membership_id, membership_type membershipType,
			Status status, User reader, Date registration_date) {
		super();
		this.expiring_time = expiring_time;
		this.membership_code = membership_code;
		this.membership_id = membership_id;
		this.membershipType = membershipType;
		this.status = status;
		this.reader = reader;
		this.registration_date = registration_date;
	}

	public Date getExpiring_time() {
		return expiring_time;
	}

	public void setExpiring_time(Date expiring_time) {
		this.expiring_time = expiring_time;
	}

	public String getMembership_code() {
		return membership_code;
	}

	public void setMembership_code(String membership_code) {
		this.membership_code = membership_code;
	}

	public UUID getMembership_id() {
		return membership_id;
	}

	public void setMembership_id(UUID membership_id) {
		this.membership_id = membership_id;
	}

	public membership_type getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(membership_type membershipType) {
		this.membershipType = membershipType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getReader() {
		return reader;
	}

	public void setReader(User reader) {
		this.reader = reader;
	}

	public Date getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}

	@Enumerated(EnumType.STRING)
    @Column(name = "membership_status")
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private User reader;
    
    @Column(name = "registration_date")
    private Date registration_date;
}
