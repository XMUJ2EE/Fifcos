package xmu.crms.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import xmu.crms.dao.ClassDao;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.ClassMapper;
import xmu.crms.service.ClassService;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.SeminarGroupService;
import org.springframework.web.bind.annotation.*;

import xmu.crms.exception.*;
import xmu.crms.service.ClassService;
import java.math.BigInteger;

@Service("ClassService")
public class ClassServiceImpl implements ClassService{
	@Autowired
	private ClassDao classDao;

	@Override
	public int deleteClassSelectionByClassId(BigInteger classId) throws ClazzNotFoundException{
		return classDao.deleteClassSelectionByClassId(classId);
	}

	@Override
	public List<ClassInfo> listClassByName(String courseName, String teacherName)
			throws UserNotFoundException, CourseNotFoundException {
		return classDao.listClassByName(courseName, teacherName);
	}

	@Override
	public List<ClassInfo> listClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
		return classDao.listClassByCourseId(courseId);
	}

	@Override
	public ClassInfo getClassByClassId(BigInteger classId) throws ClazzNotFoundException {
		return classDao.getClassByClassId(classId);
	}

	@Override
	public int updateClassByClassId(BigInteger classId, ClassInfo newClass) throws ClazzNotFoundException {
		return classDao.updateClassByClassId(classId, newClass);
	}

	@Override
	public int deleteClassByClassId(BigInteger classId) throws ClazzNotFoundException {
		return classDao.deleteClassByClassId(classId);
	}

	@Override
	public BigInteger insertCourseSelectionById(BigInteger userId, BigInteger classId)
			throws UserNotFoundException, ClazzNotFoundException {
		return classDao.insertCourseSelectionById(userId, classId);
	}

	@Override
	public int deleteCourseSelectionById(BigInteger userId, BigInteger classId)
			throws UserNotFoundException, ClazzNotFoundException {
		return classDao.deleteCourseSelectionById(userId, classId);
	}

	@Override
	public Location getCallStatusById(BigInteger classId, BigInteger seminarId) throws SeminarNotFoundException {
		return classDao.getCallStatusById(classId, seminarId);
	}

	@Override
	public BigInteger insertClassById(BigInteger courseId, ClassInfo classInfo) throws CourseNotFoundException {
		return classDao.insertClassById(courseId, classInfo);
	}

	@Override
	public int deleteClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
		return classDao.deleteClassByCourseId(courseId);
	}

	@Override
	public int deleteScoreRuleById(BigInteger classId) throws ClazzNotFoundException {
		return classDao.deleteScoreRuleById(classId);
	}

	@Override
	public ClassInfo getScoreRule(BigInteger classId) throws ClazzNotFoundException {
		return classDao.getScoreRule(classId);
	}

	@Override
	public int insertScoreRule(BigInteger classId, ClassInfo proportions)
			throws InvalidOperationException, ClazzNotFoundException {
		return classDao.insertScoreRule(classId, proportions);
	}

	@Override
	public int updateScoreRule(BigInteger classId, ClassInfo proportions)
			throws InvalidOperationException, ClazzNotFoundException {
		return classDao.updateScoreRule(classId, proportions);
	}

	@Override
	public BigInteger callInRollById(Location location) throws SeminarNotFoundException, ClazzNotFoundException {
		return classDao.callInRollById(location);
	}

	@Override
	public int endCallRollById(BigInteger seminarId, BigInteger classId) throws SeminarNotFoundException, ClazzNotFoundException {
		return classDao.endCallRollById(seminarId, classId);
	}

	@Override
	public List<ClassInfo> listClassByUserId(BigInteger userId)
			throws IllegalArgumentException, ClazzNotFoundException {
		return classDao.listClassByUserId(userId);
	}
	
}
