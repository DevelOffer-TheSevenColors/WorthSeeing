package kr.worthseeing.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.worthseeing.users.entity.Users;
import kr.worthseeing.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	  
	  @GetMapping("/mypageMain")
		public String getmypage(Users users) {
			return "mypageMain";
		}
	  
	  @GetMapping("/mypageAuctionHistory")
			public String getmymypageAuctionHistory(Users users) {
				return "mypageAuctionHistory";
			}
	  
	  @GetMapping("/mypagePointHistory")
			public String getmypagePointHistory(Users users) {
				return "mypagePointHistory";
			}
	  
	  @GetMapping("/mypagePurchaseHistory")
			public String getmyagePurchaseHistory(Users users) {
				return "mypagePurchaseHistory";
			}
	  
	  @GetMapping("/mypageCouponMall")
		public String getmypageCouponMall(Users users) {
			return "mypageCouponMall";
		}

}
