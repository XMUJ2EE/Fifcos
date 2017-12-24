package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;
import xmu.crms.dao.ClassDao;
import xmu.crms.dao.CourseDao;
import xmu.crms.entity.*;
import xmu.crms.exception.ClazzNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.*;
import xmu.crms.view.vo.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Course Controller
 * @author wang
 *
 */

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired(required = false)
	GradeService gradeService;
	@Autowired(required = false)
	CourseService courseService;
	@Autowired(required = false)
	ClassService classService;
	@Autowired(required = false)
	SeminarService seminarService;
	@Autowired(required = false)
	UserService userService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getUserCourses() {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UserCourseVO> userCourseVOList = new ArrayList<UserCourseVO>();
		try {
			List<Course> listCourse = courseService.listCourseByUserId(userId);
			for (Course aListCourse : listCourse) {
				List<ClassInfo> classInfoList = classService.listClassByCourseId(aListCourse.getId());
				List<User> userList = new ArrayList<User>();
				for (ClassInfo aClassInfoList : classInfoList) {
					userList.addAll(userService.listUserByClassId(aClassInfoList.getId(), "", ""));
				}
				UserCourseVO userCourseVO = new UserCourseVO(aListCourse, classInfoList.size(), userList.size());
				userCourseVOList.add(userCourseVO);
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(userCourseVOList);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		} catch (ClazzNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}


	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(method = POST)
	@ResponseBody
	public ResponseEntity createCourse(@RequestBody CourseVO courseVO) {
		Course course = new Course(courseVO);
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int id = courseService.insertCourseByUserId(userId, course).intValue();
		Map<String, Integer> result = null;
		result.put("id", id);
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
	}


	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{courseId}", method = GET)
	@ResponseBody
	public ResponseEntity getCourseById(@PathVariable int courseId) {
		GetCourseVO getCourseVO = null;
		try {
			Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
			getCourseVO = new GetCourseVO(course);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(getCourseVO);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateCourseById(@PathVariable int courseId, @RequestBody CourseVO courseVO) {
		Course course = new Course(courseVO);
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
		List<ClassInfo> listClass;
		try {
			listClass = classService.listClassByCourseId(BigInteger.valueOf(courseId));
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
		for (ClassInfo listClas : listClass) {
			ClassVO classVO = new ClassVO(listClas.getId(), listClas.getName());
			list.add(classVO);
		}
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(list);
	}


	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}/class", method = POST)
	@ResponseBody
	public ResponseEntity createClassByCourseId(@PathVariable int courseId, @RequestBody ClassCreateVO classCreateVO) throws UserNotFoundException {
		int id;
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Integer> result = null;
		try {
			Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
			ClassInfo classInfo = new ClassInfo(classCreateVO);
			id = classService.insertClassById(userId, BigInteger.valueOf(courseId), classInfo).intValue();
			result.put("id", id);
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{courseId}/seminar", method = GET)
	@ResponseBody
	public ResponseEntity getSeminarsByCourseId(@PathVariable int courseId) throws IllegalArgumentException{
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<SeminarGroup> listSeminarGroup = new ArrayList<SeminarGroup>();
		List<SeminarAndGradeVO> listSeminarAndGradeVO = new ArrayList<SeminarAndGradeVO>();
		listSeminarGroup = gradeService.listSeminarGradeByCourseId(userId, BigInteger.valueOf(courseId));
		for (SeminarGroup seminarGroup : listSeminarGroup) {
			SeminarAndGradeVO seminarAndGradeVO = new SeminarAndGradeVO(seminarGroup);
			listSeminarAndGradeVO.add(seminarAndGradeVO);
		}
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listSeminarAndGradeVO);
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}/seminar/create", method = POST)
	@ResponseBody
	public ResponseEntity createSeminarByCourseId(@PathVariable int courseId, @RequestBody SeminarUpdateVO seminarUpdateVO) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = simpleDateFormat.parse(seminarUpdateVO.getStartTime());
			endTime = simpleDateFormat.parse(seminarUpdateVO.getEndTime());
			Seminar seminar = new Seminar(null, seminarUpdateVO.getName(), seminarUpdateVO.getDescription(),
				null, seminarUpdateVO.getGroupingMethod().equals("fixed"), startTime, endTime);
			seminarService.insertSeminarByCourseId(BigInteger.valueOf(courseId), seminar);
			return ResponseEntity.status(200).build();
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}catch (ParseException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}


	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{courseId}/grade", method = GET)
	@ResponseBody
	public ResponseEntity getAllGradeByCourseId(@PathVariable int courseId){
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<SeminarGroup> seminarGroups = gradeService.listSeminarGradeByCourseId(userId, BigInteger.valueOf(courseId));
		List<SeminarGradeVO> seminarGradeVOS = new ArrayList<SeminarGradeVO>();
		for (SeminarGroup seminarGroup : seminarGroups) {
			SeminarGradeVO seminarGradeVO = new SeminarGradeVO(seminarGroup);
			seminarGradeVOS.add(seminarGradeVO);
		}
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarGradeVOS);
	}
}
