package xmu.crms.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xmu.crms.dao.*;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.*;

/**
 * @author chengjin
 * @author xingb
 */

@Repository("ClassDao")
public class ClassDaoImpl implements ClassDao{
	
	@Autowired(required = false)
	private ClassMapper classMapper;

	@Override
	public int deleteClassSelectionByClassId(BigInteger classId) throws ClazzNotFoundException{
		int delete=0;
		ClassInfo classInfo=classMapper.getClassByClassId(classId);
		if(classInfo==null) {
			throw new ClazzNotFoundException("无此classId的班级");
		}else {
			classMapper.deleteLocationByClassId(classId);
			List<BigInteger> fixGroupIds=classMapper.listFixGroupIdByClassId(classId);
			if(!fixGroupIds.isEmpty()) {
				classMapper.deleteFixGroupMemberByFixGroupId(fixGroupIds);
				classMapper.deleteFixGroupTopicByFixGroupId(fixGroupIds);
			}
			classMapper.deleteFixGroupByClassId(classId);
			List<BigInteger> seminarGroupIds=classMapper.listSeminarGroupIdByClassId(classId);
			if(!seminarGroupIds.isEmpty()) {
				classMapper.deleteSeminarGroupMemberBySeminarGroupId(seminarGroupIds);
				List<BigInteger> seminarGroupTopicIds=classMapper.listSeminarGroupTopicIdBySeminarGroupId(seminarGroupIds);
				if(!seminarGroupTopicIds.isEmpty()) {
					classMapper.deleteStudentScoreGroupBySeminarGroupTopicId(seminarGroupTopicIds);
				}
				classMapper.deleteSeminarGroupTopicBySeminarGroupId(seminarGroupIds);
			}
			classMapper.deleteAttendanceByClassId(classId);
			classMapper.deleteSeminarGroupByClassId(classId);
			delete=classMapper.deleteClassSelectionByClassId(classId);
		}
		return delete;
	}

	@Override
	public List<ClassInfo> listClassByName(String courseName, String teacherName)
			throws UserNotFoundException, CourseNotFoundException {
		List<ClassInfo> classInfos=new ArrayList<ClassInfo>();
		List<BigInteger> courseIds=classMapper.getCourseIdByName(courseName);
		if(courseIds.isEmpty()) {
			throw new CourseNotFoundException("无此名称的课程");
		}else {
			List<BigInteger> userIds=classMapper.getUserIdByName(teacherName);
			if(userIds.isEmpty()) {
				throw new UserNotFoundException("无此姓名的教师");
			}else {
				classInfos=classMapper.listClassByName(courseName, teacherName);
			}
		}
		return classInfos;
	}

