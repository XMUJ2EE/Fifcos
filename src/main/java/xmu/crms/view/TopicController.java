package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping("/topic")

public class TopicController {
//	@Autowired
//	TopicService topicService = new TopicServiceImpl();

	@RequestMapping(value = "/{topicId}", method = GET)
	@ResponseBody
	public ResponseEntity getTopicById(@PathVariable int topicId) {

//		if (topicId == 0)
//			return ResponseEntity.status(400).body(null);
//
//		Topic topic = topicService.getTopicById(topicId);
//
//		if (topic != null){
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(topic);
//		}else{
//			return ResponseEntity.status(404).body(null);
//		}
		String topic = "{\n" +
				"  \"id\": 257,\n" +
				"  \"serial\": \"A\",\n" +
				"  \"name\": \"领域模型与模块\",\n" +
				"  \"description\": \"Domain model与模块划分\",\n" +
				"  \"groupLimit\": 5,\n" +
				"  \"groupMemberLimit\": 6,\n" +
				"  \"groupLeft\": 2\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(topic);
	}
	
	@RequestMapping(value = "/{topicId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateTopicById(@PathVariable int topicId) {
//		if (topicId <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (topicService.updateTopicById(topicId, topicPartVO)) {
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(404).body(null);
//		}
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{topicId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteTopicById(@PathVariable int topicId) {

//		if (topicId <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (topicService.deleteTopicById(topicId)) {
//			return ResponseEntity.status(204).body(null);
//		}else{
//			return ResponseEntity.status(404).body(null);
//		}
		return ResponseEntity.status(204).body(null);
	}
	
	@RequestMapping(value = "/{topicId}/group", method = GET)
	@ResponseBody
	public ResponseEntity getGroupsByTopicId(@PathVariable int topicId) {

//		List<GroupVO> groupVOS = topicService.getGroupsByTopicId(topicId);
//		if (topicId <= 0)
//			return ResponseEntity.status(400).body(null);
//		if (groupVOS != null) {
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupVOS);
//		}else{
//			return ResponseEntity.status(404).body(null);
//		}
		String group = "[\n" +
				"  {\n" +
				"    \"id\": 23,\n" +
				"    \"name\": \"1A1\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 26,\n" +
				"    \"name\": \"2A2\"\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(group);
	}
}
