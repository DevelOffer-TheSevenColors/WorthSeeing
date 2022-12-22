package kr.worthseeing.users.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.notify.entity.Notify;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "auctionList", "blockGroupList", "notifyList", "couponList", "reservationUsersList" })
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	@Id
	private String userId;

	private String userPw;
	private String name;
	private String nickName;
	private String address;
	private String detailAddress;
	private String email;
	private String tel;
	@Enumerated(EnumType.STRING)
	private Role role;

	private String adminYn = "no";
	private String blackYn = "no";
	
	private String dailyClickCheck = "no";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "date default sysdate")
	private Date joindate;

	@Column(columnDefinition = "number default 0")
	private int point;
	@Column(columnDefinition = "number default 0")
	private int reservationCnt;
	@Column(columnDefinition = "number default 0")
	private int finishedAuctionCnt;
	@Column(columnDefinition = "number default 0")
	private int totalMoney;

	@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private List<Auction> auctionList = new ArrayList<Auction>();

	@OneToMany(mappedBy = "users")
	private List<BlockGroup> blockGroupList = new ArrayList<BlockGroup>();
 
	@OneToMany(mappedBy = "users" )
	private List<Notify> notifyList = new ArrayList<Notify>();

	@OneToMany(mappedBy = "users")
	private List<ReservationUsers> reservationUsersList = new ArrayList<ReservationUsers>();
	
	@OneToMany(mappedBy = "users")
	private List<Coupon> couponList = new ArrayList<Coupon>();

	public Users(String userId, String userPw, String name, String nickName, String address, String detailAddress,
			String email, String tel) {
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.nickName = nickName;
		this.address = address;
		this.detailAddress = detailAddress;
		this.email = email;
		this.tel = tel;
	}

}
