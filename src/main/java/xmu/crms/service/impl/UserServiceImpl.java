package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import xmu.crms.dao.LoginDao;
import xmu.crms.dao.UserDao;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.List;

/**
 * UserService Impl
 * @author mads
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private LoginDao authDao;

    @Autowired(required = false)
    private UserDao userDao;

    @Override
    public BigInteger insertAttendanceById(BigInteger classId, BigInteger seminarId, BigInteger userId, double longitude, double latitude) throws LocationNotFoundException, InvalidOperationException {
        // 判断签到地点是否正确
        Location location = userDao.getLocationById(classId, seminarId);
        if(location == null){
            throw new LocationNotFoundException("位置错误: 未发起签到或系统错误");
        }
        if(!(Math.abs(location.getLongitude() - longitude) <= 30 && Math.abs(location.getLatitude() - latitude) <=30)){
            throw new InvalidOperationException("签到地点不匹配");
        }
        // Location有三种状态， CALLING 1 表示正在签到 END 0 表示结束签到 BREAK 2 表示讨论课结束
        // Attendance有三种状态 PRESENT 0 正常签到 LATE 1 迟到 ABSENCE 2 缺勤
        if(location.getStatus().equals(Location.CALLING)){
            BigInteger insert=userDao.insertAttendanceById(classId, seminarId, userId, Attendance.PRESENT);
            List<Attendance> attendances=userDao.listAttendanceById(classId, seminarId);
            BigInteger attendanceId=new BigInteger("0");
            for(Attendance attendance:attendances){
                if(attendance.getStudent().getId().equals(userId)){
                    attendanceId=attendance.getId();
                }
            }
            return attendanceId;
        }else if(location.getStatus().equals(Location.END)){
            BigInteger insert=userDao.insertAttendanceById(classId, seminarId, userId, Attendance.LATE);
            List<Attendance> attendances=userDao.listAttendanceById(classId, seminarId);
            BigInteger attendanceId=new BigInteger("0");
            for(Attendance attendance:attendances){
                if(attendance.getStudent().getId().equals(userId)){
                    attendanceId=attendance.getId();
                }
            }
            return attendanceId;
        }else {
            BigInteger insert=userDao.insertAttendanceById(classId, seminarId, userId, Attendance.ABSENCE);
            List<Attendance> attendances=userDao.listAttendanceById(classId, seminarId);
            BigInteger attendanceId=new BigInteger("0");
            for(Attendance attendance:attendances){
                if(attendance.getStudent().getId().equals(userId)){
                    attendanceId=attendance.getId();
                }
            }
            return attendanceId;
        }
    }

    @Override
    public List<Attendance> listAttendanceById(BigInteger classId, BigInteger seminarId) throws LocationNotFoundException{
        List<Attendance> attendances = userDao.listAttendanceById(seminarId, classId);
        if(attendances == null){
            throw new LocationNotFoundException("未找到次讨论课的签到名单");
        }
        return attendances;
    }

    @Override
    public User signUpPhone(User user) throws UserDuplicatedException {
        if(user.getPhone() != null){
            UserDetails fakeUser = authDao.getUserByPhone(user.getPhone());
            if(fakeUser != null){
                throw new UserDuplicatedException("学号已存在");
            }
        }
        userDao.insertUser(user);
        return userDao.getUserByUserId(user.getId());
    }

    @Override
    public void deleteTeacherAccount(BigInteger userId) throws UserNotFoundException {
        User fakeUser = userDao.getUserByUserId(userId);
        if(fakeUser == null){
            throw new UserNotFoundException("用户不存在");
        }
        userDao.unbindUserById(userId);
    }

    @Override
    public void deleteStudentAccount(BigInteger userId) throws UserNotFoundException {
        User fakeUser = userDao.getUserByUserId(userId);
        if(fakeUser == null){
            throw new UserNotFoundException("用户不存在");
        }
        userDao.unbindUserById(userId);
    }

    @Override
    public User getUserByUserId(BigInteger userId) throws UserNotFoundException {
        User fakeUser = userDao.getUserByUserId(userId);
        if(fakeUser == null){
            throw new UserNotFoundException("用户不存在");
        }
        return fakeUser;
    }

    @Override
    public List<BigInteger> listUserIdByUserName(String userName) throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateUserByUserId(BigInteger userId, User user) throws UserNotFoundException {
        User fakeUser = userDao.getUserByUserId(userId);
        if(fakeUser == null){
            throw new UserNotFoundException("用户没找到");
        }
        userDao.updateUserByUserId(userId, user);
    }

    @Override
    public List<User> listUserByClassId(BigInteger classId, String numBeginWith, String nameBeginWith) throws ClazzNotFoundException {
        try{
            List<User> users = userDao.listUserByClassId(BigInteger.valueOf(1), numBeginWith, nameBeginWith);
            return users;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public List<User> listUserByUserName(String userName) throws UserNotFoundException {
        List<User> fakeUsers = userDao.getUsersByName(userName);
        if(fakeUsers == null){
            throw new UserNotFoundException("没有叫"+userName+"的用户");
        }
        return fakeUsers;
    }

    @Override
    public List<User> listPresentStudent(BigInteger seminarId, BigInteger classId)throws LocationNotFoundException {
        List<User> users = userDao.listPresentStudent(seminarId, classId);
        if(users == null || users.isEmpty()){
            throw new LocationNotFoundException("未找到指定讨论课");
        }
        return users;
    }

    @Override
    public List<User> listAbsenceStudent(BigInteger seminarId, BigInteger classId) throws LocationNotFoundException{
        List<User> users = userDao.listUnCallingStudent(seminarId, classId);
        if(users == null || users.isEmpty()){
            throw new LocationNotFoundException("未找到指定讨论课的签到名单");
        }
        return users;
    }

    @Override
    public List<Course> listCourseByTeacherName(String teacherName) throws UserNotFoundException, CourseNotFoundException{
        List<User> users = userDao.getUsersByName(teacherName);
        if(users == null){
            throw new UserNotFoundException("没有叫"+teacherName+"的老师");
        }
        List<Course> courses = userDao.listCourseByTeacherId(users.get(0).getId());
        if(courses == null){
            throw new CourseNotFoundException("老师还没有课程");
        }
        return courses;
    }
}
