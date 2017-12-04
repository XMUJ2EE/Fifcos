package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/")

public class UserController {
	@Autowired

	
	@RequestMapping(value = "/me", method = GET, produces = "application/json;charset=UTF-8")
	public String getCurrentUser() {
//		User user =
		return null;
	}
	
	@RequestMapping(value = "/me", method = PUT, consumes = "application/json;charset=UTF-8")
	public String updateCurrentUser() {
		
		return null;
	}
	
	
	@RequestMapping(value = "/signin", method = GET)
	public String signinWechat() {
		
		return null;
	}
	
	@RequestMapping(value = "/signin", method = POST)
	public String signinPassword(String phone, String password) {
		
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