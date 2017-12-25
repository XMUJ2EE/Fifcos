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
import xmu.crms.security.FifcosAuthenticationToken;
import xmu.crms.service.*;
import xmu.crms.view.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
	@Autowired
	CourseService courseService;
	@Autowired
	ClassService classService;
	@Autowired
	SeminarService seminarService;
	@Autowired
	UserService userService;

	@PreAuthorize("hasRole('TEACHER') ")
	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getUserCourses() {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UserCourseVO> userCourseVOList = new ArrayList<UserCourseVO>();
		try {
			List<Course> listCourse = courseService.listCourseByUserId(userId);
			System.out.println(listCourse.toString());
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
	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/student",method = GET)
	@ResponseBody
	public ResponseEntity getStudentCourses() {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UserCourseVO> userCourseVOList = new ArrayList<UserCourseVO>();
		try {
			List<ClassInfo> classInfoList = classService.listClassByUserId(userId);
			List<StudentClassVO> studentClassVOS = new ArrayList<>();
			for(ClassInfo classInfo:classInfoList){
				studentClassVOS.add(new StudentClassVO(classInfo));
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(studentClassVOS);
		} catch (ClazzNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}


	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(method = POST)
	@ResponseBody
	public ResponseEntity createCourse(HttpServletRequest httpServletRequest) throws IOException {
		try {
			BufferedReader br = httpServletRequest.getReader();
			BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String str, wholeStr = "";
			while((str = br.readLine()) != null){
				wholeStr += str;
			}
			CourseVO courseVO = new CourseVO(wholeStr);
			User teacher = userService.getUserByUserId(userId);
			Course course = new Course(courseVO, teacher);
			int id = courseService.insertCourseByUserId(userId, course).intValue();
			Map<String, Integer> result = new HashMap<String, Integer>();
			result.put("id", id);
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/student/{courseId}", method = GET)
	@ResponseBody
	public ResponseEntity getStudentCourseById(@PathVariable int courseId) {
		try {
			Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
			GetCourseVO getCourseVO = new GetCourseVO(course);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(getCourseVO);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value = "/{courseId}", method = GET)
	@ResponseBody
	public ResponseEntity getCourseById(@PathVariable int courseId) {
		try {
			Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
			GetCourseVO getCourseVO = new GetCourseVO(course);
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
	public ResponseEntity updateCourseById(@PathVariable int courseId, HttpServletRequest httpServletRequest) throws IOException {
        BufferedReader br = httpServletRequest.getReader();
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        CourseVO courseVO = new CourseVO(wholeStr);
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
		try {
			List<ClassVO> list = new ArrayList<ClassVO>();
			List<ClassInfo> listClass = new ArrayList<ClassInfo>();
			listClass = classService.listClassByCourseId(BigInteger.valueOf(courseId));
			for (ClassInfo listClas : listClass) {
				ClassVO classVO = new ClassVO(listClas.getId(), listClas.getName());
				list.add(classVO);
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(list);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}


	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}/class", method = POST)
	@ResponseBody
	public ResponseEntity createClassByCourseId(@PathVariable int courseId, HttpServletRequest httpServletRequest) throws UserNotFoundException, IOException {
		try {
            BufferedReader br = httpServletRequest.getReader();
            BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            ClassCreateVO classCreateVO = new ClassCreateVO(wholeStr);
			Map<String, Integer> result = new HashMap<String, Integer>();
			Course course = courseService.getCourseByCourseId(BigInteger.valueOf(courseId));
			ClassInfo classInfo = new ClassInfo(classCreateVO);
			int id = classService.insertClassById(userId, BigInteger.valueOf(courseId), classInfo).intValue();
			result.put("id", id);
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
		} catch (CourseNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}
	}

    @PreAuthorize("hasRole('TEACHER')")
    @RequestMapping(value = "/{courseId}/teacher/seminar", method = GET)
    @ResponseBody
    public ResponseEntity getSeminarsByCourseId(@PathVariable int courseId) throws IllegalArgumentException{
        BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            List<Seminar> seminarList = seminarService.listSeminarByCourseId(BigInteger.valueOf(courseId));
            System.out.println(seminarList);
//            List<SeminarGroup> listSeminarGroup = new ArrayList<SeminarGroup>();
//            List<SeminarAndGradeVO> listSeminarAndGradeVO = new ArrayList<SeminarAndGradeVO>();
//            listSeminarGroup = gradeService.listSeminarGradeByCourseId(userId, BigInteger.valueOf(courseId));
//            System.out.println(listSeminarGroup);
//            for (SeminarGroup seminarGroup : listSeminarGroup) {
//                SeminarAndGradeVO seminarAndGradeVO = new SeminarAndGradeVO(seminarGroup);
//                listSeminarAndGradeVO.add(seminarAndGradeVO);
//            }
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(seminarList);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{courseId}/student/seminar", method = GET)
	@ResponseBody
	public ResponseEntity getStudentSeminarsByCourseId(@PathVariable int courseId) throws IllegalArgumentException{
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<SeminarGroup> listSeminarGroup = new ArrayList<SeminarGroup>();
		List<SeminarAndGradeVO> listSeminarAndGradeVO = new ArrayList<SeminarAndGradeVO>();
		listSeminarGroup = gradeService.listSeminarGradeByCourseId(userId, BigInteger.valueOf(courseId));
		System.out.println(listSeminarGroup);
		for (SeminarGroup seminarGroup : listSeminarGroup) {
			SeminarAndGradeVO seminarAndGradeVO = new SeminarAndGradeVO(seminarGroup);
			listSeminarAndGradeVO.add(seminarAndGradeVO);
		}
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listSeminarAndGradeVO);
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value = "/{courseId}/seminar", method = POST)
	@ResponseBody
	public ResponseEntity createSeminarByCourseId(@PathVariable int courseId, HttpServletRequest httpServletRequest) throws IOException {
		try {
            BufferedReader br = httpServletRequest.getReader();
            BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            SeminarUpdateVO seminarUpdateVO = new SeminarUpdateVO(wholeStr);
			Map<String, BigInteger> result = new HashMap<String, BigInteger>();
			Seminar seminar = new Seminar(seminarUpdateVO);
			BigInteger id = seminarService.insertSeminarByCourseId(BigInteger.valueOf(courseId), seminar);
			result.put("id", id);
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(result);
		} catch (CourseNotFoundException e) {
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
