package kr.worthseeing.main.auction.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	//private int reservation_seq;
	private int auctionPrice;
	private int finishPrice;
	private int suggestPrice;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date suggestDate;
	//private String userId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false, updatable = false)
	private Users users;

	public void setUser(Users users) {
		this.users = users;
		users.getUsersList().add(this);
		
		
	@ManyToOne
	@JoinColumn(name = "reservation_seq", nullable = false, updatable = false)
	private Reservation reservation;

	public void setReservation(Reservation reservation) {
			this.reservation = reservation;
			reservation.getAuctionList().add(this);
}
