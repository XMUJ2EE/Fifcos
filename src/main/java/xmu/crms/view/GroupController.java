package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.Grade;
import xmu.crms.entity.PresentationGrade;
import xmu.crms.service.GroupService;
import xmu.crms.service.GroupServiceImpl;
import xmu.crms.view.vo.GroupDetailsVO;

@Controller

@RequestMapping("/group")

public class GroupController {
//	@Autowired
//	GroupService groupService = new GroupServiceImpl();

	@RequestMapping(value = "/{groupId}", method = GET)
	@ResponseBody
	public ResponseEntity getGroupById(@PathVariable int groupId, @RequestBody boolean embedTopics, @RequestBody boolean embedGrade) {
//		GroupDetailsVO groupDetailsVO = groupService.getGroupById(groupId, embedTopics, embedGrade);
//
//		if (groupId <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupDetailsVO != null){
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetailsVO);
//		}else{
//			return ResponseEntity.status(404).body(null);
//		}
		String group = "{\n" +
				"  \"id\": 28,\n" +
				"  \"leader\": {\n" +
				"    \"id\": 8888,\n" +
				"    \"name\": \"张三\"\n" +
				"  },\n" +
				"  \"members\": [\n" +
				"    {\n" +
				"      \"id\": 5324,\n" +
				"      \"name\": \"李四\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"id\": 5678,\n" +
				"      \"name\": \"王五\"\n" +
				"    }\n" +
				"  ],\n" +
				"  \"topics\": [\n" +
				"    {\n" +
				"      \"id\": 257,\n" +
				"      \"name\": \"领域模型与模块\"\n" +
				"    }\n" +
				"  ],\n" +
				"  \"report\": \"\"\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(group);
	}
	
	@RequestMapping(value = "/{groupId}/resign", method = PUT)
	@ResponseBody
	public ResponseEntity resignGroupLeader(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
//		if (groupId <= 0)
//			return ResponseEntity.status(404).body(null);
//		if (request.get("id") <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupService.resignGroupLeader(groupId, request.get("id"))){
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(403).body(null);
//		}
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/assign", method = PUT)
	@ResponseBody
	public ResponseEntity assignGroupLeader(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
//		if (groupId <= 0)
//			return ResponseEntity.status(404).body(null);
//		if (request.get("id") <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupService.assignGroupLeader(groupId, request.get("id"))){
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(403).body(null);
//		}
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/add", method = PUT)
	@ResponseBody
	public ResponseEntity addGroupMember(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
//
//		if (groupId <= 0)
//			return ResponseEntity.status(404).body(null);
//		if (request.get("id") <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupService.addGroupMember(groupId, request.get("id"))){
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(403).body(null);
//		}
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/remove", method = PUT)
	@ResponseBody
	public ResponseEntity removeGroupMember(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {

//		if (groupId <= 0)
//			return ResponseEntity.status(404).body(null);
//		if (request.get("id") <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupService.removeGroupMember(groupId, request.get("id"))){
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(403).body(null);
//		}
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/topic", method = POST)
	@ResponseBody
	public ResponseEntity selectTopic(@PathVariable int groupId) {
//		if (request.get("id") <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupId <= 0)
//			return ResponseEntity.status(404).body(null);
//		if (groupService.selectTopic(groupId, request.get("id")) != null){
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupService.selectTopic(groupId, request.get("id")));
//		}else{
//			return ResponseEntity.status(403).body(null);
//		}
		String u = "{\n" +
				"  \"url\": \"/group/27/topic/23\"\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(u);
	}
	
	@RequestMapping(value = "/{groupId}/topic/{topicId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deselectTopic(@PathVariable int groupId, @PathVariable int topicId) {
//		if (topicId <= 0)
//			return ResponseEntity.status(404).body(null);
//		if (groupId <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupService.deselectTopic(groupId, topicId)){
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(403).body(null);
//		}
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{groupId}/grade", method = GET)
	@ResponseBody
	public ResponseEntity getGradeByGroupId(@PathVariable int groupId) {

//		Grade grade = groupService.getGradeByGroupId(groupId);
//		if (groupId <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (grade != null) {
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(grade);
//		}else{
//			return ResponseEntity.status(404).body(null);
//		}
		String grade = "{\n" +
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
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(grade);
	}
	
	@RequestMapping(value = "/{groupId}/grade/report", method = PUT)
	@ResponseBody
	public ResponseEntity finalGradeByGroupId(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
//		if (groupId <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupService.finalGradeByGroupId(groupId, request.get("reportGrade"))) {
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(404).body(null);
//		}
		return ResponseEntity.status(204).body(null);

	}
	
	@RequestMapping(value = "/{groupId}/grade/presentation/{studentId}", method = PUT)
	@ResponseBody
	public ResponseEntity submitGradeByGroupId(@PathVariable int groupId, @PathVariable int studentId) {
//		//if (groupId <= 0)
//			return ResponseEntity.status(400).body(null);
//		//if (groupService.submitGradeByGroupId(groupId, studentId)){
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(404).body(null);
//		}
		return ResponseEntity.status(204).body(null);
	}
}
