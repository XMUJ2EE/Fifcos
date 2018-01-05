package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import xmu.crms.dao.CourseDao;
import xmu.crms.entity.Course;
import xmu.crms.exception.CourseNotFoundException;
import xmu.crms.mapper.CourseMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: ZengNan
 * @Description:
 * @Data: 2017/12/24 1:33
 */
@Repository("CourseDao")
public class CourseDaoImpl implements CourseDao{
    @Autowired
    CourseMapper courseMapper;

    @Override
	public List<Course> listCourseByUserId(BigInteger userId)
			throws IllegalArgumentException, CourseNotFoundException {
		if(!(userId.intValue() > 0)) {
            throw new IllegalArgumentException("用户ID格式错误！");
        }
		List<Course> courseList = courseMapper.listCourseByUserId(userId);
		if(courseList == null) {
            throw new CourseNotFoundException("没有找到课程！");
        }
		return courseList;
	}

    @Override
    public BigInteger insertCourseByUserId(BigInteger userId, Course course)
            throws IllegalArgumentException {
        if(!(userId.intValue() > 0)) {
            throw new IllegalArgumentException("用户ID格式错误！");
        }
        course.getTeacher().setId(userId);
        courseMapper.insertCourseByUserId(course);
        return BigInteger.valueOf(course.getId().intValue());
    }

    @Override
    public Course getCourseByCourseId(BigInteger courseId)
            throws IllegalArgumentException, CourseNotFoundException {
        if(!(courseId.intValue() > 0)) {
            throw new IllegalArgumentException("课程ID格式错误！");
        }
        Course course = courseMapper.getCourseByCourseId(courseId);
        if(course == null) {
            throw new CourseNotFoundException("没有找到课程！");
        }
        return course;
    }

    @Override
    public int updateCourseByCourseId(BigInteger courseId, Course course)
    {
        course.setId(courseId);
        int matchUpdateLines = courseMapper.updateCourseByCourseId(course);
        return matchUpdateLines;
    }

    @Override
    public int deleteCourseByCourseId(BigInteger courseId)
            throws IllegalArgumentException {
        if(!(courseId.intValue() > 0)) {
            throw new IllegalArgumentException("用户ID格式错误！");
        }
        int matchDeleteLines = courseMapper.deleteCourseByCourseId(courseId);
		/*if(matchDeleteLines == 0)
			throw new CourseNotFoundException("没有找到课程！");*/
        return matchDeleteLines;
    }

    @Override
    public List<Course> listCourseByCourseName(String courseName) {
        List<Course> courseList = courseMapper.listCourseByCourseName(courseName);
        return courseList;
    }
}
