package kr.worthseeing.main.reservation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class ReservationUserId {

	@Id
	@GeneratedValue
	private int ReservationUserId_seq;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date reservationUserIdDate;

	@ManyToOne
	@JoinColumn(name = "reservation_seq", nullable = false)
	private Reservation reservation;

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
		reservation.getReservationUserIDList().add(this);
	}

	@OneToOne
	@JoinColumn(name = "userid", nullable = false)
	private Users users;

	public void setUsers(Users users) {
		this.users = users;
		users.setReservationUserId(this);
	}

}
