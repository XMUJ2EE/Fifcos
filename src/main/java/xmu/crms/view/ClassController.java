package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.UserService;
import xmu.crms.service.impl.ClassServiceImpl;
import xmu.crms.view.vo.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Controller
 * @author mads
 */
@Controller
@RequestMapping("/class")
public class ClassController {

	@Autowired
	ClassServiceImpl classService;

	@Autowired
	UserService userService;

	@Autowired
	FixGroupService fixGroupService;

	@Autowired
	SeminarGroupService seminarGroupService;

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getClassesByUserId(@RequestParam(value = "courseName", required = false) String courseName,
											 @RequestParam(value = "courseTeacher", required = false) String teacherName) {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			List<ClassInfo> classInfos = classService.listClassByUserId(userId);
			List<UserClassVO> userClassVOS = new ArrayList<>();
			for(ClassInfo classInfo:classInfos){
				List<User> users = userService.listUserByClassId(classInfo.getId(), null, null);
				userClassVOS.add(new UserClassVO(classInfo, users.size()));
			}
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(userClassVOS);
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}
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
	public ResponseEntity updateClassById(@PathVariable BigInteger classId,
										  @PathVariable ClassCreateVO classCreateVO) {

		ClassInfo classInfo = new ClassInfo(classCreateVO);
		try{
			classService.updateClassByClassId(classId, classInfo);
			return ResponseEntity.status(204).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value="/{classId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deleteClassById(@PathVariable BigInteger classId) {
		try{
			classService.getClassByClassId(classId);
			return ResponseEntity.status(204).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}
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
	public ResponseEntity selectClass(@PathVariable BigInteger classId) {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			classService.insertCourseSelectionById(userId, classId);
			return ResponseEntity.status(204).build();
		}catch (UserNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value = "/{classId}/student/{studentId}", method = DELETE)
	@ResponseBody
	public ResponseEntity deSelectClass(@PathVariable BigInteger classId, @PathVariable BigInteger studentId) {
		try{
			classService.deleteCourseSelectionById(studentId, classId);
			return ResponseEntity.status(204).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (UserNotFoundException e){
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup", method = GET)
	@ResponseBody
	public ResponseEntity getGroupByClassId(@PathVariable BigInteger classId) {

		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
			List<User> members = fixGroupService.listFixGroupMemberByGroupId(fixGroup.getId());
			FixGroupVO fixGroupVO = new FixGroupVO(fixGroup, members);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(fixGroupVO);
		}catch (UserNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (FixGroupNotFoundException e){
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup/resign", method = PUT)
	public ResponseEntity resignFromGroup(@PathVariable BigInteger classId) {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
			seminarGroupService.resignLeaderById(fixGroup.getId(), userId);
			return ResponseEntity.status(204).build();
		}catch (UserNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (GroupNotFoundException e){
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup/assign", method = PUT)
	public ResponseEntity assignFromGroup(@PathVariable BigInteger classId) {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
			seminarGroupService.assignLeaderById(fixGroup.getId(), userId);
			return ResponseEntity.status(204).build();
		}catch (UserNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (GroupNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (InvalidOperationException e){
			return ResponseEntity.status(403).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup/add", method = PUT)
	public ResponseEntity addMemberInGroup(@PathVariable BigInteger classId) {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			/*每个人一开始都是fixGroup的leader， 若插入的时候组内还只有一个人，
			说明目前的组是个虚拟的组，要现在数据库里面插入一个fixGroup的记录
			 */
			FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
			if(fixGroup == null){
				fixGroupService.insertFixGroupByClassId(classId, userId);
			}
			FixGroup fixGroupNew = fixGroupService.getFixedGroupById(userId, classId);
			fixGroupService.insertStudentIntoGroup(userId, fixGroupNew.getId());
			return ResponseEntity.status(204).build();
		}catch (UserNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (InvalidOperationException e){
			return ResponseEntity.status(403).build();
		}catch (FixGroupNotFoundException e){
			return ResponseEntity.status(403).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/classgroup/remove", method = PUT)
	public ResponseEntity deleteMemberFromGroup(@PathVariable BigInteger classId,
												@RequestBody UserVO userVO) {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
			fixGroupService.deleteFixGroupUserById(fixGroup.getId(), userVO.getId());
			return ResponseEntity.status(204).build();
		}catch (UserNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (FixGroupNotFoundException e){
			return ResponseEntity.status(404).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}
	}
}
