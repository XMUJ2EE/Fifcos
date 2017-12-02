package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/group")

public class GroupController {
	@Autowired
	
	@RequestMapping(value = "/{groupId}", method = GET)
	public String getGroupById() {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}", method = PUT)
	public String updateGroupById() {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/topic", method = POST)
	public String selectTopic() {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/topic/{topicId}", method = DELETE)
	public String deselectTopic() {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/grade", method = GET)
	public String getGradeByGroupId() {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/grade", method = PUT)
	public String finalGradeByGroupId() {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/grade/{studentId}", method = PUT)
	public String submitGradeByGroupId() {
		
		return null;
	}
}
