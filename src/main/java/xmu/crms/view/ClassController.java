package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.User;
import xmu.crms.exception.ClazzNotFoundException;
import xmu.crms.service.UserService;
import xmu.crms.service.impl.ClassServiceImpl;
import xmu.crms.view.vo.ClassDetailVO;
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
	ClassServiceImpl classService;

	@Autowired
	UserService userService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getClassesByUserId(@RequestParam(value = "courseName", required = false) String courseName,
														  @RequestParam(value = "courseTeacher", required = false) String teacherName) {
//		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		List<ClassInfo> classInfos = classService.list
		String test = "[\n" +
				"  {\n" +
				"    \"id\": 1,\n" +
				"    \"name\": \"周三1-2节\",\n" +
				"    \"numStudent\": 60,\n" +
				"    \"time\": \"周三1-2、周五1-2\",\n" +
				"    \"site\": \"公寓405\",\n" +
				"    \"courseName\": \"OOAD\",\n" +
				"    \"courseTeacher\": \"邱明\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 2,\n" +
				"    \"name\": \"一班\",\n" +
				"    \"numStudent\": 60,\n" +
				"    \"time\": \"周三34节 周五12节\",\n" +
				"    \"site\": \"海韵202\",\n" +
				"    \"courseName\": \".Net 平台开发\",\n" +
				"    \"courseTeacher\": \"杨律青\"\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(test);
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}", method = GET)
	@ResponseBody
	public ResponseEntity getClassById(@PathVariable BigInteger classId) {

		try{
			ClassInfo classInfo = classService.getClassByClassId(classId);

			List<User> users = userService.listUserByClassId(classId, null, null);
			ClassDetailVO classDetailVO = new ClassDetailVO(classInfo, users.size());
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(classDetailVO);
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON_UTF8).build();
		}
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
	public ResponseEntity getStudentListByClassId(@PathVariable BigInteger classId,
										  @RequestParam(value = "numBeginWith",required = false) String numBeginWith,
										  @RequestParam(value = "nameBeginWith",required = false) String nameBeginWith) {
		List<User> list = new ArrayList<User>();
		try {
			list = userService.listUserByClassId(classId, numBeginWith, nameBeginWith);
		} catch (ClazzNotFoundException e) {
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
		String a = "{\n" +
				"  \"leader\": {\n" +
				"    \"id\": 2757,\n" +
				"    \"name\": \"张三\",\n" +
				"    \"number\": \"23320152202333\"\n" +
				"  },\n" +
				"  \"members\": [\n" +
				"    {\n" +
				"      \"id\": 2756,\n" +
				"      \"name\": \"李四\",\n" +
				"      \"number\": \"23320152202443\"\n" +
				"    },\n" +
				"    {\n" +
				"      \"id\": 2777,\n" +
				"      \"name\": \"王五\",\n" +
				"      \"number\": \"23320152202433\"\n" +
				"    }\n" +
				"  ]\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(a);
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
