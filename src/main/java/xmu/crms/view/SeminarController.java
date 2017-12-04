package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/seminar")

public class SeminarController {
	
	@RequestMapping(value = "/{seminarId}", method = GET)
	public String getSeminarById(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}", method = PUT)
	public String updateSeminarById(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}", method = DELETE)
	public String deleteSeminarById(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}/topic", method = GET)
	public String getTopicsBySeminarId(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}/topic", method = POST)
	public String createTopicBySeminarId(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{seminarId}/group", method = GET)
	public String getGroupBySeminarId(@PathVariable int id) {
		
		return null;
	}
}
