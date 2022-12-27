package kr.worthseeing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorthSeeingApplication extends SpringBootServletInitializer{
	
	 // 이 부분 추가(war하기위해 추가)
		@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
			return builder.sources(WorthSeeingApplication.class);
		}

	public static void main(String[] args) {
		SpringApplication.run(WorthSeeingApplication.class, args);
	}
}
