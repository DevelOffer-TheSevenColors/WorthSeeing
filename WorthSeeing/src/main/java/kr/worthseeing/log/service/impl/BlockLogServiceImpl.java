package kr.worthseeing.log.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.worthseeing.block.entity.BlockLog;
import kr.worthseeing.block.repository.BlockLogRepository;
import kr.worthseeing.log.service.LogService;

@Service
public class BlockLogServiceImpl implements LogService {

	@Autowired
	private BlockLogRepository blockLogRepo;

	@Override
	public List<BlockLog> blockChartList(String block_seq) {
		return blockLogRepo.findBlock(Integer.parseInt(block_seq));
	}

	@Override
	public List<Integer> saleChart(String startYear) {
		int[] monthPrice = new int[12];

		List<Integer> chartPrice = new ArrayList<Integer>();

		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

		List<BlockLog> blockLogList = (List<BlockLog>) blockLogRepo.findAll();

		for (BlockLog block : blockLogList) {

			for (int i = 1; i <= 12; i++) {
				String i_str = String.valueOf(i);

				if (block.getEndDate() != null && format.format(block.getEndDate()) != null) {
					if (i <= 9) {
						i_str = "0" + i;
					}
					if (format.format(block.getEndDate()).equals(startYear + i_str)) {
						monthPrice[i - 1] += block.getBlockPrice();
					}
				}
			}

		}

		for (int i = 0; i <= 3; i++) {
			chartPrice.add(monthPrice[0 + 3 * i] + monthPrice[1 + 3 * i] + monthPrice[2 + 3 * i]);
		}

		return chartPrice;
	}

	@Override
	public List<Integer> blockChart(String block_seq, String startYear) {
		int[] monthPrice = new int[12];

		List<BlockLog> blockLogList = blockLogRepo.findBlock(Integer.parseInt(block_seq));
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		
		List<BlockLog> blockChartList = blockChartList(block_seq);
		
		if (!blockLogList.isEmpty()) {

			for (BlockLog blockLog : blockChartList) {

				for (int i = 1; i <= 12; i++) {

					String i_str = String.valueOf(i);

					if (blockLog.getEndDate() != null && format.format(blockLog.getEndDate()) != null) {
						if (i <= 9) {
							i_str = "0" + i;
						}
						if (format.format(blockLog.getEndDate()).equals(startYear + i_str)) {
							monthPrice[i - 1] += blockLog.getBlockPrice();
						}
					}
				}
			}
		}
		
		List<Integer> monthPriceList = new ArrayList<Integer>();
		
		for (int price : monthPrice) {
			monthPriceList.add(price);
		}

		return monthPriceList;
	}
}
