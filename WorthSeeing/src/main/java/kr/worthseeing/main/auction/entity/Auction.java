package kr.worthseeing.main.auction.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import kr.worthseeing.main.reservation.entity.Reservation;
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
public class Auction {

	@Id
	@GeneratedValue
	private int auction_seq;
	private int auctionPrice;
	private int suggestPrice;

	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionStartDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionEndDate;
	
	// 경매 끝나는 날짜는 endDATE
	public Auction(Date auctionEndDate) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date()); 
		cal.add(Calendar.DATE, 3); // 시작날짜에 3일 더하기
		this.auctionEndDate= cal.getTime();
	}
	

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date suggestDate = new Date();
	// private String userId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users users;

	@Transactional
	public void setUsers(Users users) {
		this.users = users;
		users.getAuctionList().add(this);
	}

	@ManyToOne
	@JoinColumn(name = "reservation_seq", nullable = false, updatable = false)
	private Reservation reservation;

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
		reservation.getAuctionList().add(this);
	}
}
