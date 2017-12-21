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

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@RequestMapping(method = POST)
	@ResponseBody
	public ResponseEntity createCourse() {

		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}
	
	@RequestMapping(value = "/{courseId}", method = GET)
	@ResponseBody
	public ResponseEntity getCourseById(@PathVariable int courseId) {

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
		
	}
	
	@RequestMapping(value = "/{courseId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateCourseById(@PathVariable int courseId) {

		return ResponseEntity.status(204).build();

	}
	
	@RequestMapping(value = "/{courseId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteCourseById(@PathVariable int courseId) {

		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value = "/{courseId}/class", method = GET)
	@ResponseBody
	public ResponseEntity getClassListByCourseId(@PathVariable int courseId) {

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);

	}
	
	
	@RequestMapping(value = "/{courseId}/class", method = POST)
	@ResponseBody
	public ResponseEntity createClassByCourseId(@PathVariable int courseId) {

		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarsByCourseId(@PathVariable int courseId) {

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}
	
	@RequestMapping(value = "/{courseId}/seminar", method = POST)
	@ResponseBody
	public ResponseEntity createSeminarByCourseId(@PathVariable int courseId) {

		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@RequestMapping(value = "/{courseId}/seminar/current", method = GET)
	@ResponseBody
	public ResponseEntity getCurrentSeminarByCourseId(@PathVariable int courseId){

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@RequestMapping(value = "/{courseId}/grade", method = GET)
	@ResponseBody
	public ResponseEntity getAllGradeByCourseId(@PathVariable int courseId){

		List<SeminarGroup> seminarGroups = gradeService.listSeminarGradeByCourseId(BigInteger.valueOf(courseId));
		if (seminarGroups == null) {
			return ResponseEntity.status(404).body(null);
		}else{
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGroups);
		}
	}
}
