package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import xmu.crms.entity.Group;

@Controller

@RequestMapping("/group")

public class GroupController {
	
	@RequestMapping(value = "/{groupId}", method = GET)
	public String getGroupById(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}", method = PUT)
	public String updateGroupById(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/topic", method = POST)
	public String selectTopic(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/topic/{topicId}", method = DELETE)
	public String deselectTopic(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/grade", method = GET)
	public String getGradeByGroupId(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/grade", method = PUT)
	public String finalGradeByGroupId(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/grade/{studentId}", method = PUT)
	public String submitGradeByGroupId(@PathVariable int id) {
		
		return null;
	}
}
