package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/")

public class HomeController {
	@Autowired
	
	@RequestMapping(value = "/me", method = GET)
	public String getCurrentUser() {
		
		return null;
	}
	
	@RequestMapping(value = "/me", method = PUT)
	public String updateCurrentUser() {
		
		return null;
	}
	
	
	@RequestMapping(value = "/signin", method = GET)
	public String signinWechat() {
		
		return null;
	}
	
	@RequestMapping(value = "/signin", method = POST)
	public String signinPassword() {
		
		return null;
	}
	
	@RequestMapping(value = "/register", method = POST)
	public String registerPassword() {
		
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