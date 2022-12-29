package kr.worthseeing.block.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.worthseeing.blockGroupWaiting.entity.BlockGroupWaiting;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.status.entity.Status;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"blockGroup", "status", "blockGroupWaiting"})
@NoArgsConstructor
public class Block {

	@Id
	private int block_seq;
	
	@Column(columnDefinition = "number(10, 0) default 0")
	private int blockPrice;
	
	private int xLocation;
	
	private int yLocation;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "status_seq", nullable = false)
	private Status status;

	public void setStatus(Status status) {
		this.status = status;
		status.getBlockList().add(this);
	}
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "blockGroup_seq", nullable = false)
	private BlockGroup blockGroup;

	public void setBlockGroup(BlockGroup blockGroup) {
		this.blockGroup = blockGroup;
		blockGroup.getBlockList().add(this);
	}
	
	@ManyToOne
	@JoinColumn(name = "blockGroupWaiting_seq", nullable = false)
	@JsonIgnore
	private BlockGroupWaiting blockGroupWaiting;

	public void setBlockWaitingGroup(BlockGroupWaiting blockGroupWaiting) {
		this.blockGroupWaiting = blockGroupWaiting;
		blockGroupWaiting.getBlockList().add(this);
	}
	
}
