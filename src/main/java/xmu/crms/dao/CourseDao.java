package xmu.crms.dao;

import xmu.crms.entity.Course;
import xmu.crms.exception.CourseNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 11:16
 */
public interface CourseDao {

    /**
     * 根据 userId 获取course
     * @param userId
     * @return
     */
    List<Course> listCourseByUserId(BigInteger userId) throws IllegalArgumentException, CourseNotFoundException;

    /**
     * 根据userId 和 插入course
     * @param userId
     * @param course
     * @return
     */
    BigInteger insertCourseByUserId(BigInteger userId, Course course);

    /**
     * 根据courseId获取course
     * @param courseId
     * @return
     */
    Course getCourseByCourseId(BigInteger courseId) throws IllegalArgumentException, CourseNotFoundException;

    /**
     * 根据courseId更新course
     * @param courseId
     * @param course
     * @return
     */
    int updateCourseByCourseId(BigInteger courseId, Course course);

    /**
     * 根据courseId 删除course
     * @param courseId
     * @return
     */
    int deleteCourseByCourseId(BigInteger courseId);

    /**
     * 根据courseName列出course
     * @param courseName
     * @return
     */
    List<Course> listCourseByCourseName(String courseName);
}
