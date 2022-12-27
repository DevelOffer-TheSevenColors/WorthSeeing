package kr.worthseeing.blockgroup.entity;

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
import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
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
@ToString(exclude = {"blockList", "refundList", "reservation", "users", "status","blockGroupWaiting"})
@NoArgsConstructor
public class BlockGroup {
	
	@Id
	@GeneratedValue
	private int blockGroup_seq;
	private String linkUrl;
	private String cImg;
	private String sImg;

	@Column(columnDefinition = "number default 0")
	private int clickCnt;

	@Column(columnDefinition = "number default 0")
	private int price;
	private int avgPrice;

	private Date purchaseDay;

	private Date startDate = new Date();

	private String endDate;

	private Date groupDate = new Date();
	
	private int width = 0;
	private int height = 0;
 
	@OneToMany(mappedBy = "blockGroup")
	private List<Block> blockList = new ArrayList<Block>();

	@OneToMany(mappedBy = "blockGroup")
	private List<Refund> refundList = new ArrayList<Refund>();

//	@OneToMany(mappedBy = "blockGroup")
//	private List<Notify> notifyList = new ArrayList<Notify>();

	@OneToOne(mappedBy = "blockGroup" )
	private Reservation reservation;
//	private List<Reservation> reservationList = new ArrayList<Reservation>();
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users users;

	public void setUsers(Users users) {
		this.users = users;
		users.getBlockGroupList().add(this);
	}

	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false)
	private Status status;

	public void setStatus(Status status) {
		this.status = status;
		status.getBlockGroupList().add(this);
	}

	public BlockGroup(int blockGroup_seq, String linkUrl, String cImg, String sImg, int avgPrice) {
		this.blockGroup_seq = blockGroup_seq;
		this.linkUrl = linkUrl;
		this.cImg = cImg;
		this.sImg = sImg;
		this.avgPrice = avgPrice;
	}
	
	public BlockGroup(String linkUrl, String cImg, String sImg, int avgPrice) {
		this.linkUrl = linkUrl;
		this.cImg = cImg;
		this.sImg = sImg;
		this.avgPrice = avgPrice;
	}
	
	
	

}