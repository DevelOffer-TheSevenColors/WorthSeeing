package kr.worthseeing.main.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.worthseeing.block.entity.Block;
import kr.worthseeing.block.service.BlockService;
import kr.worthseeing.blockgroup.service.BlockGroupService;
import kr.worthseeing.main.auction.service.AuctionService;
import kr.worthseeing.main.reservation.entity.Reservation;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.main.reservation.service.ReservationService;
import kr.worthseeing.message.dto.MessageDTO;
import kr.worthseeing.security.config.SecurityUser;

@Controller
@RequestMapping("/reservation")
public class reservationController {

   @Autowired
   private ReservationService reservationservice;

   @Autowired
   private BlockService blockService;

   @Autowired
   private AuctionService auctionService;
   
   @Autowired
   private BlockGroupService blockGroupService;
   
   // 예약가능 목록 띄우기
   @RequestMapping("/reservationList")
   private String selectauctonList(Model model, Reservation reservation, @PageableDefault Pageable pageable) {
      model.addAttribute("reservationList", reservationservice.selectReservation(pageable));
      model.addAttribute("reservationStart",reservationservice.myBlockGroupWaitingYN());
      model.addAttribute("nowPage", pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1);
      return "/reservation/reservationList";
   }

   // 나의 예약가능 목록 띄우기
   @GetMapping("/myReservationList")
   private String selectMyAuctionList(Model model, Reservation reservation, String attendBtn,
         @AuthenticationPrincipal SecurityUser principal) {

      List<ReservationUsers> reservationUsersList = reservationservice
            .selectMyReservation(principal.getUsers().getUserId());
      model.addAttribute("principal", principal);
      model.addAttribute("reservationUsersList", reservationUsersList);
      if(attendBtn != null) {
         model.addAttribute("attendBtn",attendBtn);
         blockGroupService.auctionStart();
      }else {
         model.addAttribute("attendBtn",auctionService.auctionAttendBtnYes());
      }
      System.out.println(model.getAttribute("attendBtn"));
      
      return "/reservation/myReservationList";
   }

   // 예약하기 눌리면 10프로 만 결제하는 창으로 이동
   @GetMapping("/reservationCredit")
   private String reservationCredit(Model model, Reservation reservation) {
      Reservation reservation_ = reservationservice.selectReservationCreditInfo(reservation);
      
      String blockStr = "";
      for (Block block : blockService.findAuctionBlock(reservation_.getBlockGroupWaiting())) {
         blockStr += block.getBlock_seq() + ",";
         model.addAttribute("block", blockStr.substring(0, blockStr.length() - 1));
      }
      
      model.addAttribute("reservationCreditInfo", reservation_);
      
      return "/reservation/reservationCredit";
   }

   // 10프로 결제하기 버튼 클릭 시
   @PostMapping("/insertReservation")
   private String insertReservation(Reservation reservation, @AuthenticationPrincipal SecurityUser principal,Model model) {
      String reservation_message = reservationservice.insertReservationUsers(reservation, principal.getUsers().getUserId());

      MessageDTO message = new MessageDTO(reservation_message, "/reservation/reservationList?reservation_seq="+reservation.getReservation_seq(), RequestMethod.GET, null);

         return showMessageAndRedirect(message, model);
//      return "redirect:/reservation/reservationList";
   }
   
   private String showMessageAndRedirect(final MessageDTO params, Model model) {
         model.addAttribute("params", params);
         return "/common/messageRedirect";
      }
   
   @GetMapping("/deleteReservation")
   private String deleteReservation(Reservation reservation, @AuthenticationPrincipal SecurityUser principal,
         ReservationUsers reservationUsers) {

      reservationservice.deleteReservationUsers(reservation, principal.getUsers().getUserId(), reservationUsers);

      return "redirect:/reservation/myReservationList";
   }
   
   // 메인 페이지에서 예약 버튼 클릭 시 보증금 결제 페이지로 이동
   @GetMapping("/mainReservationCreaditView")
   private String mainReservation(int blockGroup_seq) {
      return "redirect:/reservation/reservationCreditView?reservation_seq=" + reservationservice.getReservationSeq(blockGroup_seq);
   }
   
}