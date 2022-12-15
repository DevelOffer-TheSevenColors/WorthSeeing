package kr.worthseeing.status.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.refund.entity.Refund;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Status {

	@Id
	@GeneratedValue
	private int status_seq;

	private String firstCode;
	private String secondCode;

	@OneToMany(mappedBy = "status")
	private List<Block> blockList = new ArrayList<Block>();

	@OneToMany(mappedBy = "status")
	private List<Refund> refundList = new ArrayList<Refund>();

	@OneToMany(mappedBy = "status")
	private List<Notify> notifyList = new ArrayList<Notify>();

	@OneToMany(mappedBy = "status")
	private List<Coupon> CouponList = new ArrayList<Coupon>();

}
