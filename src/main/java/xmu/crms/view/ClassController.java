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
import xmu.crms.entity.Course;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.UserService;
import xmu.crms.service.impl.ClassServiceImpl;
import xmu.crms.view.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
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


	@PreAuthorize("hasRole('STUDENT')")
	@RequestMapping(method = GET)
	@ResponseBody
	public ResponseEntity getAvailableClassesByUserId(@RequestParam(value = "courseName", required = false) String courseName,
											 @RequestParam(value = "courseTeacher", required = false) String teacherName) {
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try{
			List<ClassInfo> allClassInfos = classService.listClassByName(courseName, teacherName);
			System.out.println(allClassInfos);
			List<ClassInfo> myClassInfos = new ArrayList<>();
			try{
				myClassInfos = classService.listClassByUserId(userId);
			}catch (ClazzNotFoundException e){

			}finally {
				List<UserClassVO> userClassVOS = new ArrayList<>();
				if(myClassInfos != null){
					System.out.println(myClassInfos);
					List<BigInteger> myClassCourseId = new ArrayList<>();
					for(ClassInfo classInfo:myClassInfos){
						myClassCourseId.add(classInfo.getCourse().getId());
					}

					for(ClassInfo classInfo:allClassInfos) {
						if (!myClassCourseId.contains(classInfo.getCourse().getId())) {
							try{
								List<User> users = userService.listUserByClassId(classInfo.getId(), null, null);
								userClassVOS.add(new UserClassVO(classInfo, users.size()));
							}catch (ClazzNotFoundException e){

							}
						}
					}
				}else{
					for(ClassInfo classInfo:allClassInfos) {
						try{
							List<User> users = userService.listUserByClassId(classInfo.getId(), null, null);
							userClassVOS.add(new UserClassVO(classInfo, users.size()));
						}catch (ClazzNotFoundException e){
						}
					}
				}
				return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(userClassVOS);
			}

		}catch (UserNotFoundException e){
			e.printStackTrace();
			return ResponseEntity.status(404).build();
		}catch (CourseNotFoundException e) {
			e.printStackTrace();
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
			System.out.println(classDetailVO);
			return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(classDetailVO);
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON_UTF8).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER')")
	@RequestMapping(value="/{classId}", method = PUT)
	@ResponseBody
	public ResponseEntity updateClassById(@PathVariable BigInteger classId,
										  HttpServletRequest httpServletRequest) throws IOException {
		BufferedReader br = httpServletRequest.getReader();
		BigInteger userId = (BigInteger) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String str, wholeStr = "";
		while((str = br.readLine()) != null){
			wholeStr += str;
		}
		ClassCreateVO classCreateVO = new ClassCreateVO(wholeStr);
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
			classService.deleteClassByClassId(classId);
			return ResponseEntity.status(204).build();
		}catch (ClazzNotFoundException e){
			return ResponseEntity.status(404).build();
		}
	}

	@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
	@RequestMapping(value="/{classId}/student", method = GET)
	@ResponseBody
	public ResponseEntity getStudentListByClassId(@PathVariable BigInteger classId,
										  @RequestParam(value = "numberBeginWith",required = false) String numberBeginWith,
										  @RequestParam(value = "nameBeginWith",required = false) String nameBeginWith) {
		List<User> list = new ArrayList<User>();
		try {
			System.out.println(numberBeginWith+':'+nameBeginWith);
			list = userService.listUserByClassId(classId, numberBeginWith, nameBeginWith);
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
			System.out.println(members.toString());
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
			BigInteger groupId = BigInteger.valueOf(0);
			FixGroup fixGroup = fixGroupService.getFixedGroupById(userId, classId);
			if(fixGroup == null){
				ClassInfo classInfo = new ClassInfo();
				classInfo.setId(classId);
				User leader = new User();
				leader.setId(userId);
				FixGroup fixGroup1 = new FixGroup();
				fixGroup1.setClassInfo(classInfo);
				fixGroup1.setLeader(leader);
				fixGroupService.insertFixGroupByClassId(fixGroup1);
			}
//			FixGroup fixGroupNew = fixGroupService.getFixedGroupById(userId, classId);
			fixGroupService.insertStudentIntoGroup(userId, groupId);
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
