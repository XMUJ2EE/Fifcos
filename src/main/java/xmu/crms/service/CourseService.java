package xmu.crms.service;
import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;
import xmu.crms.exception.*;
import xmu.crms.entity.*;


/**
 *
 * @author zengnan
 * @version 2.00
 *
 */

public interface CourseService {
	/**
	 * 按userId获取与当前用户相关联的课程列表.
	 * <p>老师与他相关联的课程列表<br>
	 * @author ZhouZhongjun
	 * @param userId 用户Id
	 * @return null 课程列表
	 * @throws IllegalArgumentException userId格式错误时抛出
	 * @throws CourseNotFoundException 未找到课程
	 */
	List<Course> listCourseByUserId(BigInteger userId)throws IllegalArgumentException,CourseNotFoundException ;


	/**
	 * 按userId创建课程.
	 *
	 * <p>按userId创建课程<br>
	 * @author ZhouZhongjun
	 * @param userId 用户Id
	 * @param course 课程信息
	 * @return courseId 新建课程的id
	 * @throws IllegalArgumentException userId格式错误时抛出
	 */
	BigInteger insertCourseByUserId(BigInteger userId, Course course)throws IllegalArgumentException;


	/**
	 * 按courseId获取课程 .
	 * <p>按courseId获取课程 <br>
	 * @author ZhouZhongjun
	 * @param courseId 课程Id
	 * @return course
	 * @throws IllegalArgumentException courseId格式错误时抛出
	 * @throws CourseNotFoundException 未找到课程
	 */
	Course getCourseByCourseId(BigInteger courseId)throws IllegalArgumentException,CourseNotFoundException;


	/**
	 * 传入courseId和course信息修改course信息.
	 * <p>传入courseId和course信息修改course信息 <br>
	 * @author ZhouZhongjun
	 * @param courseId 课程Id
	 * @param course 课程信息
	 * @throws CourseNotFoundException 未找到课程
	 */
	void updateCourseByCourseId(BigInteger courseId, Course course);


	/**
	 * 按courseId删除课程.
	 * <p>先根据courseID删除Seminar 和 class,然后再将course的信息删除<br>
	 * @author ZhouZhongjun
	 * @param courseId 课程Id
	 * @see SeminarService #deleteSemiarByCourseId(BigInteger courseId)
	 * @see ClassService   #deleteClassByCourseId(BigInteger courseId)
	 * @throws IllegalArgumentException courseId格式错误时抛出
	 * @throws CourseNotFoundException 未找到课程
	 */
	void deleteCourseByCourseId(BigInteger courseId)throws IllegalArgumentException;



	/**
	 * 根据课程名称获取课程列表.
	 * <p>根据课程名称获取课程列表<br>
	 * @author YeXiaona
	 * @param courseName 课程名称
	 * @return list 课程列表
	 * @see CourseService #getCourseByCourseId(BigInteger courseId)
	 */
	List<Course> listCourseByCourseName(String courseName);


	/**
	 * 按课程名称获取班级列表.
	 * <p>根据课程名获取课程ID，通过课程ID获取班级列表<br>
	 * @author YeXiaona
	 * @param courseName 课程名称
	 * @return list 班级列表
	 * @see CourseService #listCourseByCourseName(String courseName)
	 * @see ClassService #listClassByCourseId(BigInteger courseId)
	 * @throws CourseNotFoundException 没有找到课程
	 */
	List<ClassInfo> listClassByCourseName(String courseName) throws CourseNotFoundException;


	/**
	 * 按教师名称获取班级列表.
	 * <p>根据教师名称获取课程ID，通过课程ID获取班级列表<br>
	 * @author YeXiaona
	 * @param teacherName 教师名称
	 * @return list 班级列表
	 * @see UserService #listUserIdByUserName(String userName)
	 * @see CourseService #listClassByUserId(BigInteger userId)
	 * @throws UserNotFoundException 没有找到用户
	 * @throws ClazzNotFoundException 没有找到班级
	 */
	List<ClassInfo> listClassByTeacherName(String teacherName) throws UserNotFoundException,ClazzNotFoundException;


	/**
	 * 根据教师ID获取班级列表.
	 * <p>根据教师ID获取班级列表<br>
	 * @author YeXiaona
	 * @param userId 教师ID
	 * @return list 班级列表
	 * @see CourseService #listCourseByUserId(BigInteger userId)
	 * @see ClassService #listClassByCourseId(BigInteger courseId)
	 * @throws IllegalArgumentException userId格式错误时抛出或courseId格式错误时抛出
	 * @throws CourseNotFoundException 未找到课程
	 * @throws ClassNotFoundException 未找到班级
	 */
//	 List<ClassInfo> listClassByUserId(BigInteger userId)throws IllegalArgumentException,CourseNotFoundException,ClassNotFoundException;
}

