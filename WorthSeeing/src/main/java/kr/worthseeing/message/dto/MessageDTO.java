package kr.worthseeing.message.dto;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

	private String message;
	private String redirectUri;
	private RequestMethod method;
	private Map<String, Object> data;
	
}
