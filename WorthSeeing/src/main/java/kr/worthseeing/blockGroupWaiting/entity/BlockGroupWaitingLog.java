package kr.worthseeing.blockGroupWaiting.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.block.entity.BlockLog;
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
public class BlockGroupWaitingLog {

	@Id
	@GeneratedValue
	private int blockGroupLog_seq;
	private int blockGroup_seq;
	private String userId;
	private String linkUrl;
	private String cImg;
	private String sImg;
	private int clickCnt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date endDate;
	private int avgPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date groupDate;

	@OneToMany(mappedBy = "blockGroupWaitingLog", fetch = FetchType.EAGER)
	private List<BlockLog> blockLogList = new ArrayList<BlockLog>();

	@OneToOne(mappedBy = "blockGroupWaitingLog")
	private ReservationLog reservationLog;

}
