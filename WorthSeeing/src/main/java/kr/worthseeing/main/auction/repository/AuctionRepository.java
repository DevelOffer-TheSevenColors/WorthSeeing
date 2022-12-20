package kr.worthseeing.main.auction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.main.auction.entity.Auction;

public interface AuctionRepository  extends CrudRepository<Auction, Integer>,
	QuerydslPredicateExecutor<BlockGroup>  {
	
	
	@Query("select a from Auction a where reservation_seq = ?1")
	List<Auction> findByAuction(int reservation_seq);
	
}
