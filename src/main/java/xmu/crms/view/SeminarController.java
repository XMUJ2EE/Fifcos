package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.Topic;
import xmu.crms.view.vo.*;

@Controller

@RequestMapping("/seminar")

public class SeminarController {
	
	@RequestMapping(value = "/{seminarId}", method = GET)
	@ResponseBody
	public ResponseEntity<Seminar> getSeminarById(@PathVariable int id) {
		Seminar seminar = new Seminar();
//		{
//			"id": 32,
//				"name": "概要设计",
//				"description": "模型层与数据库设计",
//				"groupingMethod": "fixed",
//				"startTime": "2017-10-10",
//				"endTime": "2017-10-24",
//				"topics": [
//			{
//				"id": 257,
//					"name": "领域模型与模块"
//			}
//  ]
//		}
		TopicVO[] topics = new TopicVO[1];
		TopicVO topicVO = new TopicVO(257,"领域模型与模块");
		topics[0] = topicVO;

		seminar.setId(32);
		seminar.setName("概要设计");
		seminar.setDescription("模型层与数据库设计");
		seminar.setGroupingMethod("fixed");
		seminar.setStartTime("2017-10-10");
		seminar.setEndTime("2017-10-24");
		seminar.setTopics(topics);
		return ResponseEntity.status(200).body(seminar);
	}
	
	@RequestMapping(value = "/{seminarId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateSeminarById(@PathVariable int id) {

		// 400 错误id格式
		// 404 未找到讨论课

		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{seminarId}", method = DELETE)
	public ResponseEntity deleteSeminarById(@PathVariable int id) {

		// 400
		// 403
		// 404

		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{seminarId}/my", method = GET)
	public ResponseEntity getSeminarWithMe(@PathVariable int id) {

//		{
//			"id": 32,
//				"name": "概要设计",
//				"groupingMethod": "random",
//				"courseName": "OOAD",
//				"startTime": "2017-10-11",
//				"endTime": "2017-10-24",
//				"classCalling": 23,
//				"isLeader": true,
//				"areTopicsSeletced": true
//		}
		StudentSeminarVO seminar = new StudentSeminarVO();

		seminar.setId(32);
		seminar.setName("概要设计");
		seminar.setGroupingMethod("random");
		seminar.setCourseName("OOAD");
		seminar.setStartTime("2017-10-10");
		seminar.setEndTime("2017-10-24");
		seminar.setClassColling(23);
		seminar.setLeader(true);
		seminar.setAreTopicsSeletced(true);
		return ResponseEntity.status(200).body(seminar);
	}

	@RequestMapping(value = "/{seminarId}/detail", method = GET)
	@ResponseBody
	public ResponseEntity<SeminarDetail> getSeminarDetail(@PathVariable int id) {
//		{
//			"id": 32,
//				"name": "概要设计",
//				"startTime": "2017-10-10",
//				"endTime": "2017-10-24",
//				"site": "海韵201",
//				"teacherName": "邱明",
//				"teacherEmail": "mingqiu@xmu.edu.cn"
//		}
		SeminarDetail seminarDetail = new SeminarDetail();
		seminarDetail.setId(32);
		seminarDetail.setName("概要设计");
		seminarDetail.setStartTime("2017-10-10");
		seminarDetail.setEndTime("2017-10-24");
		seminarDetail.setSite("海韵201");
		seminarDetail.setTeacherName("邱明");
		seminarDetail.setTeacherEmail("mingqiu@xmu.edu.cn");

		return ResponseEntity.status(200).body(seminarDetail);
	}

	@RequestMapping(value = "/{seminarId}/topic", method = GET)
	@ResponseBody
	public ResponseEntity getTopicBySeminarId(@PathVariable int id) {
//		[
//		{
//			"id": 257,
//				"serial": "A",
//				"name": "领域模型与模块",
//				"description": "Domain model与模块划分",
//				"groupLimit": 5,
//				"groupMemberLimit": 6,
//				"groupLeft": 2
//		}
//]
		Topic[] topics = new Topic[1];
		Topic topic = new Topic();
		topic.setId(257);
		topic.setSerial("A");
		topic.setName("领域模型与模块");
		topic.setDescription("Domain model与模块划分");
		topic.setGroupLimit(5);
		topic.setGroupMenmberLimit(6);
		topic.setGroupLeft(2);
		topics[0]=topic;
		return ResponseEntity.status(200).body(topics);
	}

	@RequestMapping(value = "/{seminarId}/topic", method = POST)
	public ResponseEntity addTopicBySeminarId(@PathVariable int id){
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object(){public int id=257;});
	}
	
	@RequestMapping(value = "/{seminarId}/group", method = GET)
	@ResponseBody
	public ResponseEntity<GroupTopicVO[]> getGroupBySeminarId(@PathVariable int id,
															  @RequestParam(value = "gradeable",required = false)Boolean gradeable,
															  @RequestParam(value = "classId", required = false)Integer classId) {

		GroupTopicVO[] groupTopicVO = new GroupTopicVO[2];
		TopicVO[] topicVOS = new TopicVO[1];

		topicVOS[0].setId(257);
		topicVOS[0].setName("领域模型与模块");

		groupTopicVO[0].setId(28);
		groupTopicVO[0].setName("1A1");
		groupTopicVO[0].setTopics(topicVOS);

		groupTopicVO[0].setId(29);
		groupTopicVO[0].setName("1A2");
		groupTopicVO[0].setTopics(topicVOS);

		
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupTopicVO);
	}

	@RequestMapping(value = "/{seminarId}/group/my", method = GET)
	@ResponseBody
	public ResponseEntity getMyGroupBySeminarId(@PathVariable int id){
		GroupDetailVO groupDetailVO = new GroupDetailVO();
		UserVO leader = new UserVO(8888,"张三");
		UserVO[] members = new UserVO[2];
		members[0].setId(5324);
		members[0].setName("李四");
		members[1].setId(5678);
		members[1].setName("王五");

		groupDetailVO.setId(28);
		groupDetailVO.setLeader(leader);
		groupDetailVO.setMembers(members);

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetailVO);
	}



}
