package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.User;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.service.UserService;
import xmu.crms.view.vo.ClassStudentVO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Controller
 * @author Xuezhang.Liu
 */
@Controller

@RequestMapping("/class")

public class ClassController {

	@Autowired
	UserService userService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getClassesByUserId(@RequestParam(value = "courseName", required = false) String courseName,
														  @RequestParam(value = "courseTeacher", required = false) String teacherName) {

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}", method = GET)
	@ResponseBody
	public ResponseEntity getClassById(@PathVariable int classId) {

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value="/{classId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateClassById(@PathVariable int classId) {

		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value="/{classId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteClassById(@PathVariable int classId) {

		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/student", method = GET)
	@ResponseBody
	public ResponseEntity getStudentListByClassId(@PathVariable int classId,
										  @RequestParam(value = "numBeginWith",required = false) String numBeginWith,
										  @RequestParam(value = "nameBeginWith",required = false) String nameBeginWith) {
		List<User> list = new ArrayList<User>();
		try {
			list = userService.listUserByClassId(BigInteger.valueOf(classId), numBeginWith, nameBeginWith);
		} catch (ClassesNotFoundException e) {
			e.printStackTrace();
		}
		if (list.isEmpty()) {
			return ResponseEntity.status(404).body(null);
		}else {
			List<ClassStudentVO> listStudent = new ArrayList<ClassStudentVO>();
			for (int i=0; i<list.size(); i++) {
				ClassStudentVO classStudentVO = new ClassStudentVO(list.get(i).getId(), list.get(i).getName(), list.get(i).getNumber());
				listStudent.add(classStudentVO);
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(listStudent);
		}
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/student", method = POST)
	@ResponseBody
	public ResponseEntity selectClass(@PathVariable int classId) {

		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);

	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/student/{studentId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deSelectClass(@PathVariable int classId, @PathVariable int studentId) {

		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup", method = GET)
	@ResponseBody
	public ResponseEntity getGroupByClassId(@PathVariable int classId) {

		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(null);
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup/resign", method = PUT)
	public ResponseEntity resignFromGroup(@PathVariable int classId) {

		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup/assign", method = PUT)
	public ResponseEntity updateClassGroupByClassId(@PathVariable int classId) {

		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup/add", method = PUT)
	public ResponseEntity addMemberInGroup(@PathVariable int classId) {

		return ResponseEntity.status(204).build();
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup/remove", method = PUT)
	public ResponseEntity deleteMemberFromGroup(@PathVariable int classId) {

		return ResponseEntity.status(204).build();
	}
	
	
}
