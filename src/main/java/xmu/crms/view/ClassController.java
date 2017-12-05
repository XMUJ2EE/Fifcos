package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.Class;
import xmu.crms.entity.Group;
import xmu.crms.entity.User;
import xmu.crms.service.ClassService;
import xmu.crms.service.ClassServiceImpl;

/**
 * Class Controller
 * @author Xuezhang.Liu
 */
@Controller

@RequestMapping("/class")

public class ClassController {

	@Autowired
	private ClassService classService = new ClassServiceImpl();

	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity<List<Class>> getClassesByUserId(@RequestParam(value = "courseName", required = false) String courseName,
														  @RequestParam(value = "courseTeacher", required = false) String teacherName) {


		int userId = 2777;

		List<Class> classes = classService.getClassListByUserId(userId, courseName, teacherName);
		if(null == classes){
			return ResponseEntity.status(404).build();
		}else{
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(classes);
		}
	}
	
	@RequestMapping(value="/{classId}", method = GET)
	@ResponseBody
	public ResponseEntity<Class> getClassById(@PathVariable int classId) {
		// id 格式判断 400

		Class myClass = classService.getClassById(classId);
		if(null == myClass){
			// 未找到班级
			return ResponseEntity.status(404).build();
		}else{
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(myClass);
		}
	}
	
	@RequestMapping(value="/{classId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateClassById(@PathVariable int classId, @RequestParam Class myClass) {

		// id格式判断 400
		// 权限认证 403

		Boolean result = classService.updateClassById(classId, myClass);
		if(result){
			return ResponseEntity.status(204).build();
		}else{
			//未找到班级 404
			return ResponseEntity.status(404).build();
		}
	}
	
	@RequestMapping(value="/{classId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteClassById(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403

		Boolean result = classService.deleteClassById(classId);
		if(result){
			return ResponseEntity.status(204).build();
		}else{
			return ResponseEntity.status(404).build();
		}
	}
	
	@RequestMapping(value="/{classId}/student", method = GET)
	@ResponseBody
	public ResponseEntity<List<User>> getStudentListByClassId(@PathVariable int classId,
										  @RequestParam(value = "numBeginWith",required = false) String numBeginWith,
										  @RequestParam(value = "nameBeginWith",required = false) String nameBeginWith)
	{
		// id格式判断 400
		// 权限认证 403

		List<User> students = classService.getStudentListByClassId(classId,numBeginWith, nameBeginWith);

		if(null == students){
			// 未找到班级404
			return ResponseEntity.status(404).build();
		}else{
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(students);
		}
	}
	
	@RequestMapping(value="/{classId}/student", method = POST)
	@ResponseBody
	public ResponseEntity selectClass(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403

		int userId = 123;
		Boolean result = classService.addStudentToClassByClassIdAndStudentId(classId, userId);
		if(result){
			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object(){public String url = "/class/"+classId+"/student/2757";});

		}else{
			// 选过
			return ResponseEntity.status(409).build();
		}

	}
	
	@RequestMapping(value="/{classId}/student/{studentId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deSelectClass(@PathVariable int classId, @PathVariable int studentId) {
		// id格式判断 400
		// 权限认证 403

		Boolean result = classService.deleteStudentFromClassByClassIdAndStudentId(classId, studentId);

		if(result){
			return ResponseEntity.status(204).build();
		}else{
			// 不存在这个选课或不存在这个学生、班级
			return ResponseEntity.status(404).build();
		}
	}
	
	@RequestMapping(value="/{classId}/classgroup", method = GET)
	@ResponseBody
	public ResponseEntity<Group> getGroupByClassId(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403
		int userId = 2777;

		Group group = classService.getGroupByClassId(classId, userId);
		if(null == group){
			// 还没有小组 404
			return ResponseEntity.status(404).build();
		}else{
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(group);
		}
	}
	
	@RequestMapping(value="/{classId}/classgroup/resign", method = PUT)
	public ResponseEntity resignFromGroup(@PathVariable int classId, @RequestBody Map<String, Object> request) {

		// id格式判断 400
		// 权限认证 403

		Boolean result = classService.deleteLeaderFromGroupByClassId(classId,Integer.parseInt(request.get("groupId").toString()));
		if(result){
			return ResponseEntity.status(204).build();
		}else {
			// 失败
			return ResponseEntity.status(404).build();
		}
	}
	
	@RequestMapping(value="/{classId}/classgroup/assign", method = GET)
	public ResponseEntity updateClassGroupByClassId(@PathVariable int classId, @RequestBody Map<String, Object> request) {
		// id格式判断 400
		// 权限认证 403

		Boolean result = classService.addLeaderInGroupByClassId(classId,
				Integer.parseInt(request.get("id").toString()),
				Integer.parseInt(request.get("groupId").toString()));
		if(result){
			return ResponseEntity.status(204).build();
		}else {
			// 已经有组长
			return ResponseEntity.status(409).build();
		}
	}
	
	@RequestMapping(value="/{classId}/classgroup/add", method = PUT)
	public ResponseEntity addMemberInGroup(@PathVariable int classId, @RequestBody Map<String, Object> request) {
		// id格式判断 400
		// 权限认证 403

		Boolean result = classService.addMemberInGroupByClassId(classId,
				Integer.parseInt(request.get("id").toString()),
				Integer.parseInt(request.get("groupId").toString()));
		if(result){
			return ResponseEntity.status(204).build();
		}else {
			// 已经在小组
			return ResponseEntity.status(409).build();
		}
	}

	@RequestMapping(value="/{classId}/classgroup/add", method = PUT)
	public ResponseEntity deleteMemberFromGroup(@PathVariable int classId, @RequestBody Map<String, Object> request) {
		// id格式判断 400
		// 权限认证 403

		Boolean result = classService.deleteMemberFromGroupByClassId(classId,
				Integer.parseInt(request.get("id").toString()),
				Integer.parseInt(request.get("groupId").toString()));
		if(result){
			return ResponseEntity.status(204).build();
		}else {
			// 待移除的学生不在组内
			return ResponseEntity.status(409).build();
		}
	}
	
	
}
