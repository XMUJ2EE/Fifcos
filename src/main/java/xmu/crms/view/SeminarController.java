package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.*;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.SeminarService;
import xmu.crms.service.TopicService;
import xmu.crms.service.impl.SeminarServiceImpl;
import xmu.crms.view.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mads
 */

@Controller
@RequestMapping("/seminar")
public class SeminarController {
	@Autowired
	SeminarServiceImpl seminarService;

	@Autowired
	TopicService topicService;

	@Autowired
	SeminarGroupService seminarGroupService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{seminarId}", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarById(@PathVariable BigInteger seminarId) {
		try {
			Seminar seminar = seminarService.getSeminarBySeminarId(seminarId);
			List<Topic> topics = topicService.listTopicBySeminarId(seminarId);
			SeminarVO seminarVO = new SeminarVO(seminar, topics);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarVO);
		} catch (SeminarNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{seminarId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateSeminarById(@PathVariable int seminarId, HttpServletRequest httpServletRequest) throws IOException {
		try {
			BufferedReader br = httpServletRequest.getReader();
			BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String str, wholeStr = "";
			while((str = br.readLine()) != null){
				wholeStr += str;
			}
			SeminarUpdateVO seminarUpdateVO = new SeminarUpdateVO(wholeStr);
			Seminar seminar = new Seminar(seminarUpdateVO);
			seminarService.updateSeminarBySeminarId(BigInteger.valueOf(seminarId), seminar);
			return ResponseEntity.status(204).build();
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{seminarId}", method = DELETE)
	public ResponseEntity deleteSeminarById(@PathVariable int seminarId) {
		try {
			seminarService.deleteSeminarBySeminarId(BigInteger.valueOf(seminarId));
			return ResponseEntity.status(204).build();
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{seminarId}/my", method = GET)
	public ResponseEntity getSeminarWithMe(@PathVariable int seminarId) {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();




		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDNET')")
	@RequestMapping(value = "/{seminarId}/detail", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarDetail(@PathVariable int seminarId) {
		try {
			Seminar seminar = seminarService.getSeminarBySeminarId(BigInteger.valueOf(seminarId));
			SeminarDetailVO seminarDetailVO = new SeminarDetailVO(seminar);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarDetailVO);
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).body(null);
		}
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{seminarId}/topic", method = GET)
	@ResponseBody
	public ResponseEntity getTopicBySeminarId(@PathVariable int seminarId) {
		try{
			List<Topic> listTopic = topicService.listTopicBySeminarId(BigInteger.valueOf(seminarId));
			List<GetTopicVO> getTopicVOS = new ArrayList<GetTopicVO>();
			for (Topic topic : listTopic) {
				List<String> groupList = new ArrayList<String>();
				List<SeminarGroup> seminarGroups = seminarGroupService.listGroupByTopicId(topic.getId());
				for (SeminarGroup seminarGroup : seminarGroups) {
					String group = seminarGroup.getId().toString();
					groupList.add(group);
				}
				GetTopicVO getTopicVO = new GetTopicVO(topic.getId(), topic.getSerial(), topic.getName(),
						topic.getDescription(), topic.getGroupNumberLimit(), topic.getGroupStudentLimit(), groupList);
				getTopicVOS.add(getTopicVO);
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(getTopicVOS);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (TopicNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{seminarId}/topic", method = POST)
	public ResponseEntity addTopicBySeminarId(@PathVariable int seminarId, HttpServletRequest httpServletRequest) throws IOException {
		BufferedReader br = httpServletRequest.getReader();
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String str, wholeStr = "";
		while((str = br.readLine()) != null){
			wholeStr += str;
		}
		AddTopicVO addTopicVO = new AddTopicVO(wholeStr);
		System.out.println(addTopicVO.toString());
		Topic topic = new Topic(addTopicVO);
		BigInteger id = topicService.insertTopicBySeminarId(BigInteger.valueOf(seminarId), topic);
		Map<String, BigInteger> result = new HashMap<String, BigInteger>();
		result.put("id", id);
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
	}


	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{seminarId}/group", method = GET)
	@ResponseBody
	public ResponseEntity getGroupBySeminarId(@PathVariable int seminarId) {
		try {
			List<SeminarGroup> seminarGroups = seminarGroupService.listSeminarGroupBySeminarId(BigInteger.valueOf(seminarId));
			List<SeminarGradeVO> seminarGradeVOS = new ArrayList<SeminarGradeVO>();
			for (SeminarGroup seminarGroup : seminarGroups) {
				List<SeminarGroupTopic> seminarGroupTopics = topicService.listSeminarGroupTopicByGroupId(seminarGroup.getId());
				System.out.println(seminarGroupTopics);
				for (SeminarGroupTopic seminarGroupTopic : seminarGroupTopics) {
					Topic topic = topicService.getTopicByTopicId(seminarGroupTopic.getTopic().getId());
					SeminarGradeVO seminarGradeVO = new SeminarGradeVO(seminarGroup, topic);
					seminarGradeVOS.add(seminarGradeVO);
				}
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGradeVOS);
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (TopicNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{seminarId}/group/my", method = GET)
	@ResponseBody
	public ResponseEntity getMyGroupBySeminarId(@PathVariable int seminarId){
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			Seminar seminar = seminarService.getSeminarBySeminarId(BigInteger.valueOf(seminarId));
			SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupById(BigInteger.valueOf(seminarId), userId);
			List<User> member = seminarGroupService.listSeminarGroupMemberByGroupId(seminarGroup.getId());
			UserVO leader = new UserVO(seminarGroup.getLeader());
			List<UserVO> members = new ArrayList<UserVO>();
			for (User user : member) {
				UserVO userVO = new UserVO(user);
				members.add(userVO);
			}
			List<SeminarGroupTopic> list = topicService.listSeminarGroupTopicByGroupId(seminarGroup.getId());
			List<TopicVO> topics = new ArrayList<TopicVO>();
			for (SeminarGroupTopic seminarGroupTopic : list) {
				TopicVO topicVO = new TopicVO(seminarGroupTopic.getTopic().getId(), seminarGroupTopic.getTopic().getName());
				topics.add(topicVO);
			}
			MyGroupVO myGroupVO = new MyGroupVO(seminarGroup.getId(), seminarGroup.getId().toString(), leader, members, topics);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(myGroupVO);
		} catch (GroupNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (SeminarNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}
}
