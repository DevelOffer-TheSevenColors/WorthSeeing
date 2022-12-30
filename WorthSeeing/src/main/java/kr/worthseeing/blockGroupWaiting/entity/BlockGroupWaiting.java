package kr.worthseeing.blockGroupWaiting.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = { "blockList", "status" })
@NoArgsConstructor
public class BlockGroupWaiting {

	@Id
	@GeneratedValue
	private int blockGroupWaiting_seq;

	private String cImg = "https://kwangan2-worthseeing-burket.s3.eu-west-2.amazonaws.com/defaultIMG.png";

	private String linkUrl;
	private String sImg;

	@Column(columnDefinition = "number default 0")
	private int price;

	private int width = 0;
	private int height = 0;
	private int minBlockSeq;
	
	private String blockNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date startDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date groupDate = new Date();

	@OneToOne(mappedBy = "blockGroupWaiting")
	private Reservation reservation;

	@OneToMany(mappedBy = "blockGroupWaiting")
	private List<Block> blockList = new ArrayList<Block>();

	@Column(columnDefinition = "number default 0")
	private int clickCnt;

	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users users;

	public void setStatus(Status status) {
		this.status = status;
		status.getBlockGroupWaitingList().add(this);
	}

	public void setUsers(Users users) {
		this.users = users;
		users.getBlockGroupWaitingList().add(this);
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionDate;

}