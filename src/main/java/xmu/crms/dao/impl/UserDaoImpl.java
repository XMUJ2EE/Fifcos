package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xmu.crms.dao.UserDao;
import xmu.crms.entity.*;
import xmu.crms.mapper.UserMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2017/12/30 22:24
 */
public class UserDaoImpl implements UserDao {

    @Autowired(required = false)
    UserMapper userMapper;
    @Override
    public User getUserByUserId(BigInteger id) {
        return userMapper.getUserByUserId(id);
    }

    @Override
    public void updateUserByUserId(BigInteger id, User user) {
        userMapper.updateUserByUserId(id, user);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public void unbindUserById(BigInteger id) {
        userMapper.unbindUserById(id);
    }

    @Override
    public List<Course> listCourseByTeacherId(BigInteger id) {
        return userMapper.listCourseByTeacherId(id);
    }

    @Override
    public List<Course> getCoursesByStudentId(BigInteger id) {
        return userMapper.getCoursesByStudentId(id);
    }

    @Override
    public School getSchoolByName(String name) {
        return userMapper.getSchoolByName(name);
    }

    @Override
    public School getSchoolById(BigInteger id) {
        return userMapper.getSchoolById(id);
    }

    @Override
    public User getUserByNumber(String number) {
        return userMapper.getUserByNumber(number);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userMapper.getUsersByName(name);
    }

    @Override
    public List<User> listUserByClassId(BigInteger classId, String numberBeginWith, String nameBeginWith) {
        return userMapper.listUserByClassId(classId, numberBeginWith, nameBeginWith);
    }

    @Override
    public BigInteger insertAttendanceById(BigInteger classId, BigInteger seminarId, BigInteger userId, int status) {
        return userMapper.insertAttendanceById(classId, seminarId, userId,status);
    }

    @Override
    public Location getLocationById(BigInteger classId, BigInteger seminarId) {
        return userMapper.getLocationById(classId, seminarId);
    }

    @Override
    public List<User> listAbsenceStudentById(BigInteger seminarId, BigInteger classId) {
        return userMapper.listAbsenceStudentById(seminarId, classId);
    }

    @Override
    public List<Attendance> listAttendanceById(BigInteger seminarId, BigInteger classId) {
        return userMapper.listAttendanceById(seminarId, classId);
    }

    @Override
    public List<User> listPresentStudent(BigInteger seminarId, BigInteger classId) {
        return userMapper.listPresentStudent(seminarId, classId);
    }

    @Override
    public List<User> listUnCallingStudent(BigInteger seminarId, BigInteger classId) {
        return userMapper.listUnCallingStudent(seminarId, classId);
    }

    @Override
    public User getUserByOpenId(String openid) {
        return userMapper.getUserByOpenId(openid);
    }
}
