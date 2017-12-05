package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xmu.crms.entity.Class;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;
import xmu.crms.view.vo.SeminarGradeDetail;
import xmu.crms.service.CourseService;
import xmu.crms.service.CourseServiceImpl;

/**
 * Course Controller
 * @author Xuezhang.Liu
 *
 */

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService courseService = new CourseServiceImpl();

	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity<List<Course>> getUserCourses() {
		int id = 123;

		List<Course> courses = courseService.getCourseListById(id);
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(courses);
	}

	@RequestMapping(method = POST)
	@ResponseBody
	public ResponseEntity createCourse(@RequestBody Course course) {
		int id = 123;

		int courseId = courseService.addCourse(id, course);
		if(0 != courseId) {
			return ResponseEntity.created(URI.create("/course")).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object() {
				public int id = courseId;
			});
		}else{
			return ResponseEntity.status(403).body(null);
		}
	}
	
	@RequestMapping(value = "/{courseId}", method = GET)
	@ResponseBody
	public ResponseEntity<Course> getCourseById(@PathVariable int courseId) {

		if(0 == courseId){
			//错误的ID格式
			return ResponseEntity.status(400).body(null);
		}
		Course course = courseService.getCourseById(courseId);
		if(null != course) {
			//
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(course);
		}else{
			// 未找到课程
			return ResponseEntity.status(404).body(null);
		}
		
	}
	
	@RequestMapping(value = "/{courseId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateCourseById(@PathVariable int courseId, @RequestBody Course course) {

		Boolean result = courseService.updateCourseById(courseId, course);
		if(true == result){
			// 修改成功
			return ResponseEntity.status(204).body(null);
		}else{
			// 用户权限不足
			return ResponseEntity.status(403).body(null);
		}
	}
	
	@RequestMapping(value = "/{courseId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteCourseById(@PathVariable int courseId) {
		int userId = 123;
		if(0 == courseId){
			// id格式错误
			return ResponseEntity.status(400).build();
		}
		Boolean result = courseService.deleteCourseById(userId, courseId);
		if(true == result){
			// 删除成功
			return ResponseEntity.status(204).body(null);
		}else{
			// 未找到课程
			return ResponseEntity.status(404).body(null);
		}
	}
	
	@RequestMapping(value = "/{courseId}/class", method = GET)
	@ResponseBody
	public ResponseEntity<List<Map<String, Object>>> getClassListByCourseId(@PathVariable int courseId) {
		if(0 == courseId){
			// 错误的id格式
			return ResponseEntity.status(400).build();
		}
		List<Map<String, Object>> classes = courseService.getClassListByCourseId(courseId);
		if(null != classes){
			return ResponseEntity.status(200).body(classes);
		}else{
			// 未找到
			return ResponseEntity.status(404).build();
		}

	}
	
	
	@RequestMapping(value = "/{courseId}/class", method = POST)
	@ResponseBody
	public ResponseEntity createClassByCourseId(@PathVariable int courseId, @RequestBody Class myClass) {

		// 权限认证 403

		int result = courseService.addClassByCourseId(courseId, myClass);
		if(0 == result){
			// 未找到课程
			return ResponseEntity.status(404).build();
		}else{
			// 成功
			return ResponseEntity.status(201).body(new Object(){public int id=result;});
		}
		
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = GET)
	@ResponseBody
	public ResponseEntity<List<Seminar>> getSeminarsByCourseId(@PathVariable int courseId) {
		// 权限认证

		List<Seminar> seminars = courseService.getSeminarListByCourseId(courseId);
		if(null != seminars){
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminars);
		}else{
			// 未找到课程
			return ResponseEntity.status(404).build();
		}
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = POST)
	@ResponseBody
	public ResponseEntity createSeminarByCourseId(@PathVariable int courseId, @RequestBody Seminar seminar) {
		// 权限认证

		int result = courseService.addSeminarByCourseId(courseId, seminar);
		if(0 == result){
			return ResponseEntity.status(404).build();
		}else{
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object(){public int id=result;});
		}
	}

	@RequestMapping(value = "/{courseId}/seminar/current", method = GET)
	@ResponseBody
	public ResponseEntity<Seminar> getCurrentSeminarByCourseId(@PathVariable int courseId){
		// 权限认证

		Seminar seminar = courseService.getCurrentSeminarByCourseId(courseId);
		if(null != seminar){
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminar);
		}else{
			return ResponseEntity.status(404).build();
		}
	}

	@RequestMapping(value = "/{courseId}/grade", method = GET)
	@ResponseBody
	public ResponseEntity getAllGradeByCourseId(@PathVariable int courseId){
		// 权限认证

		int userId = 123;
		List<SeminarGradeDetail> seminarGradeDetails = courseService.getAllSeminarGradeByCourseId(courseId,userId);
		if(null == seminarGradeDetails){
			return ResponseEntity.status(404).build();
		}else{
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGradeDetails);
		}
	}
}
