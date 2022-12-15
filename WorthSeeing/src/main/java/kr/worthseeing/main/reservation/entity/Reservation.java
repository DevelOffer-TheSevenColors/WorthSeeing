package kr.worthseeing.main.reservation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.auction.entity.Auction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue
	private int reservation_seq;

	private int startPrice;

	private int groupblock_seq;

	private int userCnt;

	@OneToMany(mappedBy = "reservation")
	private List<Auction> auctionList = new ArrayList<Auction>();

	@OneToMany(mappedBy = "reservation")
	private List<ReservationUserId> reservationUserIDList = new ArrayList<ReservationUserId>();

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private BlockGroup blockGroup;

	public void setBlockGroup(BlockGroup blockGroup) {
		this.blockGroup = blockGroup;
		blockGroup.getReservationList().add(this);
	}

}
