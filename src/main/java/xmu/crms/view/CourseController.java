package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xmu.crms.entity.Course;
import xmu.crms.service.CourseService;

/**
 * 
 * 课程信息Controller层
 * @author
 *
 */


@Controller

@RequestMapping("/course")

public class CourseController {
	

	@Autowired
	private List<Course> courses = new ArrayList<Course>();
	
	@RequestMapping(method = GET)
	public @ResponseBody Model getUserCourses(Model model) {
		
		/*
		List<Course> courses = new ArrayList<Course>();
		
		Course course = new Course();
		course.setId(1);
		course.setName("OOAD");
		course.setNumClass(3);
		course.setNumStudent(60);
		course.setStartTime("2017.9.1");
		course.setEndTime("2018.1.1");
		
		courses.add(course);

		course.setId(2);
		course.setName("J2EE");
		course.setNumClass(3);
		course.setNumStudent(60);
		course.setStartTime("2017.9.1");
		course.setEndTime("2018.1.1");
		
		courses.add(course);
		
		*/
		model.addAttribute("courseList", courses);
		
		return model;
	}
	
	@RequestMapping(method = POST)
	public String createCourse(int id, String name, int numClass, int numStudent, String startTime, String endTime, String description, Model model) {
		
		Course course = new Course(id, name, numClass, numStudent, startTime, endTime, description);
		courses.add(course);
		
		model.addAttribute(course);
		return null;
	}
	
	@RequestMapping(value = "/{courseId}", method = GET)
	
	public String getCourseById(@PathVariable int id, Model model) {
		
		model.addAttribute("course", courses.get(id));
		
		return null;
		
	}
	
	@RequestMapping(value = "/{courseId}", method = PUT )
	
	public String updateCourseById(@PathVariable int id, Model model) {
		
		return null;
	}
	
	@RequestMapping(value = "/{courseId}", method = DELETE )
	
	public String deleteCourseById(@PathVariable int id) {
		
		courses.remove(id);
		
		return null;
	}
	
	@RequestMapping(value = "/{courseId}/class", method = GET)
	
	public String getClassesByCourseId(@PathVariable int id, Model model) {
		
		
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
