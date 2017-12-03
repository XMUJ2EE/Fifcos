package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/topic")

public class TopicController {
	@Autowired
	
	@RequestMapping(value = "/{topicId}", method = GET)
	public String getTopicById(@PathVariable int id, Model model) {
		
		return null;
	}
	
	@RequestMapping(value = "/{topicId}", method = PUT)
	public String updateTopicById(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{topicId}", method = DELETE)
	public String deleteTopicById(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{topicId}/group", method = GET)
	public String getGroupsByTopicId(@PathVariable int id) {
		
		return null;
	}
}
