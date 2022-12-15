package kr.worthseeing.event.coupon.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

	@Id
	@GeneratedValue
	private int coupon_seq;
	private int couponPrice;
	private String couponSerialNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date couponUsedDate;

	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false, updatable = false)
	private Status status;

	public void setStatus(Status status) {
		this.status = status;
		status.getCouponList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false, updatable = false)
	private Users users;
	
	public void setUsers(Users users) {
		this.users = users;
		users.getCouponList().add(this);
	}
	
}
