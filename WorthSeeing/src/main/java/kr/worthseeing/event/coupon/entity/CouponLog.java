package kr.worthseeing.event.coupon.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponLog {

	@Id
	@GeneratedValue
	private int couponLog_seq;
	private int coupon_seq;
	private String userid;
	private int couponPrice;
	private String couponSerialNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date couponUsedDate;

}
