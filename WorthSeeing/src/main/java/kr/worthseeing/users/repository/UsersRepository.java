package kr.worthseeing.users.repository;

import org.springframework.data.repository.CrudRepository;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.blockgroup.entity.BlockGroup;
import kr.worthseeing.event.coupon.entity.Coupon;
import kr.worthseeing.event.pointlog.entity.PointLog;
import kr.worthseeing.main.auction.entity.Auction;
import kr.worthseeing.main.auction.entity.AuctionLog;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUserId;
import kr.worthseeing.notify.entity.Notify;
import kr.worthseeing.refund.entity.Refund;
import kr.worthseeing.status.entity.Status;
import kr.worthseeing.users.entity.Users;

public interface UsersRepository  extends CrudRepository<Users, String>{
 
}
