package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.UserDao;
import xmu.crms.entity.Attendance;
import xmu.crms.entity.Course;
import xmu.crms.entity.User;
import xmu.crms.exception.ClassesNotFoundException;
import xmu.crms.exception.SeminarNotFoundException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.List;

/**
 * UserService Impl
 * @author mads
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean insertAttendanceById(BigInteger classId, BigInteger seminarId, BigInteger userId, double longitude, double latitude) throws ClassesNotFoundException, SeminarNotFoundException {
        return null;
    }

    @Override
    public List<Attendance> listAttendanceById(BigInteger classId, BigInteger seminarId) throws ClassesNotFoundException, SeminarNotFoundException {
        return null;
    }

    @Override
    public User signUpPhone(User user) {
        return null;
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
        return userDao.getUserByUserId(userId);
    }

    @Override
    public List<BigInteger> listUserIdByUserName(String userName) throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateUserByUserId(BigInteger userId, User user) throws UserNotFoundException {

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
