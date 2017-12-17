package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Class Controller
 * @author Xuezhang.Liu
 */
@Controller

@RequestMapping("/class")

public class ClassController {

//	@Autowired
//	private ClassService classService;

	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getClassesByUserId(@RequestParam(value = "courseName", required = false) String courseName,
														  @RequestParam(value = "courseTeacher", required = false) String teacherName) {

//
//		int userId = 2777;
//
//		List<Class> classes = classService.getClassListByUserId(userId, courseName, teacherName);
//		if(null == classes){
//			return ResponseEntity.status(404).build();
//		}else{
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(classes);
//		}
		String myClass = "[\n" +
				"  {\n" +
				"    \"id\": 23,\n" +
				"    \"name\": \"周三1-2节\",\n" +
				"    \"numStudent\": 60,\n" +
				"    \"time\": \"周三1-2、周五1-2\",\n" +
				"    \"site\": \"公寓405\",\n" +
				"    \"courseName\": \"OOAD\",\n" +
				"    \"courseTeacher\": \"邱明\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 42,\n" +
				"    \"name\": \"一班\",\n" +
				"    \"numStudent\": 60,\n" +
				"    \"time\": \"周三34节 周五12节\",\n" +
				"    \"site\": \"海韵202\",\n" +
				"    \"courseName\": \".Net 平台开发\",\n" +
				"    \"courseTeacher\": \"杨律青\"\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(myClass);
	}
	
	@RequestMapping(value="/{classId}", method = GET)
	@ResponseBody
	public ResponseEntity getClassById(@PathVariable int classId) {
//		// id 格式判断 400
//
//		Class myClass = classService.getClassById(classId);
//		if(null == myClass){
//			// 未找到班级
//			return ResponseEntity.status(404).build();
//		}else{
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(myClass);
//		}
		String myClass = "{\n" +
				"  \"id\": 23,\n" +
				"  \"name\": \"周三1-2节\",\n" +
				"  \"numStudent\": 120,\n" +
				"  \"time\": \"周三一二节\",\n" +
				"  \"site\": \"海韵201\",\n" +
				"  \"calling\": -1,\n" +
				"  \"roster\": \"/roster/周三12班.xlsx\",\n" +
				"  \"proportions\": {\n" +
				"    \"report\": 50,\n" +
				"    \"presentation\": 50,\n" +
				"    \"c\": 20,\n" +
				"    \"b\": 60,\n" +
				"    \"a\": 20\n" +
				"  }\n" +
				"}";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(myClass);
	}
	
	@RequestMapping(value="/{classId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateClassById(@PathVariable int classId) {

		// id格式判断 400
		// 权限认证 403

//		Boolean result = classService.updateClassById(classId, myClass);
//		if(result){
//			return ResponseEntity.status(204).build();
//		}else{
//			//未找到班级 404
//			return ResponseEntity.status(404).build();
//		}
		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value="/{classId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteClassById(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403

//		Boolean result = classService.deleteClassById(classId);
//		if(result){
//			return ResponseEntity.status(204).build();
//		}else{
//			return ResponseEntity.status(404).build();
//		}
		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value="/{classId}/student", method = GET)
	@ResponseBody
	public ResponseEntity getStudentListByClassId(@PathVariable int classId,
										  @RequestParam(value = "numBeginWith",required = false) String numBeginWith,
										  @RequestParam(value = "nameBeginWith",required = false) String nameBeginWith)
	{
		// id格式判断 400
		// 权限认证 403
//
//		List<User> students = classService.getStudentListByClassId(classId,numBeginWith, nameBeginWith);
//
//		if(null == students){
//			// 未找到班级404
//			return ResponseEntity.status(404).build();
//		}else{
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(students);
//		}
		String stuList = "[\n" +
				"  {\n" +
				"    \"id\": 233,\n" +
				"    \"name\": \"张三\",\n" +
				"    \"number\": \"24320152202333\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"id\": 245,\n" +
				"    \"name\": \"张八\",\n" +
				"    \"number\": \"24320152202334\"\n" +
				"  }\n" +
				"]";
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(stuList);
	}
	
