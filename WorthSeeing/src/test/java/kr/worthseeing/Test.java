//package kr.worthseeing;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import kr.worthseeing.blockgroup.entity.BlockGroup;
//import kr.worthseeing.notify.entity.Notify;
//import kr.worthseeing.notify.repository.NotifyRepository;
//import kr.worthseeing.status.entity.Status;
//import kr.worthseeing.users.entity.Users;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@EnableScheduling
//public class Test {
//
//	@org.junit.jupiter.api.Test
//	public void test() {
//
//	}
//
//	@Autowired
//	private NotifyRepository notifyRepo;
//
//	// @org.junit.jupiter.api.Test
////	private void SendSMS(){
////		 String api_key = "NCSUITE0YBDKUPYA";      //사이트에서 발급 받은 API KEY
////		    String api_secret = "PUYKDBJR9NKPZOWIPUUFLSRUK03WZGER";        //사이트에서 발급 받은 API SECRET KEY
////		    Message coolsms = new Message(api_key, api_secret);
////		    System.out.println(coolsms.toString() + "test---------");
////		    
////		    HashMap<String, String> params = new HashMap<String, String>();
////		    params.put("to", "01026751123"); //받는사람
////		    params.put("from", "01026751123");  //보내는사람
////		    //사전에 사이트에서 번호를 인증하고 등록하여야 함
////		    params.put("type", "SMS");
////		    params.put("text", "testtest");     //메시지 내용
////		    params.put("app_version", "test app 1.2");
////		    
////		    try {
////		      JSONObject obj = (JSONObject) coolsms.send(params);
////		      System.out.println(obj.toString());   //전송 결과 출력 
////		    } catch (CoolsmsException e) {
////		      System.out.println(e.getMessage());
////		      System.out.println(e.getCode());
////		    }
////		  }
//
//	// @org.junit.jupiter.api.Test
//	// @Scheduled(cron = "0 1 1 10 * *")
//	public void setSendMsg() {
//		// SendSMS();
//		System.out.println("===========>");
//	}
//
////	@org.junit.jupiter.api.Test
//	public void insertNotify() {
//		for (int i = 1; i < 14; i++) {
//			Notify notify = new Notify(i, "공지공지" + i, "작성자" + i, 0);
//
//			Status status = new Status();
//			status.setStatus_seq(2);
//
//			Users users = new Users();
//			users.setUserId("user1");
//
//			BlockGroup blockGroup = new BlockGroup();
//			blockGroup.setBlockGroup_seq(19);
//
//			notify.setUsers(users);
////			notify.setBlockGroup(blockGroup);
//			notify.setStatus(status);
//
//			notifyRepo.save(notify);
//
//		}
//
////		Notify notify = new Notify(1, "제목", "작성자", 0);
////		notifyRepo.save(notify);
////		
//	}
//	
////	@org.junit.jupiter.api.Test
//	public void insertContact() {
//		for (int i = 1; i < 14; i++) {
//			Notify notify = new Notify(i, "문의문의" + i, "작성자" + i, 0);
//
//			Status status = new Status();
//			status.setStatus_seq(4);
//
//			Users users = new Users();
//			users.setUserId("user1");
//
//			BlockGroup blockGroup = new BlockGroup();
//			blockGroup.setBlockGroup_seq(19);
//
//			notify.setUsers(users);
////			notify.setBlockGroup(blockGroup);
//			notify.setStatus(status);
//
//			notifyRepo.save(notify);
//
//		}
//
//	
//
//	}}
