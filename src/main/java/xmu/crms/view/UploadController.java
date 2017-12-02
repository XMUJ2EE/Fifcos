package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/upload")

public class UploadController {
	@Autowired
	
	@RequestMapping(value = "/avatar", method = POST)
	public String uploadAvatar() {
		
		return null;
	}
	
	@RequestMapping(value = "/classroster", method = POST)
	public String uploadRoster() {
		
		return null;
	}
	
	@RequestMapping(value = "/report", method = POST)
	public String uploadReport() {
		
		return null;
	}
}
