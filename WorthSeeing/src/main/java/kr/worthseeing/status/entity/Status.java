package kr.worthseeing.status.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.refund.entity.Refund;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"blockList", "refundList", "notifyList", "couponList"})
@AllArgsConstructor
@NoArgsConstructor
public class Status {

	public Status(String firstCode, String secondCode ) {
		this.firstCode=firstCode;
		this.secondCode=secondCode;
		
	}
	
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
	private List<Coupon> couponList = new ArrayList<Coupon>();

}
