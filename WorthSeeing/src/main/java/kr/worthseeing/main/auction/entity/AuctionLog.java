package kr.worthseeing.main.auction.entity;

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

import kr.worthseeing.blockgroup.entity.BlockGroupLog;
import kr.worthseeing.main.reservation.entity.ReservationLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuctionLog {

	@Id
	@GeneratedValue
	private int auctionLog_seq;
	private int auction_seq;
	private int auctionPrice;
	private int finishPrice;
	private int suggestPrice;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date suggestDate;
	private String userId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ReservationLog", nullable = false)
	private ReservationLog reservationLog;
	
}
