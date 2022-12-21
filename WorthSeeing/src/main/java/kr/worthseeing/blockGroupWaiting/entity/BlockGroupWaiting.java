package kr.worthseeing.blockGroupWaiting.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.status.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "blockGroup","status" })
@NoArgsConstructor
@AllArgsConstructor
public class BlockGroupWaiting {

	@Id
	@GeneratedValue
	private int blockGroupWaiting_seq;
	
	private String linkUrl;
	private String cImg;
	private String sImg;

	

	@Column(columnDefinition = "number default 0")
	private int price;
	

	@Column(columnDefinition = "number default 0")
	private int purchaseDay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date startDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date endDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date groupDate = new Date();


	private String userId;

	@OneToOne
	@JoinColumn(name = "blockGroup_seq")
	private BlockGroup blockGroup;
	
	
	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false)
	private Status status;

	public void setStatus(Status status) {
		this.status = status;
		status.getBlockGroupWaitingList().add(this);
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionDate= new Date();

}