package kr.worthseeing.blockgroup.entity;

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

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.users.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"blockList", "users"})
@NoArgsConstructor
public class BlockGroup {
	
	@Id
	@GeneratedValue
	private int blockGroup_seq;
	private String linkUrl;
	private String cImg;
	private String sImg;

	@Column(columnDefinition = "number default 0")
	private int clickCnt;

	@Column(columnDefinition = "number default 0")
	private int price;

	private Date purchaseDay;

	private Date startDate = new Date();

	private String endDate;

	private Date groupDate = new Date();
	
	private int width = 0;
	private int height = 0;
	private int minBlockSeq;
 
	private String blockNumber;
	
	@OneToMany(mappedBy = "blockGroup")
	private List<Block> blockList = new ArrayList<Block>();

//	@OneToMany(mappedBy = "blockGroup")
//	private List<Notify> notifyList = new ArrayList<Notify>();
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private Users users;

	public void setUsers(Users users) {
		this.users = users;
		users.getBlockGroupList().add(this);
	}

	public BlockGroup(int blockGroup_seq, String linkUrl, String cImg, String sImg, int avgPrice) {
		this.blockGroup_seq = blockGroup_seq;
		this.linkUrl = linkUrl;
		this.cImg = cImg;
		this.sImg = sImg;
	}
	
	public BlockGroup(String linkUrl, String cImg, String sImg, int avgPrice) {
		this.linkUrl = linkUrl;
		this.cImg = cImg;
		this.sImg = sImg;
	}
	
	
	

}