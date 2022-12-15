package kr.worthseeing.event.pointlog.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.pointlog.entity.PointLog;

public interface PoingLogRepository  extends CrudRepository<PointLog, Integer>{
 
}
