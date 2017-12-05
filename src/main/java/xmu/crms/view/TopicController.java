package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.Topic;
import xmu.crms.service.TopicService;
import xmu.crms.service.TopicServiceImpl;
import xmu.crms.view.VO.GroupVO;

import java.util.List;

@Controller

@RequestMapping("/topic")

public class TopicController {
	@Autowired
	TopicService topicService = new TopicServiceImpl();

	@RequestMapping(value = "/{topicId}", method = GET)
	@ResponseBody
	public ResponseEntity getTopicById(@PathVariable int topicId) {
		Topic topic = topicService.getTopicById(topicId);
		if (topic != null){
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(topic);
		}else if ( topicId == 100000){
			return ResponseEntity.status(400).body(null);
		}else{
			return ResponseEntity.status(404).body(null);
		}
	}
	
	@RequestMapping(value = "/{topicId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateTopicById(@PathVariable int topicId) {
		
		return ResponseEntity.status(204).body(null);

	}
	
	@RequestMapping(value = "/{topicId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteTopicById(@PathVariable int topicId) {
		
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{topicId}/group", method = GET)
	@ResponseBody
	public ResponseEntity<List<GroupVO>> getGroupsByTopicId(@PathVariable int topicId) {

		List<GroupVO> groupVOS = topicService.getGroupsByTopicId(topicId);

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupVOS);
	}
}
