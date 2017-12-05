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
	public ResponseEntity getSeminarById(@PathVariable int seminarId) {
		String seminar = "{\n" +
				"  \"id\": 32,\n" +
				"  \"name\": \"概要设计\",\n" +
				"  \"description\": \"模型层与数据库设计\",\n" +
				"  \"groupingMethod\": \"fixed\",\n" +
				"  \"startTime\": \"2017-10-10\",\n" +
				"  \"endTime\": \"2017-10-24\",\n" +
				"  \"topics\": [\n" +
				"    {\n" +
				"      \"id\": 257,\n" +
				"      \"name\": \"领域模型与模块\"\n" +
				"    }\n" +
				"  ]\n" +
				"}";
		return ResponseEntity.status(200).body(seminar);
	}
	
	@RequestMapping(value = "/{seminarId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateSeminarById(@PathVariable int seminarId) {

		// 400 错误id格式
		// 404 未找到讨论课

		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{seminarId}", method = DELETE)
	public ResponseEntity deleteSeminarById(@PathVariable int seminarId) {

		// 400
		// 403
		// 404

		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{seminarId}/my", method = GET)
	public ResponseEntity getSeminarWithMe(@PathVariable int seminarId) {

		String seminar = "{\n" +
				"  \"id\": 32,\n" +
				"  \"name\": \"概要设计\",\n" +
				"  \"groupingMethod\": \"random\",\n" +
				"  \"courseName\": \"OOAD\",\n" +
				"  \"startTime\": \"2017-10-11\",\n" +
				"  \"endTime\": \"2017-10-24\",\n" +
				"  \"classCalling\": 23,\n" +
				"  \"isLeader\": true,\n" +
				"  \"areTopicsSeletced\": true\n" +
				"}";
		return ResponseEntity.status(200).body(seminar);
	}

	@RequestMapping(value = "/{seminarId}/detail", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarDetail(@PathVariable int seminarId) {
		String seminarDetail = "{\n" +
				"  \"id\": 32,\n" +
				"  \"name\": \"概要设计\",\n" +
				"  \"startTime\": \"2017-10-10\",\n" +
				"  \"endTime\": \"2017-10-24\",\n" +
				"  \"site\": \"海韵201\",\n" +
				"  \"teacherName\": \"邱明\",\n" +
				"  \"teacherEmail\": \"mingqiu@xmu.edu.cn\"\n" +
				"}";

		return ResponseEntity.status(200).body(seminarDetail);
	}

	@RequestMapping(value = "/{seminarId}/topic", method = GET)
	@ResponseBody
	public ResponseEntity getTopicBySeminarId(@PathVariable int seminarId) {

		String topics = "[\n" +
				"  {\n" +
				"    \"id\": 257,\n" +
				"    \"serial\": \"A\",\n" +
				"    \"name\": \"领域模型与模块\",\n" +
				"    \"description\": \"Domain model与模块划分\",\n" +
				"    \"groupLimit\": 5,\n" +
				"    \"groupMemberLimit\": 6,\n" +
				"    \"groupLeft\": 2\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).body(topics);
	}

	@RequestMapping(value = "/{seminarId}/topic", method = POST)
	public ResponseEntity addTopicBySeminarId(@PathVariable int seminarId){
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object(){public int id=257;});
	}
	
	@RequestMapping(value = "/{seminarId}/group", method = GET)
	@ResponseBody
	public ResponseEntity getGroupBySeminarId(@PathVariable int seminarId,
															  @RequestParam(value = "gradeable",required = false)Boolean gradeable,
															  @RequestParam(value = "classId", required = false)Integer classId) {

		String groupTopicVO = "[\n" +
				"  {\n" +
				"    \"id\": 28,\n" +
				"    \"name\": \"1A1\",\n" +
				"    \"topics\": [\n" +
				"      {\n" +
				"        \"id\": 257,\n" +
				"        \"name\": \"领域模型与模块\"\n" +
				"      }\n" +
				"    ]\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 29,\n" +
				"    \"name\": \"1A2\",\n" +
				"    \"topics\": [\n" +
				"      {\n" +
				"        \"id\": 257,\n" +
				"        \"name\": \"领域模型与模块\"\n" +
				"      }\n" +
				"    ]\n" +
				"  }\n" +
				"]";

		
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupTopicVO);
	}

	@RequestMapping(value = "/{seminarId}/group/my", method = GET)
	@ResponseBody
	public ResponseEntity getMyGroupBySeminarId(@PathVariable int seminarId){
		String groupDetailVO = "{\n" +
				"  \"id\": 28,\n" +
				"  \"name\": 28,\n" +
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
				"  ]\n" +
				"}";

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetailVO);
	}



}
