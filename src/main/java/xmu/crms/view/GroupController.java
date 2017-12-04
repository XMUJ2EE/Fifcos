package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.Group;

@Controller

@RequestMapping("/group")

public class GroupController {
	
	@RequestMapping(value = "/{groupId}", method = GET)
	@ResponseBody
	public String getGroupById(@PathVariable int id, @RequestBody boolean embedTopics, @RequestBody boolean embedGrade) {
		
		return null;
	}
	
	@RequestMapping(value = "/{groupId}/resign", method = PUT)
	@ResponseBody
	public ResponseEntity resignGroupLeader(@PathVariable int groupid, @RequestBody Map<String, Integer> request) {

		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/assign", method = PUT)
	@ResponseBody
	public ResponseEntity assignGroupLeader(@PathVariable int groupid, @RequestBody Map<String, Integer> request) {

		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/add", method = PUT)
	@ResponseBody
	public ResponseEntity addGroupMember(@PathVariable int groupid, @RequestBody Map<String, Integer> request) {
		
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/remove", method = PUT)
	@ResponseBody
	public ResponseEntity removeGroupMember(@PathVariable int groupid, @RequestBody Map<String, Integer> request) {

		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/topic", method = POST)
	@ResponseBody
	public String selectTopic(@PathVariable int groupid, @RequestBody Map<String, Integer> request) {

		return "\"url\": \"/group/27/topic/23\"";
	}
	
	@RequestMapping(value = "/{groupId}/topic/{topicId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deselectTopic(@PathVariable int groupId, @PathVariable int topicId) {
		
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/grade", method = GET)
	@ResponseBody
	public String getGradeByGroupId(@PathVariable int id) {
		
		return "{\n" +
				"  \"presentationGrade\": [\n" +
				"    {\n" +
				"      \"topicId\": 257,\n" +
				"      \"grade\": 4\n" +
				"    },\n" +
				"    {\n" +
				"      \"topicId\": 258,\n" +
				"      \"grade\": 5\n" +
				"    }\n" +
				"  ],\n" +
				"  \"reportGrade\": 3,\n" +
				"  \"grade\": 4\n" +
				"}";
	}
	
	@RequestMapping(value = "/{groupId}/grade", method = PUT)
	@ResponseBody
	public ResponseEntity finalGradeByGroupId(@PathVariable int groupid, @RequestBody Map<String, Integer> request) {
		
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/grade/{studentId}", method = PUT)
	@ResponseBody
	public String submitGradeByGroupId(@PathVariable int id, int studentId) {
		
		return null;
	}
}
