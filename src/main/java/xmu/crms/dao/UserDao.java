package xmu.crms.dao;

import org.apache.ibatis.annotations.Param;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2017/12/30 22:21
 */
public interface UserDao {
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
    void updateUserByUserId(BigInteger id,User user);

    /**
     * 新增用户
     *
     * @param user
     */
    int insertUser(User user);

    /**
     * 根据id解绑用户，就是置手机号为空
     * @param id
     */
    void unbindUserById(BigInteger id);

    /**
     * 根据老师id查找老师的课程
     * @param id
     * @return
     */
    List<Course> listCourseByTeacherId(BigInteger id);

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

    /**
     * 根据姓名拿老师，老师可能重名
     * @param name
     * @return
     */
    List<User> getUsersByName(String name);

    /**
     * 根据班级查找学生列表
     * @param classId
     * @return
     */
    List<User> listUserByClassId(BigInteger classId,
                                 String numberBeginWith,
                                 String nameBeginWith);

    /**
     * 小程序： 学生签到
     * @param classId
     * @param seminarId
     * @param userId
     * @param status
     */
    BigInteger insertAttendanceById(BigInteger classId,
                                    BigInteger seminarId,
                                    BigInteger userId,
                                    int status);

    /**
     * 根据classId和seminarId获取位置信息
     * @param classId
     * @param seminarId
     * @return
     */
    Location getLocationById(BigInteger classId,
                             BigInteger seminarId);

    /**
     * 查找某一节讨论课迟到的学生列表
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listAbsenceStudentById(BigInteger seminarId,
                                     BigInteger classId);

    /**
     * 查找某一节讨论课的签到名单
     * @param seminarId
     * @param classId
     * @return
     */
    List<Attendance> listAttendanceById(BigInteger seminarId,
                                        BigInteger classId);

    /**
     * 查找正常签到的学生
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listPresentStudent(BigInteger seminarId,
                                  BigInteger classId);

    /**
     * 查找正在上课的时候还没签到的同学
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listUnCallingStudent(BigInteger seminarId,
                                    BigInteger classId);

    /**
     * 根据openid拿用户
     * @param openid
     * @return
     */
    User getUserByOpenId(String openid);
}
