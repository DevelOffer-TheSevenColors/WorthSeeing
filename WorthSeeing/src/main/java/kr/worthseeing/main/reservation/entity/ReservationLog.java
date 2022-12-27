package kr.worthseeing.main.reservation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaitingLog;
import kr.worthseeing.main.auction.entity.AuctionLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = {"member","replyList","fileList"})
public class ReservationLog {

	@Id
	@GeneratedValue
	private int reservationLog_seq;
	private int reservation_seq;
	private int startPrice;
	
	
	private String useId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date reservationTime;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blockGroupLogWaiting_seq")
	private BlockGroupWaitingLog blockGroupWaitingLog;

	@OneToOne(mappedBy = "reservationLog")
	private AuctionLog auctionLog;

}