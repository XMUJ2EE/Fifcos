package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/class")

public class ClassController {
	@Autowired
	List<Class> classes = new ArrayList<Class>();
	
	@RequestMapping(method = GET)
	public String getUserClass(String courseName, String teacherName, Model model) {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}", method = GET)
	public String getClassById(@PathVariable int id, Model model) {
		
		model.addAttribute("class", classes.get(id));
		
		return null;
	}
	
	@RequestMapping(value="/{classId}", method = PUT)
	public String updateClassById(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}", method = DELETE)
	public String deleteClassById(@PathVariable int id) {
		
		classes.remove(id);
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/student", method = GET)
	public String getClassStudent(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/student", method = POST)
	public String selectClass(@PathVariable int id) {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/student/{studentId}", method = DELETE)
	public String deselectClass() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/attendance", method = GET)
	public String getAttendanceByClassId() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/attendance/{studentId}", method = PUT)
	public String updateAttendanceByClassId() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/classgroup", method = GET)
	public String updateClassGroupByClassId() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/classgroup", method = PUT)
	public String getClassGroupByClassId() {
		
		return null;
	}
	
	
}
