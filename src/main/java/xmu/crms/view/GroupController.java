package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.math.BigInteger;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.service.GradeService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.TopicService;
import xmu.crms.service.impl.GradeServiceImpl;
import xmu.crms.view.vo.PresentationGradeVO;

@Controller

@RequestMapping("/group")

public class GroupController {
	@Autowired
	GradeService gradeService;
//	@Autowired
//	SeminarGroupService seminarGroupService;
//	@Autowired
//	TopicService topicService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}", method = GET)
	@ResponseBody
	public ResponseEntity getGroupById(@PathVariable int groupId) {

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/resign", method = PUT)
	@ResponseBody
	public ResponseEntity resignGroupLeader(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {

		return ResponseEntity.status(204).body(null);
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/assign", method = PUT)
	@ResponseBody
	public ResponseEntity assignGroupLeader(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {

		return ResponseEntity.status(204).body(null);
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/add", method = PUT)
	@ResponseBody
	public ResponseEntity addGroupMember(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {

		return ResponseEntity.status(204).body(null);
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/remove", method = PUT)
	@ResponseBody
	public ResponseEntity removeGroupMember(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {

		return ResponseEntity.status(204).body(null);
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/topic", method = POST)
	@ResponseBody
	public ResponseEntity selectTopic(@PathVariable int groupId, @RequestBody Map<String, Integer> request) throws GroupNotFoundException, TopicNotFoundException {
//		int id = request.get("id");
//		if (seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId)) == null) {
//			return ResponseEntity.status(404).body(null);
//		}else {
//			seminarGroupService.insertTopicByGroupId(BigInteger.valueOf(groupId), BigInteger.valueOf(id));
//		}
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/topic/{topicId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deselectTopic(@PathVariable int groupId, @PathVariable int topicId) {

		return ResponseEntity.status(204).body(null);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/grade", method = GET)
	@ResponseBody
	public ResponseEntity getGradeByGroupId(@PathVariable int groupId) {

		SeminarGroup seminarGroup = null;
		try {
			seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(1), BigInteger.valueOf(groupId));
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
		}
		if (seminarGroup == null) {
			return ResponseEntity.status(404).body(null);
		}else{
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGroup);
		}
	}
	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/grade/report", method = PUT)
	@ResponseBody
	public ResponseEntity finalGradeByGroupId(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		SeminarGroup seminarGroup = null;
		try {
			seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(1), BigInteger.valueOf(groupId));
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
		}
		if (seminarGroup == null) {
			return ResponseEntity.status(404).body(null);
		}else{
			BigInteger grade = BigInteger.valueOf(request.get("reportGrade"));
			try {
				gradeService.updateGroupByGroupId(BigInteger.valueOf(groupId), grade);
			} catch (GroupNotFoundException e) {
				e.printStackTrace();
			}
			return ResponseEntity.status(204).body(null);
		}
	}
	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/grade/presentation/{studentId}", method = PUT)
	@ResponseBody
	public ResponseEntity submitGradeByGroupId(@PathVariable int groupId, @PathVariable int studentId, @RequestBody Map<String, Integer> request) {

		SeminarGroup seminarGroup = null;
		try {
			seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(1), BigInteger.valueOf(groupId));
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
		}
		if (seminarGroup == null) {
			return ResponseEntity.status(404).body(null);
		}else{
			BigInteger topicId = BigInteger.valueOf(request.get("topicId"));
			BigInteger grade = BigInteger.valueOf(request.get("grade"));
			BigInteger groupId1 = BigInteger.valueOf(groupId);
			BigInteger studentId1 = BigInteger.valueOf(studentId);
			gradeService.insertGroupGradeByUserId(topicId, studentId1, groupId1, grade);
			return ResponseEntity.status(204).body(null);
		}
	}
}
