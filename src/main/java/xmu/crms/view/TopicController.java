package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.Topic;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.TopicService;
import xmu.crms.view.vo.TopicDetailVO;

import java.math.BigInteger;
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
			TopicDetailVO topicDetailVO = new TopicDetailVO(topic, groupLeft);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(topicDetailVO);
		} catch (TopicNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}
	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{topicId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateTopicById(@PathVariable int topicId) {

		return ResponseEntity.status(204).body(null);
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{topicId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteTopicById(@PathVariable int topicId) {

		return ResponseEntity.status(204).body(null);
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{topicId}/group", method = GET)
	@ResponseBody
	public ResponseEntity getGroupsByTopicId(@PathVariable int topicId) {

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}
}
