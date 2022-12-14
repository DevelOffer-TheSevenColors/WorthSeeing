package kr.worthseeing.event.coupon.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private Date couponUsedDate;
	
}
