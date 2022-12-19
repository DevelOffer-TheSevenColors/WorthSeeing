package kr.worthseeing.status.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.status.entity.Status;

public interface StatusRepository  extends CrudRepository<Status, Integer>{
 
}
