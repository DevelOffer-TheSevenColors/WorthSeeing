package kr.worthseeing.main.reservation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.main.auction.entity.Auction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"auctionList", "reservationUsersList", "blockGroupWaiting"})
public class Reservation {

	@Id
	@GeneratedValue
	private int reservation_seq;

	@Column(columnDefinition = "number default 0")
	private int startPrice;
	@Column(columnDefinition = "number default 0")
	private int userCnt;

	@OneToMany(mappedBy = "reservation")
	private List<Auction> auctionList = new ArrayList<Auction>();

	@OneToMany(mappedBy = "reservation")
	private List<ReservationUsers> reservationUsersList = new ArrayList<ReservationUsers>();

	@OneToOne(orphanRemoval = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "blockGroupWaiting_seq")
	private BlockGroupWaiting blockGroupWaiting;

	public Reservation(int startPrice, int userCnt) {
		this.startPrice = startPrice;
		this.userCnt = userCnt;
	}

}
