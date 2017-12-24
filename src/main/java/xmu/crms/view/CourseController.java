package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.service.ClassService;
import xmu.crms.service.CourseService;
import xmu.crms.service.GradeService;
import xmu.crms.service.SeminarService;
import xmu.crms.view.vo.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Course Controller
 * @author wang
 *
 */

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	GradeService gradeService;
	//@Autowired
	CourseService courseService;
	//@Autowired
	ClassService classService;
	//@Autowired
	SeminarService seminarService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
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

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(method = POST)
	@ResponseBody
	public ResponseEntity createCourse(@RequestBody CourseVO courseVO) {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date startTime = null;
//		Date endTime = null;
//		try {
//			startTime = simpleDateFormat.parse(courseVO.getStartTime());
//			endTime = simpleDateFormat.parse(courseVO.getEndTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		Course course = new Course(null, courseVO.getName(), startTime, endTime,
//				null, courseVO.getDescription(), courseVO.getProportions().getReport(), courseVO.getProportions().getPresentation(),
//				courseVO.getProportions().getA(), courseVO.getProportions().getB(), courseVO.getProportions().getC());

		String a = "{\n" +
				"  \"id\": 23\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{courseId}", method = GET)
	@ResponseBody
	public ResponseEntity getCourseById(@PathVariable int courseId) {
//		GetCourseVO getCourseVO = null;
//		try {
//			Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
//			getCourseVO = new GetCourseVO(course.getId().intValue(), course.getName(), course.getDescription(),
//					course.getTeacher().getName(), course.getTeacher().getEmail());
//		} catch (CourseNotFoundException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(404).body(null);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(400).body(null);
//		}

		String a = "{\n" +
				"  \"id\": 23,\n" +
				"  \"name\": \"OOAD\",\n" +
				"  \"description\": \"面向对象分析与设计\",\n" +
				"  \"teacherName\": \"邱明\",\n" +
				"  \"teacherEmail\": \"mingqiu@xmu.edu.cn\"\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
		
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateCourseById(@PathVariable int courseId, @RequestBody CourseVO courseVO) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = simpleDateFormat.parse(courseVO.getStartTime());
			endTime = simpleDateFormat.parse(courseVO.getEndTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Course course = new Course(null, courseVO.getName(), startTime, endTime,
				null, courseVO.getDescription(), courseVO.getProportions().getReport(), courseVO.getProportions().getPresentation(),
				courseVO.getProportions().getA(), courseVO.getProportions().getB(), courseVO.getProportions().getC());
		courseService.updateCourseByCourseId(BigInteger.valueOf(courseId), course);
		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteCourseById(@PathVariable int courseId) {
		try{
			courseService.deleteCourseByCourseId(BigInteger.valueOf(courseId));
			return ResponseEntity.status(204).build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{courseId}/class", method = GET)
	@ResponseBody
	public ResponseEntity getClassListByCourseId(@PathVariable int courseId) {
		List<ClassVO> list = new ArrayList<ClassVO>();
		List<ClassInfo> listClass = new ArrayList<ClassInfo>();
//		try {
//			listClass = classService.listClassByCourseId(BigInteger.valueOf(courseId));
//		} catch (CourseNotFoundException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(404).body(null);
//		}
//		for (ClassInfo listClas : listClass) {
//			ClassVO classVO = new ClassVO(listClas.getId(), listClas.getName());
//			list.add(classVO);
//		}
		for (int i=0; i<1; i++) {
			ClassVO classVO = new ClassVO(BigInteger.valueOf(1), "周三12节");
			list.add(classVO);
		}
		ClassVO classVO = new ClassVO(BigInteger.valueOf(1), "周三12节");
		String a = "[\n" +
				"  {\n" +
				"    \"id\": 1,\n" +
				"    \"name\": \"周三1-2节\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 2,\n" +
				"    \"name\": \"周三3-4节\"\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);

	}


	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}/class", method = POST)
	@ResponseBody
	public ResponseEntity createClassByCourseId(@PathVariable int courseId, @RequestBody ClassCreateVO classCreateVO) {
		ClassInfo classInfo = new ClassInfo();
		String a = "{\n" +
				"  \"id\": 45\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{courseId}/seminar", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarsByCourseId(@PathVariable int courseId) {
//		List<Seminar> listSeminar = new ArrayList<Seminar>();
//		try {
//			listSeminar = seminarService.listSeminarByCourseId(BigInteger.valueOf(courseId));
//		} catch (CourseNotFoundException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(404).body(null);
//		}

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
				"    \"groupingMethod\": \"random\",\n" +
				"    \"startTime\": \"2017-10-10\",\n" +
				"    \"endTime\": \"2017-10-24\",\n" +
				"    \"grade\": 5\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}/seminar/create", method = POST)
	@ResponseBody
	public ResponseEntity createSeminarByCourseId(@PathVariable int courseId, @RequestBody SeminarVO seminarVO) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = simpleDateFormat.parse(seminarVO.getStartTime());
			endTime = simpleDateFormat.parse(seminarVO.getEndTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Seminar seminar = new Seminar(null, seminarVO.getName(), seminarVO.getDescription(),
				null, seminarVO.getGroupingMethod().equals("fixed"), startTime, endTime);
		try {
			seminarService.insertSeminarByCourseId(BigInteger.valueOf(courseId), seminar);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
		}
		String a = "{\n" +
				"  \"id\": 32\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
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

	@PreAuthorize("hasRole('TEACHER')")
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
