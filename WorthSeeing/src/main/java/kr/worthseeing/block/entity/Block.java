package kr.worthseeing.block.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.worthseeing.blockGroupReservation.entity.BlockGroupReservaton;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.status.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"blockGroup"})
@NoArgsConstructor
@AllArgsConstructor
public class Block {

	@Id
	private int Block_seq;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, columnDefinition = "date default sysdate")
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false, columnDefinition = "date default sysdate")
	private Date endDate;

	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "blockGroup_seq", nullable = false)
	private BlockGroup blockGroup;

	public void setBlockGroup(BlockGroup blockGroup) {
		this.blockGroup = blockGroup;
		blockGroup.getBlockList().add(this);
	}

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "blockGroupReservation_seq", nullable = false)
	private BlockGroupReservaton blockGroupReservation;
	
	public void setBlockGroupReservation(BlockGroupReservaton blockGroupReservation) {
		this.blockGroupReservation = blockGroupReservation;
		blockGroupReservation.getBlockList().add(this);
	}

}
