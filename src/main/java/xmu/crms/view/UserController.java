package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.Gender;
import xmu.crms.entity.Type;
import xmu.crms.entity.User;
import xmu.crms.service.UserService;
import xmu.crms.service.UserServiceImpl;

import java.util.Map;

@Controller

@RequestMapping("/")

public class UserController {

	@Autowired
	private  UserService userService = new UserServiceImpl();
	
	@RequestMapping(value = "/me", method = GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public User getCurrentUser() {
		int id = 123;
		return userService.getUserById(id);
	}
	
	@RequestMapping(value = "/me", method = PUT, consumes = "application/json;charset=utf-8")
	public ResponseEntity updateCurrentUser(@RequestBody Map<String, String> request) {
	    User user = new User(123, Type.STUDENT,request.get("number"),
                request.get("name"),request.get("phone"),request.get("email"),
                Gender.MALE,null,request.get("title"),
                null,null,request.get("avator"));
		Boolean result = userService.updateUserById(123, user);
		if(result){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/signin", method = GET)
	public String signInWechat(@RequestParam String code, @RequestParam String state, @RequestParam String successUrl) {
		
		return null;
	}
	
	@RequestMapping(value = "/signin", method = POST)
	public String signInPassword(String phone, String password) {
		
		return null;
	}
	
	@RequestMapping(value = "/register", method = POST)
	public String registerPassword(String phone, String password) {
		
		return null;
	}
	
	@RequestMapping(value = "/school", method = GET)
	public String getSchools() {
		
		return null;
	}
	
	@RequestMapping(value = "/school", method = POST)
	public String createSchool() {
		
		return null;
	}
}