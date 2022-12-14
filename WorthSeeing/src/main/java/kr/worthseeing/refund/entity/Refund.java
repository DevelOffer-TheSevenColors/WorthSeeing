package kr.worthseeing.refund.entity;

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
public class Refund {

	@Id
	@GeneratedValue
	private int refund_seq;
	private int groupBlock_seq;
	private int refundPrice;
	private Date refundDate;
	private int status_seq;
	
}
