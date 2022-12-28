package kr.worthseeing.refund.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Refund {

	@Id
	@GeneratedValue
	private int refund_seq;
	private int refundPrice;
	
	@Column(nullable = true)
	private int blockGroup_seq;
	@Column(nullable = true)
	private int blockGroupWaiting_seq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date refundDate;

}
