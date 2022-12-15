package kr.worthseeing.block.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kr.worthseeing.blockgroup.entity.BlockGroupLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlockLog {

	@Id
	@GeneratedValue
	private int blockLog_seq;
	private int block_seq;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blockGroupLog_seq", nullable = false)
	private BlockGroupLog blockGroupLog;
	private int soldOutCnt;

	public void setBlockGroupLog(BlockGroupLog blockGroupLog) {
		this.blockGroupLog = blockGroupLog;
		blockGroupLog.getBlockLogList().add(this);
	}

}
