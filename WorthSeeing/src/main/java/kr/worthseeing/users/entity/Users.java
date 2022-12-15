package kr.worthseeing.users.entity;

import java.util.ArrayList;
import java.util.List;

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
	@GeneratedValue
	private String userId;

	private String nickName;
	private String userPw;
	private String adress;
	private String detatiladress;
	private String email;

	private String adminyn;
	private String blackyn;

	private String joindate;
	private String tel;
	private String name;
	private int point;
	private int reservationCnt;
	private int finishedAuctionCnt;
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

}
