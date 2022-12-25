package kr.worthseeing.log.service;

import java.util.List;

import kr.worthseeing.block.entity.BlockLog;

public interface LogService {

	public List<BlockLog> blockChartList(String block_seq);
	
	public List<Integer> saleChart(String startYear);
	
	public List<Integer> blockChart(String block_seq, String startYear);
}
