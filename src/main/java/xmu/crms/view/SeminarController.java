package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/seminar")

public class SeminarController {
	@Autowired
	
	@RequestMapping(value = "/{seminarId}", method = GET)
	public String getSeminarById() {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}", method = PUT)
	public String updateSeminarById() {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}", method = DELETE)
	public String deleteSeminarById() {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}/topic", method = GET)
	public String getTopicsBySeminarId() {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}/topic", method = POST)
	public String createTopicBySeminarId() {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}/group", method = GET)
	public String getGroupBySeminarId() {
		
		return null;
	}
}
