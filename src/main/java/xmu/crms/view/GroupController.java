package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.SeminarGroupTopic;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.service.*;
import xmu.crms.view.vo.*;

/**
 * @author wang
 */
@Controller

@RequestMapping("/group")

public class GroupController {
	@Autowired
	GradeService gradeService;
	@Autowired
	SeminarGroupService seminarGroupService;
	@Autowired(required = false)
	FixGroupService fixGroupService;
	@Autowired
	UserService userService;
	@Autowired
	TopicService topicService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}", method = GET)
	@ResponseBody
	public ResponseEntity getGroupById(@PathVariable int groupId, @RequestParam Boolean embedTopics, @RequestParam Boolean embedGrade) {
		if (embedTopics && embedGrade) {
			try {
				List<User> member = seminarGroupService.listSeminarGroupMemberByGroupId(BigInteger.valueOf(groupId));
				UserVO leader = new UserVO(seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId)).getLeader());
				List<UserVO> members = new ArrayList<UserVO>();
				for (User user : member) {
					UserVO userVO = new UserVO(user);
					members.add(userVO);
				}
				SeminarGroup group = seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId));
				List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
				List<TopicVO> topics = new ArrayList<TopicVO>();
				for (SeminarGroupTopic seminarGroupTopic : list) {
					TopicVO topicVO = new TopicVO(seminarGroupTopic.getTopic().getId().intValue(), seminarGroupTopic.getTopic().getName());
					topics.add(topicVO);
				}
				List<PresentationGradeVO> topicList = new ArrayList<PresentationGradeVO>();
				for (SeminarGroupTopic s : list) {
					PresentationGradeVO presentationGradeVO = new PresentationGradeVO(s.getTopic().getId().intValue(), group.getPresentationGrade());
					topicList.add(presentationGradeVO);
				}
				GroupGradeVO groupGradeVO = new GroupGradeVO(topicList, group);
				GroupMemberTopicGradeVO groupDetail = new GroupMemberTopicGradeVO(group.getId().intValue(), group.getId().toString(),
						leader, members, topics, groupGradeVO, "");
				return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetail);
			} catch (GroupNotFoundException e) {
				e.printStackTrace();
				return ResponseEntity.status(404).build();
			}
		}
		else if (embedTopics && !embedGrade) {
			try {
				List<User> member = seminarGroupService.listSeminarGroupMemberByGroupId(BigInteger.valueOf(groupId));
				UserVO leader = new UserVO(seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId)).getLeader());
				List<UserVO> members = new ArrayList<UserVO>();
				for (User user : member) {
					UserVO userVO = new UserVO(user);
					members.add(userVO);
				}
				SeminarGroup group = seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId));
				GroupVO groupVO = new GroupVO(group);
				List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
				List<TopicVO> topics = new ArrayList<TopicVO>();
				for (SeminarGroupTopic seminarGroupTopic : list) {
					TopicVO topicVO = new TopicVO(seminarGroupTopic.getTopic().getId().intValue(), seminarGroupTopic.getTopic().getName());
					topics.add(topicVO);
				}
				GroupDetailsVO groupDetailsVO = new GroupDetailsVO(groupVO, leader, members, topics, "");
				return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetailsVO);
			} catch (GroupNotFoundException e) {
				e.printStackTrace();
				return ResponseEntity.status(404).build();
			}
		}
		else if (!embedTopics && embedGrade) {
			List<User> member = null;
			try {
				member = seminarGroupService.listSeminarGroupMemberByGroupId(BigInteger.valueOf(groupId));
				UserVO leader = new UserVO(seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId)).getLeader());
				List<UserVO> members = new ArrayList<UserVO>();
				for (User user : member) {
					UserVO userVO = new UserVO(user);
					members.add(userVO);
				}
				SeminarGroup group = seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId));
				List<PresentationGradeVO> topicList = new ArrayList<PresentationGradeVO>();
				List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
				for (SeminarGroupTopic s : list) {
					PresentationGradeVO presentationGradeVO = new PresentationGradeVO(s.getTopic().getId().intValue(), group.getPresentationGrade());
					topicList.add(presentationGradeVO);
				}
				GroupGradeVO groupGradeVO = new GroupGradeVO(topicList, group);
				GroupMemberGradeVO groupDetail = new GroupMemberGradeVO(group.getId().intValue(), group.getId().toString(),
						leader, members, groupGradeVO, "");
				return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetail);
			} catch (GroupNotFoundException e) {
				e.printStackTrace();
				return ResponseEntity.status(404).build();
			}
		} else {
			try {
				List<User> member = seminarGroupService.listSeminarGroupMemberByGroupId(BigInteger.valueOf(groupId));
				UserVO leader = new UserVO(seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId)).getLeader());
				List<UserVO> members = new ArrayList<UserVO>();
				for (User user : member) {
					UserVO userVO = new UserVO(user);
					members.add(userVO);
				}
				SeminarGroup group = seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId));
				GroupMembersVO groupMembersVO = new GroupMembersVO(group.getId().intValue(),
						group.getId().toString(), leader, members, "");
				return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupMembersVO);
			} catch (GroupNotFoundException e) {
				e.printStackTrace();
				return ResponseEntity.status(404).build();
			}
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/resign", method = PUT)
	@ResponseBody
	public ResponseEntity resignGroupLeader(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			User user = userService.getUserByUserId(BigInteger.valueOf(request.get("id")));
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
		try {
			seminarGroupService.resignLeaderById(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
			return ResponseEntity.status(204).build();
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/assign", method = PUT)
	@ResponseBody
	public ResponseEntity assignGroupLeader(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			seminarGroupService.assignLeaderById(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
			return ResponseEntity.status(204).build();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (InvalidOperationException e) {
			e.printStackTrace();
			return ResponseEntity.status(409).build();
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/add", method = PUT)
	@ResponseBody
	public ResponseEntity addGroupMember(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			seminarGroupService.insertSeminarGroupMemberById(BigInteger.valueOf(request.get("id")), BigInteger.valueOf(groupId));
			return ResponseEntity.status(204).build();
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (InvalidOperationException e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/remove", method = PUT)
	@ResponseBody
	public ResponseEntity removeGroupMember(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			fixGroupService.deleteFixGroupUserById(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
			return ResponseEntity.status(204).build();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		} catch (FixGroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/topic", method = POST)
	@ResponseBody
	public ResponseEntity selectTopic(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			seminarGroupService.insertTopicByGroupId(BigInteger.valueOf(groupId), BigInteger.valueOf(request.get("id")));
			Map<String, String> result = new HashMap<String, String>();
			String url = "/group/" + String.valueOf(groupId) + "/topic/" + String.valueOf(request.get("id"));
			result.put("url", url);
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
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
		try {
			SeminarGroup seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
			List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
			List<PresentationGradeVO> topicList = new ArrayList<PresentationGradeVO>();
			for (SeminarGroupTopic s : list) {
				PresentationGradeVO presentationGradeVO = new PresentationGradeVO(s.getTopic().getId().intValue(), seminarGroup.getPresentationGrade());
				topicList.add(presentationGradeVO);
			}
			GroupGradeVO groupGradeVO = new GroupGradeVO(topicList, seminarGroup);
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupGradeVO);
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}


	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/grade/report", method = PUT)
	@ResponseBody
	public ResponseEntity finalGradeByGroupId(@PathVariable int groupId, @RequestBody Map<String, Integer> request) {
		try {
			SeminarGroup seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
			BigInteger grade = BigInteger.valueOf(request.get("reportGrade"));
			gradeService.updateGroupByGroupId(BigInteger.valueOf(groupId), grade);
			return ResponseEntity.status(204).build();
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
	}


	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{groupId}/grade/presentation/{studentId}", method = PUT)
	@ResponseBody
	public ResponseEntity submitGradeByGroupId(@PathVariable int groupId, @PathVariable int studentId,
											   @RequestBody List<PresentationGradeVO> presentationGrade) {
		try {
			SeminarGroup seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
			for (PresentationGradeVO presentation : presentationGrade) {
				gradeService.insertGroupGradeByUserId(BigInteger.valueOf(presentation.getTopicId()),
						BigInteger.valueOf(studentId), BigInteger.valueOf(groupId),
						BigInteger.valueOf(presentation.getGrade()));
			}
			return ResponseEntity.status(204).build();
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}
}
