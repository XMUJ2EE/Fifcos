package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/topic")

public class TopicController {
	@Autowired
	
	@RequestMapping(value = "/{topicId}", method = GET)
	public String getTopicById() {
		
		return null;
	}
	
	@RequestMapping(value = "/{topicId}", method = PUT)
	public String updateTopicById() {
		
		return null;
	}
	
	@RequestMapping(value = "/{topicId}", method = DELETE)
	public String deleteTopicById() {
		
		return null;
	}
	
	@RequestMapping(value = "/{topicId}/group", method = GET)
	public String getGroupsByTopicId() {
		
		return null;
	}
}
