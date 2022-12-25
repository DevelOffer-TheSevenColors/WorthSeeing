package kr.worthseeing.main.reservation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.auction.entity.Auction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"auctionList", "reservationUsersList", "blockGroup"})
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

	@OneToOne
	@JoinColumn(name = "blockGroup_seq")
	private BlockGroup blockGroup;

	public Reservation(int startPrice, int userCnt) {
		this.startPrice = startPrice;
		this.userCnt = userCnt;
	}

}
