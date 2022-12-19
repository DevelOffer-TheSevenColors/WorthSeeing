package kr.worthseeing.main.message.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Message {
	
	@Id
	@GeneratedValue
	private int message_seq;
	
	private String toUser;
	private String msg;
	private Date sendTime;
	
//	@OneToOne(mappedBy = "message")
//	private ReservationUserId reservationUserId;
	
}
