package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.Map;

@Controller

@RequestMapping("/")

public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/me", method = GET)
	@ResponseBody
	public ResponseEntity getCurrentUser() {
		BigInteger id = BigInteger.valueOf(1);
		try{
			User user = userService.getUserByUserId(id);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(user);
		}catch (UserNotFoundException e){
			System.out.println(e.getMessage());
			return ResponseEntity.status(400).build();
		}
	}
	
	@RequestMapping(value = "/me", method = PUT)
	public ResponseEntity updateCurrentUser(@RequestBody Map<String, String> request) {
//	    User user = new User(123, Type.STUDENT,request.get("number"),
//                request.get("name"),request.get("phone"),request.get("email"),
//                Gender.MALE,null,request.get("title"),
//                null,null,request.get("avator"));
//		Boolean result = userService.updateUserById(123, user);
//		if(result){
		return ResponseEntity.status(204).build();
//		}else{
//			return ResponseEntity.status(400).build();
//		}
	}
	
	
	@RequestMapping(value = "/signin", method = GET)
	public ResponseEntity signInWechat(@RequestParam String code, @RequestParam String state, @RequestParam String successUrl) {
		
		String success = "{\n" +
				"  \"id\": 3486,\n" +
				"  \"type\": \"student\",\n" +
				"  \"name\": \"张三\",\n" +
				"  \"jwt\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjaWQiOiJPQTAwMDEiLCJpYXQiOjE0ODI2NTcyODQyMjF9.TeJpy936w610Vrrm+c3+RXouCA9k1AX0Bk8qURkYkdo=\"\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(success);
	}

}