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
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.service.*;

/**
 * @author wang
 */
@Controller

@RequestMapping("/group")

public class GroupController {
	@Autowired
	GradeService gradeService;
	//@Autowired
	SeminarGroupService seminarGroupService;
	//@Autowired
	FixGroupService fixGroupService;
	@Autowired
	UserService userService;

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
		try {
			User user = userService.getUserByUserId(BigInteger.valueOf(request.get("id")));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(null);
		}
		try {
			seminarGroupService.resignLeaderById(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
			return ResponseEntity.status(204).body(null);
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/assign", method = PUT)
	@ResponseBody
	public ResponseEntity assignGroupLeader(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			seminarGroupService.assignLeaderById(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
			return ResponseEntity.status(204).body(null);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(null);
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		} catch (InvalidOperationException e) {
			e.printStackTrace();
			return ResponseEntity.status(409).body(null);
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/add", method = PUT)
	@ResponseBody
	public ResponseEntity addGroupMember(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			seminarGroupService.insertSeminarGroupMemberById(BigInteger.valueOf(request.get("id")), BigInteger.valueOf(groupId));
			return ResponseEntity.status(204).body(null);
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		} catch (InvalidOperationException e) {
			e.printStackTrace();
			return ResponseEntity.status(403).body(null);
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/remove", method = PUT)
	@ResponseBody
	public ResponseEntity removeGroupMember(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
        try {
            SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId));
            fixGroupService.deleteFixGroupUserById(seminarGroup.getFixGroup().getId(), BigInteger.valueOf(request.get("id")));
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(204).body(null);
        } catch (FixGroupNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(204).body(null);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.status(204).body(null);
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/topic", method = POST)
	@ResponseBody
	public ResponseEntity selectTopic(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			seminarGroupService.insertTopicByGroupId(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
			String url = "{\"url\":" + "\"/group/" + String.valueOf(groupId) + "/topic/" + String.valueOf(request.get("id")) + "\"}";
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(url);
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).body(null);
		}
	}


//	@PreAuthorize("hasRole('STUDENT')")
//	@RequestMapping(value = "/{groupId}/topic/{topicId}", method = DELETE)
//	@ResponseBody
//	public ResponseEntity deselectTopic(@PathVariable int groupId, @PathVariable int topicId) {
//		try {
//			fixGroupService.deleteTopicByGroupId(BigInteger.valueOf(groupId));
//			return ResponseEntity.status(204).body(null);
//		} catch (FixGroupNotFoundException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(404).body(null);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(400).body(null);
//		}
//	}


	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/grade", method = GET)
	@ResponseBody
	public ResponseEntity getGradeByGroupId(@PathVariable int groupId) {
		SeminarGroup seminarGroup = null;
		try {
			seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
		}
		if (seminarGroup == null) {
			return ResponseEntity.status(404).body(null);
		}else{
			//GroupGradeVO groupGradeVO = new GroupGradeVO(seminarGroup.getPresentationGrade(), seminarGroup.getReportGrade(), seminarGroup.getFinalGrade());
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGroup);
		}
	}


	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/grade/report", method = PUT)
	@ResponseBody
	public ResponseEntity finalGradeByGroupId(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		SeminarGroup seminarGroup = null;
		try {
			seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
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
			seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
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
