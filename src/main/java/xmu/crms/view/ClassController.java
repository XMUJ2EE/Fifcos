package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/class")

public class ClassController {
	@Autowired
	
	@RequestMapping(method = GET)
	public String getUserClass() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}", method = GET)
	public String getClassById() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}", method = PUT)
	public String updateClassById() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}", method = DELETE)
	public String deleteClassById() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/student", method = GET)
	public String getClassStudent() {
		
		return null;
	}
	
	@RequestMapping(value="/{classId}/student", method = POST)
	public String selectClass() {
		
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
