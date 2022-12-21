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

import groovy.transform.ToString;
import kr.worthseeing.main.reservation.entity.ReservationLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@ToString(excludes = "reservationLog")
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
	private int status_seq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date suggestDate;
	private String userId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservationLog_seq")
	private ReservationLog reservationLog;
	
} 
