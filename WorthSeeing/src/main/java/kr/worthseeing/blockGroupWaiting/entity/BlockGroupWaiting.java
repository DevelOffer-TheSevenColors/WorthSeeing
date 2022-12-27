package kr.worthseeing.blockGroupWaiting.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.status.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = { "blockList", "status" })
@NoArgsConstructor
public class BlockGroupWaiting {

	@Id
	@GeneratedValue
	private int blockGroupWaiting_seq;

	private String cImg = "https://kwangan2-worthseeing-burket.s3.eu-west-2.amazonaws.com/defaultIMG.png";

	private String linkUrl;
	private String sImg;

	@Column(columnDefinition = "number default 0")
	private int price;

	private int width = 0;
	private int height = 0;

	@Temporal(TemporalType.TIMESTAMP)
	private Date purchaseDay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date startDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date groupDate = new Date();

	private String userId;

	@OneToMany(mappedBy = "blockGroup")
	private List<Block> blockList = new ArrayList<Block>();

	@ManyToOne
	@JoinColumn(name = "status_seq", nullable = false)
	private Status status;

	public void setStatus(Status status) {
		this.status = status;
		status.getBlockGroupWaitingList().add(this);
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionDate;

}