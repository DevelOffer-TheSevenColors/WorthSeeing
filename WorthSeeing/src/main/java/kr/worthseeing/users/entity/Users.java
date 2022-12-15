package kr.worthseeing.users.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.reservation.entity.ReservationUserId;
import kr.worthseeing.notify.entity.Notify;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

	@Column(columnDefinition = "varchar2(10) default 'no'")
	private String adminyn;
	@Column(columnDefinition = "varchar2(10) default 'no'")
	private String blackyn;

	@Column(columnDefinition = "date default sysdate")
	private String joindate;
	
	@Column(columnDefinition = "number default 0")
	private int point;
	@Column(columnDefinition = "number default 0")
	private int reservationCnt;
	@Column(columnDefinition = "number default 0")
	private int finishedAuctionCnt;
	@Column(columnDefinition = "number default 0")
	private int totalMoney;

	@OneToMany(mappedBy = "users")
	private List<Auction> auctionList = new ArrayList<Auction>();

	@OneToMany(mappedBy = "users")
	private List<BlockGroup> blockGroupList = new ArrayList<BlockGroup>();

	@OneToMany(mappedBy = "users")
	private List<Notify> notifyList = new ArrayList<Notify>();

	@OneToOne(mappedBy = "users")
	private ReservationUserId reservationUserId;

	@OneToMany(mappedBy = "users")
	private List<Coupon> couponList= new ArrayList<Coupon>();
	
	public Users(String userId, String userPw, String name, String nickName, String address, String detailAddress, String email, String tel) {
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
