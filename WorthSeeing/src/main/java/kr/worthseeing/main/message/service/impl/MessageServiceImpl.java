package kr.worthseeing.main.message.service.impl;


import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.worthseeing.main.message.service.MessageService;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class MessageServiceImpl implements MessageService{
	
	private void SendSMS(){
		 String api_key = "NCSUITE0YBDKUPYA";      //사이트에서 발급 받은 API KEY
		    String api_secret = "PUYKDBJR9NKPZOWIPUUFLSRUK03WZGER";        //사이트에서 발급 받은 API SECRET KEY
		    Message coolsms = new Message(api_key, api_secret);
		    System.out.println(coolsms.toString() + "test---------");
		    
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put("to", "01026751123"); //받는사람
		    params.put("from", "01026751123");  //보내는사람
		    //사전에 사이트에서 번호를 인증하고 등록하여야 함
		    params.put("type", "SMS");
		    params.put("text", "testtest");     //메시지 내용
		    params.put("app_version", "test app 1.2");
		    
		    try {
		      JSONObject obj = (JSONObject) coolsms.send(params);
		      System.out.println(obj.toString());   //전송 결과 출력 
		    } catch (CoolsmsException e) {
		      System.out.println(e.getMessage());
		      System.out.println(e.getCode());
		    }
		  }
	

	//@Scheduled(cron = "0 1 1 10 * *")
//	@Scheduled(cron = "1 0 0 * * *")
	public void setSendMsg() {
		//SendSMS();
		System.out.println("===========>");
	}
	
	
	
	
	
}
