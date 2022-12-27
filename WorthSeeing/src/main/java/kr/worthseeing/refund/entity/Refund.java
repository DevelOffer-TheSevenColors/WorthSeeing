package kr.worthseeing.refund.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Refund {

	@Id
	@GeneratedValue
	private int refund_seq;
	private int refundPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date refundDate;

	@ManyToOne
	@JoinColumn(name = "blockGroupWaiting_seq", nullable = false)
	private BlockGroupWaiting blockGroupWaiting;

	public void setBlockGroupWaiting(BlockGroupWaiting blockGroupWaiting) {
		this.blockGroupWaiting = blockGroupWaiting;
		blockGroupWaiting.getRefundList().add(this);
	}

	

}
