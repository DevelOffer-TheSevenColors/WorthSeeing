package kr.worthseeing.main.auction.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private int reservation_seq;
	private int auctionPrice;
	private int finishPrice;
	private int suggestPrice;
	private Date suggestDate;
	private String userId;

}
