package kr.worthseeing.event.pointlog.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.event.pointlog.entity.PointLog;

public interface PointLogRepository  extends CrudRepository<PointLog, Integer>{
 
}
