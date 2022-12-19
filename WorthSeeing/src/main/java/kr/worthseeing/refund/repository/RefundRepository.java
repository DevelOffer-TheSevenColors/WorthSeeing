package kr.worthseeing.refund.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.refund.entity.Refund;

public interface RefundRepository extends CrudRepository<Refund, Integer> {

}
