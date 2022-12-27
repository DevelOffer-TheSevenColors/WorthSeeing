package kr.worthseeing.main.message.service.impl;


import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.worthseeing.main.message.repository.MessageRepository;
import kr.worthseeing.main.message.service.MessageService;
import kr.worthseeing.main.reservation.entity.ReservationUsers;
import kr.worthseeing.main.reservation.repository.ReservationUsersRepository;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@Component
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private ReservationUsersRepository reservationUsersRepo;
	
	
	@Override
	public void SendSMS(String phoneNumber){
		 
		String api_key = "NCSXC7EOXNVZTEV4";      //사이트에서 발급 받은 API KEY
		  
		 String api_secret = "IHNXT8XH4GVTFA5W27YXHOVUL1SEL0WL";        //사이트에서 발급 받은 API SECRET KEY
		    
		    Message coolsms = new Message(api_key, api_secret);
		    
		    System.out.println(coolsms.toString() + "test---------");
		    
		    
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put("to", "01039300472"); //받는사람
		    params.put("from", "01080285978");  //보내는사람
		    //사전에 사이트에서 번호를 인증하고 등록하여야 함
		    params.put("type", "SMS");
		    params.put("text", "공지를 확인해주세요");     //메시지 내용
		    params.put("app_version", "test app 1.2");
		    
		    try {
		      JSONObject obj = (JSONObject) coolsms.send(params);
		      System.out.println(obj.toString());   //전송 결과 출력 
		    } catch (CoolsmsException e) {
		      System.out.println(e.getMessage());
		      System.out.println(e.getCode());
		    }
		  }
	

	@Scheduled(cron = "0 0 12 20 * *")
	public void setSendreservationMsg( ) {
		for(ReservationUsers reservationUser : reservationUsersRepo.findAll()) {
			
			SendSMS( reservationUser.getUsers().getTel() );
			
			kr.worthseeing.main.message.entity.Message message = new kr.worthseeing.main.message.entity.Message();
			message.setSendTime(new Date());
			message.setToUser(reservationUser.getUsers().getUserId());
			message.setMsg("공지를 확인해주세요");
			messageRepo.save(message);
			System.out.println("에약문자 전송완료");
		}
		
		
	}
	
	@Scheduled(cron = "0 0 12 27 * *")
	public void setSendauctionMsg( ) {
		for(ReservationUsers reservationUser : reservationUsersRepo.findAll()) {
			
			SendSMS( reservationUser.getUsers().getTel() );
			
			kr.worthseeing.main.message.entity.Message message = new kr.worthseeing.main.message.entity.Message();
			message.setSendTime(new Date());
			message.setToUser(reservationUser.getUsers().getUserId());
			message.setMsg("공지를 확인해주세요");
			messageRepo.save(message);
			System.out.println("경매문자 전송완료");
		}
		
		
	}
	
	
	
	
	
}
