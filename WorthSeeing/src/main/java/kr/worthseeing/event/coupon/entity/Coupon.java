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
	private String userid;
	private int couponPrice;
	private String couponSerialNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date couponUsedDate;

	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false, updatable = false)
	private Status status;

	public void setUsers(Status status) {
		this.status = status;
		status.getCouponList().add(this);
	}
}
