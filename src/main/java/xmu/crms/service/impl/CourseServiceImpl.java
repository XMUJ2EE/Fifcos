package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.CourseDao;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.User;
import xmu.crms.exception.ClazzNotFoundException;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.CourseService;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZengNan
 */
@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
    CourseDao courseDao;
	@Autowired
    ClassServiceImpl classServiceImpl;
	@Autowired
    SeminarServiceImpl seminarServiceImpl;
	@Autowired
    UserServiceImpl userServiceImpl;
	@Autowired
    CourseServiceImpl courseServiceImpl;
	@Override
	public List<Course> listCourseByUserId(BigInteger userId)
			throws IllegalArgumentException, CourseNotFoundException {
		if(!(userId.intValue() > 0)) {
			throw new IllegalArgumentException("用户ID格式错误");
		}
		List<Course> courseList = courseDao.listCourseByUserId(userId);
		if(courseList == null) {
			throw new CourseNotFoundException("没有找到课程！");
		}
		return courseList;
	}
	@Override
	public BigInteger insertCourseByUserId(BigInteger userId, Course course)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(!(userId.intValue() > 0)) {
			throw new IllegalArgumentException("用户ID格式错误");
		}
		return courseDao.insertCourseByUserId(userId, course);
	}
	@Override
	public Course getCourseByCourseId(BigInteger courseId)
			throws IllegalArgumentException, CourseNotFoundException {
		// TODO Auto-generated method stub
		if(!(courseId.intValue() > 0)) {
			throw new IllegalArgumentException("课程ID格式错误");
		}
		Course course = courseDao.getCourseByCourseId(courseId);
		if(course == null) {
			throw new CourseNotFoundException("没有找到课程！");
		}
		return course;
	}
	@Override
	public void updateCourseByCourseId(BigInteger courseId, Course course) {
		// TODO Auto-generated method stub
		courseDao.updateCourseByCourseId(courseId, course);
	}
	@Override
	public void deleteCourseByCourseId(BigInteger courseId)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(!(courseId.intValue() > 0)) {
			throw new IllegalArgumentException("课程ID格式错误");
		}
		courseDao.deleteCourseByCourseId(courseId);
		try{
			seminarServiceImpl.deleteSeminarByCourseId(courseId);
			classServiceImpl.deleteClassByCourseId(courseId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Course> listCourseByCourseName(String courseName) {
		// TODO Auto-generated method stub
		return courseDao.listCourseByCourseName(courseName);
	}
	@Override
	public List<ClassInfo> listClassByCourseName(String courseName)
			throws CourseNotFoundException {
		// TODO Auto-generated method stub
		List<Course> courseList = this.listCourseByCourseName(courseName);
		if(courseList == null)
		{
			throw new CourseNotFoundException("没有找到课程！");
		}
		List<ClassInfo> classInfoList = new LinkedList<ClassInfo>();
		for(int i = 0; i < courseList.size(); i++)
		{
			BigInteger id = courseList.get(i).getId();
			classInfoList.addAll(classServiceImpl.listClassByCourseId(id));
		}
		return classInfoList;
	}
	@Override
	public List<ClassInfo> listClassByTeacherName(String teacherName)
			throws UserNotFoundException,ClazzNotFoundException {
		// TODO Auto-generated method stub
		try
		{
		List<User> userList = userServiceImpl.listUserByUserName(teacherName);
		List<Course> courseList = new LinkedList<Course>();
		for(int i = 0; i < userList.size(); i++)
		{
			BigInteger userid = userList.get(i).getId();
			courseList.addAll(courseServiceImpl.listCourseByUserId(userid));
		}
		List<ClassInfo> classInfoList = new LinkedList<ClassInfo>();
		for(int i = 0; i < userList.size(); i++)
		{
			BigInteger courseid = courseList.get(i).getId();
			classInfoList.addAll(classServiceImpl.listClassByCourseId(courseid));
		}
		return classInfoList;
		}
		catch(Exception e)
		{
			return null;
		}
	}
//	@Override
//	public List<ClassInfo> listClassByUserId(BigInteger userId)
//			throws IllegalArgumentException, CourseNotFoundException,
//			ClassNotFoundException {
//		// TODO Auto-generated method stub
//		if(!(userId.intValue() > 0)) {
//			throw new IllegalArgumentException("用户ID格式错误");
//		}
//		List<Course> courseList = courseServiceImpl.listCourseByUserId(userId);
//		if(courseList == null) {
//			throw new CourseNotFoundException("没有找到课程！");
//		}
//		List<ClassInfo> classInfoList = new LinkedList<ClassInfo>();
//		for(int i = 0; i < courseList.size(); i++)
//		{
//			BigInteger courseId = courseList.get(i).getId();
//			classInfoList.addAll(classServiceImpl.listClassByCourseId(courseId));
//		}
//		if(classInfoList == null) {
//			throw new ClassNotFoundException("没有找到班级！");
//		}
//		return classInfoList;
//	}

}
