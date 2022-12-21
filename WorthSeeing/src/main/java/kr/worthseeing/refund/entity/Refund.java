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

import kr.worthseeing.blockgroup.entity.BlockGroup;
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
public class Refund {

	@Id
	@GeneratedValue
	private int refund_seq;
	private int refundPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date refundDate;

	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false, updatable = false)
	private Status status;

	public void setStatus(Status status) {
		this.status = status;
		status.getRefundList().add(this);
	}

	@ManyToOne
	@JoinColumn(name = "blockGroup_seq", nullable = false)
	private BlockGroup blockGroup;

	public void setBlockGroup(BlockGroup blockGroup) {
		this.blockGroup = blockGroup;
		blockGroup.getRefundList().add(this);
	}

	

}
