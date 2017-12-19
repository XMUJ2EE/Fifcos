package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.Course;
import xmu.crms.entity.School;
import xmu.crms.entity.User;

import java.math.BigInteger;
import java.util.List;

/**
 * User的mapper
 * @author mads
 */
public interface UserMapper {
    /**
     * 根据用户名查找用户
     *
     * @param id
     * @return
     */
    User getUserByUserId(BigInteger id);

    /**
     * 更新用户
     *
     * @param id
     * @param user
     */
    void updateUserByUserId(BigInteger id, User user);

    /**
     * 新增用户
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUserByUserId(BigInteger id);

    /**
     * 根据老师id查找老师的课程
     * @param id
     * @return
     */
    List<Course> getCoursesByTeacherId(BigInteger id);

    /**
     * 根据学生id查找学生选的课程
     * @param id
     * @return
     */
    List<Course> getCoursesByStudentId(BigInteger id);

    /**
     * 根据学校名字获得学校
     * @param name
     * @return
     */
    School getSchoolByName(String name);

    /**
     * 根据学校id获取学校
     * @param id
     * @return
     */
    School getSchoolById(BigInteger id);

    /**
     * 根据学号拿学生
     * @param number
     * @return
     */
    User getUserByNumber(String number);
}