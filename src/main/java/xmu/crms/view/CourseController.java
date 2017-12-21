package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.service.GradeService;

import java.math.BigInteger;
import java.util.List;

/**
 * Course Controller
 * @author Xuezhang.Liu
 *
 */

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	GradeService gradeService;
//	CourseService courseService;

	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getUserCourses() {

		String a = "[\n" +
				"  {\n" +
				"    \"id\": 1,\n" +
				"    \"name\": \"OOAD\",\n" +
				"    \"numClass\": 3,\n" +
				"    \"numStudent\": 60,\n" +
				"    \"startTime\": \"2017-9-1\",\n" +
				"    \"endTime\": \"2018-1-1\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 2,\n" +
				"    \"name\": \"J2EE\",\n" +
				"    \"numClass\": 1,\n" +
				"    \"numStudent\": 60,\n" +
				"    \"startTime\": \"2017-9-1\",\n" +
				"    \"endTime\": \"2018-1-1\"\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}

	@RequestMapping(method = POST)
	@ResponseBody
	public ResponseEntity createCourse() {
		String a = "{\n" +
				"  \"id\": 23\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}
	
	@RequestMapping(value = "/{courseId}", method = GET)
	@ResponseBody
	public ResponseEntity getCourseById(@PathVariable int courseId) {
		String a = "{\n" +
				"  \"id\": 23,\n" +
				"  \"name\": \"OOAD\",\n" +
				"  \"description\": \"面向对象分析与设计\",\n" +
				"  \"teacherName\": \"邱明\",\n" +
				"  \"teacherEmail\": \"mingqiu@xmu.edu.cn\"\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
		
	}
	
	@RequestMapping(value = "/{courseId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateCourseById(@PathVariable int courseId) {
		String a = "";
		return ResponseEntity.status(204).build();

	}
	
	@RequestMapping(value = "/{courseId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteCourseById(@PathVariable int courseId) {
		String a = "";
		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{courseId}/class", method = GET)
	@ResponseBody
	public ResponseEntity getClassListByCourseId(@PathVariable int courseId) {
		String a = "[\n" +
				"  {\n" +
				"    \"id\": 45,\n" +
				"    \"name\": \"周三1-2节\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 48,\n" +
				"    \"name\": \"周三3-4节\"\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);

	}
	
	
	@RequestMapping(value = "/{courseId}/class", method = POST)
	@ResponseBody
	public ResponseEntity createClassByCourseId(@PathVariable int courseId) {
		String a = "{\n" +
				"  \"id\": 45\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarsByCourseId(@PathVariable int courseId) {
		String a = "[\n" +
				"  {\n" +
				"    \"id\": 29,\n" +
				"    \"name\": \"界面原型设计\",\n" +
				"    \"description\": \"界面原型设计\",\n" +
				"    \"groupingMethod\": \"fixed\",\n" +
				"    \"startTime\": \"2017-09-25\",\n" +
				"    \"endTime\": \"2017-10-09\",\n" +
				"    \"grade\": 4\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 32,\n" +
				"    \"name\": \"概要设计\",\n" +
				"    \"description\": \"模型层与数据库设计\",\n" +
				"    \"groupingMethod\": \"fixed\",\n" +
				"    \"startTime\": \"2017-10-10\",\n" +
				"    \"endTime\": \"2017-10-24\",\n" +
				"    \"grade\": 5\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = POST)
	@ResponseBody
	public ResponseEntity createSeminarByCourseId(@PathVariable int courseId) {
		String a = "{\n" +
				"  \"id\": 32\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}

	@RequestMapping(value = "/{courseId}/seminar/current", method = GET)
	@ResponseBody
	public ResponseEntity getCurrentSeminarByCourseId(@PathVariable int courseId){
		String a = "{\n" +
				"  \"id\": 29,\n" +
				"  \"name\": \"界面原型设计\",\n" +
				"  \"courseName\": \"OOAD\",\n" +
				"  \"groupingMethod\": \"fixed\",\n" +
				"  \"startTime\": \"2017-09-25\",\n" +
				"  \"endTime\": \"2017-10-09\",\n" +
				"  \"classes\": [\n" +
				"    {\n" +
				"      \"id\": 53,\n" +
				"      \"name\": \"周三12\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"id\": 57,\n" +
				"      \"name\": \"周三34\"\n" +
				"    }\n" +
				"  ]\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}

	@RequestMapping(value = "/{courseId}/grade", method = GET)
	@ResponseBody
	public ResponseEntity getAllGradeByCourseId(@PathVariable int courseId){

//		List<SeminarGroup> seminarGroups = gradeService.listSeminarGradeByCourseId(BigInteger.valueOf(courseId));
//		if (seminarGroups == null) {
//			return ResponseEntity.status(404).body(null);
//		}else{
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGroups);
//		}
		String a = "[\n" +
				"  {\n" +
				"    \"seminarName\": \"需求分析\",\n" +
				"    \"groupName\": \"3A2\",\n" +
				"    \"leaderName\": \"张三\",\n" +
				"    \"presentationGrade\": 3,\n" +
				"    \"reportGrade\": 4,\n" +
				"    \"grade\": 4\n" +
				"  },\n" +
				"  {\n" +
				"    \"seminarName\": \"界面原型设计\",\n" +
				"    \"groupName\": \"3A3\",\n" +
				"    \"leaderName\": \"张三\",\n" +
				"    \"presentationGrade\": 4,\n" +
				"    \"reportGrade\": 4,\n" +
				"    \"grade\": 4\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}
}