	@RequestMapping(value="/{classId}/student", method = POST)
	@ResponseBody
	public ResponseEntity selectClass(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403

//		int userId = 123;
//		Boolean result = classService.addStudentToClassByClassIdAndStudentId(classId, userId);
//		if(result){
//			return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(new Object(){public String url = "/class/"+classId+"/student/2757";});
//
//		}else{
//			// 选过
//			return ResponseEntity.status(409).build();
//		}
		String url = "{\n" +
				"  \"url\": \"/class/34/student/2757\"\n" +
				"}";
		return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON_UTF8).body(url);

	}
	
	@RequestMapping(value="/{classId}/student/{studentId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deSelectClass(@PathVariable int classId, @PathVariable int studentId) {
		// id格式判断 400
		// 权限认证 403

//		Boolean result = classService.deleteStudentFromClassByClassIdAndStudentId(classId, studentId);
//
//		if(result){
//			return ResponseEntity.status(204).build();
//		}else{
//			// 不存在这个选课或不存在这个学生、班级
//			return ResponseEntity.status(404).build();
//		}
		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value="/{classId}/classgroup", method = GET)
	@ResponseBody
	public ResponseEntity getGroupByClassId(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403
//		int userId = 2777;
//
//		Group group = classService.getGroupByClassId(classId, userId);
//		if(null == group){
//			// 还没有小组 404
//			return ResponseEntity.status(404).build();
//		}else{
//			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(group);
//		}
		String group = "{\n" +
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
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(group);
	}
	
	@RequestMapping(value="/{classId}/classgroup/resign", method = PUT)
	public ResponseEntity resignFromGroup(@PathVariable int classId) {

		// id格式判断 400
		// 权限认证 403

//		Boolean result = classService.deleteLeaderFromGroupByClassId(classId,Integer.parseInt(request.get("groupId").toString()));
//		if(result){
//			return ResponseEntity.status(204).build();
//		}else {
//			// 失败
//			return ResponseEntity.status(404).build();
//		}
		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value="/{classId}/classgroup/assign", method = PUT)
	public ResponseEntity updateClassGroupByClassId(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403
//
//		Boolean result = classService.addLeaderInGroupByClassId(classId,
//				Integer.parseInt(request.get("id").toString()),
//				Integer.parseInt(request.get("groupId").toString()));
//		if(result){
//			return ResponseEntity.status(204).build();
//		}else {
//			// 已经有组长
//			return ResponseEntity.status(409).build();
//		}
		return ResponseEntity.status(204).build();
	}
	
	@RequestMapping(value="/{classId}/classgroup/add", method = PUT)
	public ResponseEntity addMemberInGroup(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403
//
//		Boolean result = classService.addMemberInGroupByClassId(classId,
//				Integer.parseInt(request.get("id").toString()),
//				Integer.parseInt(request.get("groupId").toString()));
//		if(result){
//			return ResponseEntity.status(204).build();
//		}else {
//			// 已经在小组
//			return ResponseEntity.status(409).build();
//		}
		return ResponseEntity.status(204).build();
	}

	@RequestMapping(value="/{classId}/classgroup/remove", method = PUT)
	public ResponseEntity deleteMemberFromGroup(@PathVariable int classId) {
		// id格式判断 400
		// 权限认证 403

//		Boolean result = classService.deleteMemberFromGroupByClassId(classId,
//				Integer.parseInt(request.get("id").toString()),
//				Integer.parseInt(request.get("groupId").toString()));
//		if(result){
//			return ResponseEntity.status(204).build();
//		}else {
//			// 待移除的学生不在组内
//			return ResponseEntity.status(409).build();
//		}
		return ResponseEntity.status(204).build();
	}
	
	
}
