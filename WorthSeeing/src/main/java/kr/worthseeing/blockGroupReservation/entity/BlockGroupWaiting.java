package kr.worthseeing.blockGroupReservation.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "blockGroup" })
@NoArgsConstructor
@AllArgsConstructor
public class BlockGroupWaiting {

	@Id
	@GeneratedValue
	private int blockGroupReservation_seq;
	
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

	@OneToMany(mappedBy = "blockGroupWaiting")
	private List<Block> blockList = new ArrayList<Block>();




	private String userId;

	@OneToOne
	@JoinColumn(name = "blockGroupWaiting")
	private BlockGroup blockGroup;
	
	
	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false)
	private Status status;

	public void setStatus(Status status) {
		this.status = status;
		status.getBlockGroupWaitingList().add(this);
	}

}