package kr.worthseeing.main.reservation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUserId {
	

	@Id
	@GeneratedValue
	private int ReservationUserId_seq;
	
	private int	reservation_seq;
	private int userid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date reservationUserIdDate;

	

}
