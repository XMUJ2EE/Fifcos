package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

@RequestMapping("/course")

public class CourseController {
	
	@Autowired
	
	@RequestMapping(method = GET)
	public Model getUserCourses(Model model) {
		
		return model;
	}
	
	@RequestMapping(method = GET)
	public String createCourse() {
		
		return null;
	}
	
	@RequestMapping(value = "/{courseId}", method = GET)
	
	public String getCourseById(@PathVariable int id, Model model) {
		
		
		return null;
		
	}
	 
	@RequestMapping(value = "/{courseId}", method = PUT )
	
	public String updateCourseById(@PathVariable int id, Model model) {
		
		return null;
	}
	
	@RequestMapping(value = "/{courseId}", method = DELETE )
	
	public String deleteCourseById(@PathVariable int id, Model model) {
		
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
