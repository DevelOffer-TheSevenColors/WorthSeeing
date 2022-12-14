package kr.worthseeing.blockgroup.entity;

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
	private Date startDate;
	private Date endDate;
	private int avgPrice;
	private Date groupDate;
	
}
