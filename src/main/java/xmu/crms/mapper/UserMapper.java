package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xmu.crms.security.UserDetailsImpl;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

/**
 * User的mapper
 * @author mads
 */
@Mapper
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
    void updateUserByUserId(@Param("id") BigInteger id, @Param("user") User user);

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
    List<User> listUserByClassId(@Param("classId") BigInteger classId,
                                 @Param("numberBeginWith") String numberBeginWith,
                                 @Param("nameBeginWith") String nameBeginWith);

    /**
     * 小程序： 学生签到
     * @param classId
     * @param seminarId
     * @param userId
     * @param status
     */
    BigInteger insertAttendanceById(@Param("classId") BigInteger classId,
                              @Param("seminarId") BigInteger seminarId,
                              @Param("userId") BigInteger userId,
                              @Param("status") int status);

    /**
     * 根据classId和seminarId获取位置信息
     * @param classId
     * @param seminarId
     * @return
     */
    Location getLocationById(@Param("classId")BigInteger classId,
                             @Param("seminarId")BigInteger seminarId);

    /**
     * 查找某一节讨论课迟到的学生列表
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listAbsenceStudentById(@Param("seminarId") BigInteger seminarId,
                                      @Param("classId") BigInteger classId);

    /**
     * 查找某一节讨论课的签到名单
     * @param seminarId
     * @param classId
     * @return
     */
    List<Attendance> listAttendanceById(@Param("seminarId") BigInteger seminarId,
                                        @Param("classId") BigInteger classId);

    /**
     * 查找正常签到的学生
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listPresentStudent(@Param("seminarId") BigInteger seminarId,
                                  @Param("classId") BigInteger classId);

    /**
     * 查找正在上课的时候还没签到的同学
     * @param seminarId
     * @param classId
     * @return
     */
    List<User> listUnCallingStudent(@Param("seminarId")BigInteger seminarId,
                                    @Param("classId") BigInteger classId);

    /**
     * 根据手机号登陆用
     * @param phone
     * @return
     */
    UserDetailsImpl getUserByPhone(@Param("phone") String phone);

    /**
     * 根据openid登录用
     * @param openid
     * @return
     */
    UserDetailsImpl getUserByOpenId(@Param("openid") String openid);
}