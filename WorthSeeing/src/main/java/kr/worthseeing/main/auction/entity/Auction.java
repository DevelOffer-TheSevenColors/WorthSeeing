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
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Column(columnDefinition = "number default 0")
	private int maxPrice;

	@Temporal(TemporalType.TIMESTAMP)
	private Date suggestDate = new Date();
	// private String userId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	@JsonIgnore
	private Users users;

	@Transactional
	public void setUsers(Users users) {
		this.users = users;
		users.getAuctionList().add(this);
	}

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "reservation_seq", nullable = false, updatable = false)
	private Reservation reservation;

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
		reservation.getAuctionList().add(this);
	}
}
