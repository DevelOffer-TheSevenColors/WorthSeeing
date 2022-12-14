package kr.worthseeing.blockgroup.entity;

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
public class BlockGroupLog {

	@Id
	@GeneratedValue
	private int groupBlockLog_seq;
	private int groupBlock_seq;
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
	
}
