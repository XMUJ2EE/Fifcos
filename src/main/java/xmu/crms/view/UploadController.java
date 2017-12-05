package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.service.UploadService;
import xmu.crms.service.UploadServiceImpl;
import xmu.crms.view.vo.AvatarVO;

@Controller

@RequestMapping("/upload")

public class UploadController {
	@Autowired
	UploadService uploadService = new UploadServiceImpl();

	@RequestMapping(value = "/avatar", method = POST)
	@ResponseBody
	public ResponseEntity<AvatarVO> uploadAvatar() {
		AvatarVO avatarVO = uploadService.uploadAvatar();
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(avatarVO);
	}
	/*
	@RequestMapping(value = "/classroster", method = POST)
	public String uploadRoster() {
		
		return null;
	}
	
	@RequestMapping(value = "/report", method = POST)
	public String uploadReport() {
		
		return null;
	}*/
}
