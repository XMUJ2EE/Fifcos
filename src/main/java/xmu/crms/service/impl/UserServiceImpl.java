package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import xmu.crms.entity.Attendance;
import xmu.crms.entity.Course;
import xmu.crms.entity.User;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.mapper.UserMapper;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.List;

/**
 * UserService Impl
 * @author mads
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public Boolean insertAttendanceById(BigInteger classId, BigInteger seminarId, BigInteger userId, double longitude, double latitude) throws ClassesNotFoundException, SeminarNotFoundException {
        return null;
    }

    @Override
    public List<Attendance> listAttendanceById(BigInteger classId, BigInteger seminarId) throws ClassesNotFoundException, SeminarNotFoundException {
        return null;
    }

    @Override
    public User signUpPhone(User user) throws Exception{
        User fakeUser = userMapper.getUserByNumber(user.getNumber());
        if(fakeUser != null){
            throw new Exception("用户已存在");
        }
        userMapper.insertUser(user);
        return userMapper.getUserByNumber(user.getNumber());
    }

    @Override
    public boolean deleteTeacherAccount(BigInteger userId) throws UserNotFoundException {
        return false;
    }

    @Override
    public Boolean deleteStudentAccount(BigInteger userId) throws UserNotFoundException {
        return null;
    }

    @Override
    public User getUserByUserId(BigInteger userId) throws UserNotFoundException {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public List<BigInteger> listUserIdByUserName(String userName) throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateUserByUserId(BigInteger userId, User user) throws UserNotFoundException {
        userMapper.updateUserByUserId(userId, user);
    }

    @Override
    public List<User> listUserByClassId(BigInteger classId, String numBeginWith, String nameBeginWith) throws ClassesNotFoundException {
        return null;
    }

    @Override
    public List<User> listUserByUserName(String userName) throws UserNotFoundException {
        return null;
    }

    @Override
    public List<User> listPresentStudent(BigInteger seminarId, BigInteger classId) {
        return null;
    }

    @Override
    public List<User> listAbsenceStudent(BigInteger seminarId, BigInteger classId) {
        return null;
    }

    @Override
    public List<Course> listCourseByTeacherName(String teacherName) {
        return null;
    }
}