	@Override
	public List<ClassInfo> listClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
		List<ClassInfo> classInfos=new ArrayList<ClassInfo>();
		Course course=classMapper.selectCourseByCourseId(courseId);
		if(course==null) {
			throw new CourseNotFoundException("无此courseId的课程");
		}else {
			classInfos=classMapper.listClassByCourseId(courseId);
		}
		return classInfos;
	}

	@Override
	public ClassInfo getClassByClassId(BigInteger classId) throws ClazzNotFoundException {
		ClassInfo classInfo=classMapper.getClassByClassId(classId);
		if(classInfo==null) {
			throw new ClazzNotFoundException("无此classId的班级");
		}
		return classInfo;
	}

	@Override
	public int updateClassByClassId(BigInteger classId, ClassInfo newClass) throws ClazzNotFoundException {
		int update=0;
		ClassInfo classInfo=classMapper.getClassByClassId(classId);
		if(classInfo==null) {
			throw new ClazzNotFoundException("无此classId的班级");
		}else {
			update=classMapper.updateClassByClassId(classId, newClass);
		}
		return update;
	}

	@Override
	public int deleteClassByClassId(BigInteger classId) throws ClazzNotFoundException {
		int delete=0;
		ClassInfo classInfo=classMapper.getClassByClassId(classId);
		if(classInfo==null) {
			throw new ClazzNotFoundException("无此classId的班级");
		}else {
			classMapper.deleteLocationByClassId(classId);
			classMapper.deleteClassSelectionByClassId(classId);
			List<BigInteger> fixGroupIds=classMapper.listFixGroupIdByClassId(classId);
			if(!fixGroupIds.isEmpty()) {
				classMapper.deleteFixGroupMemberByFixGroupId(fixGroupIds);
				classMapper.deleteFixGroupTopicByFixGroupId(fixGroupIds);
			}
			classMapper.deleteFixGroupByClassId(classId);
			List<BigInteger> seminarGroupIds=classMapper.listSeminarGroupIdByClassId(classId);
			if(!seminarGroupIds.isEmpty()) {
				classMapper.deleteSeminarGroupMemberBySeminarGroupId(seminarGroupIds);
				List<BigInteger> seminarGroupTopicIds=classMapper.listSeminarGroupTopicIdBySeminarGroupId(seminarGroupIds);
				if(!seminarGroupTopicIds.isEmpty()) {
					classMapper.deleteStudentScoreGroupBySeminarGroupTopicId(seminarGroupTopicIds);
				}
				classMapper.deleteSeminarGroupTopicBySeminarGroupId(seminarGroupIds);
			}
			classMapper.deleteAttendanceByClassId(classId);
			classMapper.deleteSeminarGroupByClassId(classId);
			delete=classMapper.deleteClassByClassId(classId);
		}
		return delete;
	}

	@Override
	public BigInteger insertCourseSelectionById(BigInteger userId, BigInteger classId)
			throws UserNotFoundException, ClazzNotFoundException {
		BigInteger insertId=new BigInteger("0");
		User user=classMapper.selectUserByUserId(userId);
		if(user==null) {
			throw new UserNotFoundException("无此userId的用户");
		}else {
			ClassInfo classInfo=classMapper.getClassByClassId(classId);
			if(classInfo==null) {
				throw new ClazzNotFoundException("无此classId的班级");
			}else {
				ClassInfo classInfoIn=new ClassInfo();
				User studentIn=new User();
				classInfoIn.setId(classId);
				studentIn.setId(userId);
				CourseSelection courseSelection=new CourseSelection();
				courseSelection.setClassInfo(classInfoIn);
				courseSelection.setStudent(studentIn);
				int insert=classMapper.insertCourseSelection(courseSelection);
				insertId=courseSelection.getId();
			}
		}
		return insertId;
	}

	@Override
	public int deleteCourseSelectionById(BigInteger userId, BigInteger classId)
			throws UserNotFoundException, ClazzNotFoundException {
		int delete=0;
		User user=classMapper.selectUserByUserId(userId);
		if(user==null) {
			throw new UserNotFoundException("无此userId的用户");
		}{
			ClassInfo classInfo=classMapper.getClassByClassId(classId);
			if(classInfo==null) {
				throw new ClazzNotFoundException("无此classId的班级");
			}else {
				delete=classMapper.deleteCourseSelectionById(userId, classId);
			}
		}
		return delete;
	}

	@Override
	public Location getCallStatusById(BigInteger classId, BigInteger seminarId) throws SeminarNotFoundException {
		Location location=new Location();
		Seminar seminar=classMapper.selectSeminarBySeminarId(seminarId);
		if(seminar==null) {
			throw new SeminarNotFoundException("无此seminarId的讨论课");
		}else {
			location=classMapper.getCallStatusById(classId, seminarId);
		}
		return location;
	}

	@Override
	public BigInteger insertClassById(BigInteger courseId, ClassInfo classInfo) throws CourseNotFoundException {
		BigInteger insertId=new BigInteger("0");
		Course course=classMapper.selectCourseByCourseId(courseId);
		if(course==null) {
			throw new CourseNotFoundException("无此courseId的课程");
		}else {
			int insert=classMapper.insertClassById(courseId, classInfo);
			insertId=classInfo.getId();
		}
		return insertId;
	}

	@Override
	public int deleteClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
		int delete=0;
		Course course=classMapper.selectCourseByCourseId(courseId);
		if(course==null) {
			throw new CourseNotFoundException("无此courseId的课程");
		}else {
			List<ClassInfo> classInfos=classMapper.listClassByCourseId(courseId);
			Iterator<ClassInfo> it = classInfos.iterator();
			while(it.hasNext()) {
				BigInteger classId=it.next().getId();
				classMapper.deleteLocationByClassId(classId);
				classMapper.deleteClassSelectionByClassId(classId);
				List<BigInteger> fixGroupIds=classMapper.listFixGroupIdByClassId(classId);
				if(!fixGroupIds.isEmpty()) {
					classMapper.deleteFixGroupMemberByFixGroupId(fixGroupIds);
					classMapper.deleteFixGroupTopicByFixGroupId(fixGroupIds);
				}
				classMapper.deleteFixGroupByClassId(classId);
				List<BigInteger> seminarGroupIds=classMapper.listSeminarGroupIdByClassId(classId);
				if(!seminarGroupIds.isEmpty()) {
					classMapper.deleteSeminarGroupMemberBySeminarGroupId(seminarGroupIds);
					List<BigInteger> seminarGroupTopicIds=classMapper.listSeminarGroupTopicIdBySeminarGroupId(seminarGroupIds);
					if(!seminarGroupTopicIds.isEmpty()) {
						classMapper.deleteStudentScoreGroupBySeminarGroupTopicId(seminarGroupTopicIds);
					}
					classMapper.deleteSeminarGroupTopicBySeminarGroupId(seminarGroupIds);
				}
				classMapper.deleteAttendanceByClassId(classId);
				classMapper.deleteSeminarGroupByClassId(classId);
				delete+=classMapper.deleteClassByClassId(classId);
			}
		}
		return delete;
	}

	@Override
	public int deleteScoreRuleById(BigInteger classId) throws ClazzNotFoundException {
		int delete=0;
		ClassInfo classInfo=classMapper.getClassByClassId(classId);
		if(classInfo==null) {
			throw new ClazzNotFoundException("无此classId的班级");
		}else {
			delete=classMapper.deleteScoreRuleById(classId);
		}
		return delete;
	}

	@Override
	public ClassInfo getScoreRule(BigInteger classId) throws ClazzNotFoundException {
		ClassInfo classInfo=classMapper.getClassByClassId(classId);
		if(classInfo==null) {
			throw new ClazzNotFoundException("无此classId的班级");
		}
		return classInfo;
	}

	@Override
	public int insertScoreRule(BigInteger classId, ClassInfo proportions)
			throws InvalidOperationException, ClazzNotFoundException {
		int insert=0;
		if(!(proportions instanceof ClassInfo)) {
			throw new InvalidOperationException("操作失败");
		}
		ClassInfo classInfo=classMapper.getClassByClassId(classId);
		if(classInfo==null) {
			throw new ClazzNotFoundException("无此classId的班级");
		}else {
			insert=classMapper.insertScoreRule(classId, proportions);
		}
		return insert;
	}

	@Override
	public int updateScoreRule(BigInteger classId, ClassInfo proportions)
			throws InvalidOperationException, ClazzNotFoundException {
		int update=0;
		ClassInfo classInfo=classMapper.getClassByClassId(classId);
		if(classInfo==null) {
			throw new ClazzNotFoundException("无此classId的班级");
		}else {
			update=classMapper.updateScoreRule(classId, proportions);
		}
		return update;
	}

	@Override
	public BigInteger callInRollById(Location location) throws SeminarNotFoundException, ClazzNotFoundException {
		Seminar seminar=classMapper.selectSeminarBySeminarId(location.getSeminar().getId());
		if(seminar==null) {
			throw new SeminarNotFoundException("无此seminarId的讨论课");
		}else {
			ClassInfo classInfo=classMapper.getClassByClassId(location.getClassInfo().getId());
			if(classInfo==null) {
				throw new ClazzNotFoundException("无此classId的班级");
			}else {
				classMapper.CallInRollById(location);
			}
		}
		return location.getId();
	}

	@Override
	public int endCallRollById(BigInteger seminarId, BigInteger classId) throws SeminarNotFoundException, ClazzNotFoundException {
		int update=0;
		Seminar seminar=classMapper.selectSeminarBySeminarId(seminarId);
		if(seminar==null) {
			throw new SeminarNotFoundException("无此seminarId的讨论课");
		}else {
			ClassInfo classInfo=classMapper.getClassByClassId(classId);
			if(classInfo==null) {
				throw new ClazzNotFoundException("无此classId的班级");
			}else {
				update=classMapper.endCallRollById(seminarId,classId);
			}
		}
		return update;
	}

	@Override
	public List<ClassInfo> listClassByUserId(BigInteger userId)
			throws IllegalArgumentException, ClazzNotFoundException {
		List<ClassInfo> classInfos=new ArrayList<ClassInfo>();
		if(!(userId instanceof BigInteger)) {
			throw new IllegalArgumentException();
		}else {
			List<BigInteger> classIds=classMapper.listClassByUserId(userId);
			if(classIds.isEmpty()) {
				throw new ClazzNotFoundException("找不到班级");
			}else {
				Iterator<BigInteger> it = classIds.iterator();
				while(it.hasNext()) {
					classInfos.add(classMapper.getClassByClassId(it.next()));
				}
			}
		}
		return classInfos;
	}

}
