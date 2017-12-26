package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.Topic;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.TopicService;
import xmu.crms.view.vo.GetTopicVO;
import xmu.crms.view.vo.GroupVO;
import xmu.crms.view.vo.TopicDetailVO;
import xmu.crms.view.vo.TopicUpdateVO;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 */

@Controller


@RequestMapping("/topic")

public class TopicController {
	@Autowired
	TopicService topicService;

	@Autowired
	SeminarGroupService seminarGroupService;

	@PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
	@RequestMapping(value = "/{topicId}", method = GET)
	@ResponseBody
	public ResponseEntity getTopicById(@PathVariable BigInteger topicId) {
		try {
			Topic topic = topicService.getTopicByTopicId(topicId);
			List<SeminarGroup> seminarGroups = seminarGroupService.listGroupByTopicId(topicId);
			System.out.println(seminarGroups.size());
			Integer groupLeft = topic.getGroupNumberLimit()-seminarGroups.size();
			System.out.println(groupLeft.toString());
			List<String> groupList = new ArrayList<String>();
			for (SeminarGroup seminarGroup : seminarGroups) {
				String group = seminarGroup.getId().toString();
				groupList.add(group);
			}
			TopicDetailVO topicDetailVO = new TopicDetailVO(topic, groupLeft, groupList);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(topicDetailVO);
		} catch (TopicNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}
	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{topicId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateTopicById(@PathVariable BigInteger topicId,
										  HttpServletRequest httpServletRequest) throws IOException {
		try{
			BufferedReader br = httpServletRequest.getReader();
			BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String str, wholeStr = "";
			while((str = br.readLine()) != null){
				wholeStr += str;
			}
			TopicUpdateVO topicUpdateVO = new TopicUpdateVO(wholeStr);
			Topic topic = new Topic(topicUpdateVO);
			topicService.updateTopicByTopicId(topicId, topic);
			return ResponseEntity.status(204).build();
		}catch (TopicNotFoundException e){
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{topicId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteTopicById(@PathVariable BigInteger topicId) {
		try{
			Topic topic = topicService.getTopicByTopicId(topicId);
			topicService.deleteTopicByTopicId(topicId);
			return ResponseEntity.status(204).build();
		}catch (TopicNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{topicId}/group", method = GET)
	@ResponseBody
	public ResponseEntity getGroupsByTopicId(@PathVariable BigInteger topicId) {
		try{
			Topic topic = topicService.getTopicByTopicId(topicId);
			List<SeminarGroup> seminarGroups = seminarGroupService.listGroupByTopicId(topicId);
			List<GroupVO> groupVOS = new ArrayList<>();
			for(SeminarGroup seminarGroup:seminarGroups){
				groupVOS.add(new GroupVO(seminarGroup));
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupVOS);
		}catch (TopicNotFoundException e) {
			return ResponseEntity.status(404).build();
		}
	}
}
