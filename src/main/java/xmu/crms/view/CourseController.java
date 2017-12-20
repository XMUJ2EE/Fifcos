package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Course Controller
 * @author Xuezhang.Liu
 *
 */

@Controller
@RequestMapping("/course")
public class CourseController {

//	@Autowired
//	CourseService courseService;

	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getUserCourses() {
		String courses = "[\n" +
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
//		List<Course> courses = courseService.getCourseListById(id);
//		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(courses);
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(courses);
	}

	@RequestMapping(method = POST)
	@ResponseBody
	public ResponseEntity createCourse() {
//		int id = 123;

//		int courseId = courseService.addCourse(id, course);
//		if(0 != courseId) {
//			return ResponseEntity.created(URI.create("/course")).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object() {
//				public int id = courseId;
//			});
//		}else{
//			return ResponseEntity.status(403).body(null);
//		}
		String res = "{\n" +
				"  \"id\": 23\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(res);
	}
	
	@RequestMapping(value = "/{courseId}", method = GET)
	@ResponseBody
	public ResponseEntity getCourseById(@PathVariable int courseId) {

//		if(0 == courseId){
//			//错误的ID格式
//			return ResponseEntity.status(400).body(null);
//		}
//		Course course = courseService.getCourseById(courseId);
//		if(null != course) {
//			//
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(course);
//		}else{
//			// 未找到课程
//			return ResponseEntity.status(404).body(null);
//		}
		String course = "{\n" +
				"  \"id\": 23,\n" +
				"  \"name\": \"OOAD\",\n" +
				"  \"description\": \"面向对象分析与设计\",\n" +
				"  \"teacherName\": \"邱明\",\n" +
				"  \"teacherEmail\": \"mingqiu@xmu.edu.cn\"\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(course);
		
	}
	
	@RequestMapping(value = "/{courseId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateCourseById(@PathVariable int courseId) {

//		Boolean result = courseService.updateCourseById(courseId, course);
//		if(true == result){
//			// 修改成功
//			return ResponseEntity.status(204).body(null);
//		}else{
//			// 用户权限不足
//			return ResponseEntity.status(403).body(null);
//		}
		return ResponseEntity.status(204).build();

	}
	
	@RequestMapping(value = "/{courseId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteCourseById(@PathVariable int courseId) {
//		int userId = 123;
//		if(0 == courseId){
//			// id格式错误
//			return ResponseEntity.status(400).build();
//		}
//		Boolean result = courseService.deleteCourseById(userId, courseId);
//		if(true == result){
//			// 删除成功
//			return ResponseEntity.status(204).body(null);
//		}else{
//			// 未找到课程
//			return ResponseEntity.status(404).body(null);
//		}
		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{courseId}/class", method = GET)
	@ResponseBody
	public ResponseEntity getClassListByCourseId(@PathVariable int courseId) {
//		if(0 == courseId){
//			// 错误的id格式
//			return ResponseEntity.status(400).build();
//		}
//		List<Map<String, Object>> classes = courseService.getClassListByCourseId(courseId);
//		if(null != classes){
//			return ResponseEntity.status(200).body(classes);
//		}else{
//			// 未找到
//			return ResponseEntity.status(404).build();
//		}
		String classes = "[\n" +
				"  {\n" +
				"    \"id\": 45,\n" +
				"    \"name\": \"周三1-2节\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 48,\n" +
				"    \"name\": \"周三3-4节\"\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(classes);

	}
	
	
	@RequestMapping(value = "/{courseId}/class", method = POST)
	@ResponseBody
	public ResponseEntity createClassByCourseId(@PathVariable int courseId) {

		// 权限认证 403

//		int result = courseService.addClassByCourseId(courseId, myClass);
//		if(0 == result){
//			// 未找到课程
//			return ResponseEntity.status(404).build();
//		}else{
//			// 成功
//			return ResponseEntity.status(201).body(new Object(){public int id=result;});
//		}
		String a ="{\n" +
				"  \"id\": 45\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarsByCourseId(@PathVariable int courseId) {
//		// 权限认证
//
//		List<Seminar> seminars = courseService.getSeminarListByCourseId(courseId);
//		if(null != seminars){
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminars);
//		}else{
//			// 未找到课程
//			return ResponseEntity.status(404).build();
//		}
		String seminars = "[\n" +
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
				"    \"groupingMethod\": \"random\",\n" +
				"    \"startTime\": \"2017-10-10\",\n" +
				"    \"endTime\": \"2017-10-24\",\n" +
				"    \"grade\": 5\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminars);
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = POST)
	@ResponseBody
	public ResponseEntity createSeminarByCourseId(@PathVariable int courseId) {
		// 权限认证
//
//		int result = courseService.addSeminarByCourseId(courseId, seminar);
//		if(0 == result){
//			return ResponseEntity.status(404).build();
//		}else{
//			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object(){public int id=result;});
//		}
		String a = "{\n" +
				"  \"id\": 32\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}

	@RequestMapping(value = "/{courseId}/seminar/current", method = GET)
	@ResponseBody
	public ResponseEntity getCurrentSeminarByCourseId(@PathVariable int courseId){
		// 权限认证
//
//		Seminar seminar = courseService.getCurrentSeminarByCourseId(courseId);
//		if(null != seminar){
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminar);
//		}else{
//			return ResponseEntity.status(404).build();
//		}
		String seminar = "{\n" +
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
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminar);
	}

	@RequestMapping(value = "/{courseId}/grade", method = GET)
	@ResponseBody
	public ResponseEntity getAllGradeByCourseId(@PathVariable int courseId){
		// 权限认证
//
//		int userId = 123;
//		List<SeminarGradeDetail> seminarGradeDetails = courseService.getAllSeminarGradeByCourseId(courseId,userId);
//		if(null == seminarGradeDetails){
//			return ResponseEntity.status(404).build();
//		}else{
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGradeDetails);
//		}
		String grade = "[\n" +
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
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(grade);
	}
}
