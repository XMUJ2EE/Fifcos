package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xmu.crms.entity.Course;
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
	public ResponseEntity<List<Class>> getClassListByCourseId(@PathVariable int courseId) {
		List<Class> classes = courseService.getClassListByCourseId(courseId);

		
		return null;
		
	}
	
	
	@RequestMapping(value = "/{courseId}/class", method = POST)
	
	public String createClassByCourseId(@PathVariable int id, Model model) {
		
		
		return null;
		
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = GET)
	
	public String getSeminarsByCourseId(@PathVariable int id, Model model) {
		
		
		return null;
		
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = POST)
	
	public String createSeminarByCourseId(@PathVariable int id, Model model) {
		
		
		return null;
		
	}
}
