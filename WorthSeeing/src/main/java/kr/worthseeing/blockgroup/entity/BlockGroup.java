package kr.worthseeing.blockgroup.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlockGroup {

	@Id
	@GeneratedValue
	private int blockGroup_seq;
	// private String userId;
	private String linkUrl;
	private String cImg;
	private String sImg;
	private int clickCnt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date endDate;
	private int avgPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date groupDate;

	@OneToMany(mappedBy = "blockGroup", cascade = CascadeType.REMOVE)
	private List<Block> blockList = new ArrayList<Block>();

	@OneToMany(mappedBy = "blockGroup", cascade = CascadeType.REMOVE)
	private List<Refund> refundList = new ArrayList<Refund>();

	@OneToMany(mappedBy = "blockGroup", cascade = CascadeType.REMOVE)
	private List<Notify> notifyList = new ArrayList<Notify>();

	@OneToMany(mappedBy = "blockGroup", cascade = CascadeType.REMOVE)
	private List<Reservation> reservationList = new ArrayList<Reservation>();

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users users;

	public void setUsers(Users users) {
		this.users = users;
		users.getBlockGroupList().add(this);
	}

}
