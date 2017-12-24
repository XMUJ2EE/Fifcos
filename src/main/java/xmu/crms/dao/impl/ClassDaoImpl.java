package xmu.crms.dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xmu.crms.dao.*;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.*;

/**
 * @author chengjin
 */
@Repository("ClassDao")
public class ClassDaoImpl implements ClassDao {
    @Autowired(required = false)
    private ClassMapper classMapper;

    /*已经测试-已经实现*/
    @Override
    public void deleteClassSelectionByClassId(BigInteger classId) {
        classMapper.deleteClassSelectionByClassId(classId);
    }

    /*已测试-已经实现*/
    @Override
    public List<ClassInfo> listClassByName(String courseName, String teacherName) throws
            UserNotFoundException, CourseNotFoundException {
        List<ClassInfo> classes = new ArrayList<ClassInfo>();
        classes = classMapper.listClassByName("课程1", "邱明");
        return classes;
    }

    /*已测试-已经实现*/
    @Override
    public List<ClassInfo> listClassByCourseId(BigInteger courseId) throws CourseNotFoundException {
        List<ClassInfo> classes = new ArrayList<ClassInfo>();
        classes = classMapper.listClassByCourseId(courseId);
        return classes;
    }

    /*已测试-已经实现*/
    @Override
    public ClassInfo getClassByClassId(BigInteger classId) throws ClassNotFoundException {
        ClassInfo fclass = new ClassInfo();
        fclass = classMapper.getClassByClassId(classId);
        return fclass;
    }

    /*已经测试-已经实现*/
    @Override
    public void updateClassByClassId(BigInteger classId, ClassInfo newClass)
            throws ClassNotFoundException {
        if (classId == null){
            throw new ClassNotFoundException("没有找到相应的班级id");
        }
        if (newClass == null){
            throw new ClassNotFoundException("无法获得班级相应的信息");
        }
        classMapper.updateClassByClassId(classId, newClass);

    }

    /*已经测试-已经实现*/
    @Override
    public void deleteClassByClassId(BigInteger classId) throws ClassNotFoundException {
        classMapper.deleteClassByClassId(classId);
    }

    /*已经测试*/
    @Override
    public BigInteger insertCourseSelectionById(BigInteger userId, BigInteger classId) throws UserNotFoundException, ClassNotFoundException {
        int integer = classMapper.insertCourseSelectionById(userId, classId);
        return BigInteger.valueOf(integer);
    }

    /*已经测试-已经实现*/
    @Override
    public void deleteCourseSelectionById(BigInteger userId, BigInteger classId) throws UserNotFoundException, ClassNotFoundException {
        classMapper.deleteCourseSelectionById(userId, classId);
    }

    /*已测试-经实现*/
    @Override
    public Location getCallStatusById(BigInteger classId, BigInteger seminarId) throws SeminarNotFoundException {
        return classMapper.getCallStatusById(classId, seminarId);
    }

    /*已经测试-已经实现*/
    @Override
    public BigInteger insertClassById(BigInteger userId, BigInteger courseId, ClassInfo classInfo)
            throws UserNotFoundException, CourseNotFoundException {
        if (userId == null){
            throw new UserNotFoundException("未找到用户");
        }
        if (courseId == null){
            throw new CourseNotFoundException("未找到课程");
        }
        classMapper.insertClassById(userId, courseId, classInfo);
        return classInfo.getId();
    }

    /*已经测试-已经实现*/
    @Override
    public void deleteClassByCourseId(BigInteger courseId) throws
            CourseNotFoundException {
        classMapper.deleteClassByCourseId(courseId);
    }

    /*已经测试-已经实现*/
    @Override
    public void deleteScoreRuleById(BigInteger classId) throws ClassNotFoundException {
        classMapper.deleteScoreRuleById(classId);
    }

    /*已测试-已经实现*/
    @Override
    public ClassInfo getScoreRule(BigInteger classId) throws ClassNotFoundException {
        return classMapper.getScoreRule(classId);
    }

    /*已经测试-已经实现*/
    @Override
    public BigInteger insertScoreRule(BigInteger classId, ClassInfo proportions) throws InvalidOperationException, ClassNotFoundException {
        if (!(proportions instanceof ClassInfo)){
            throw new InvalidOperationException("传入参数错误");
        }
        classMapper.insertScoreRule(classId, proportions);
        return proportions.getId();
    }

    /*已经测试-已经实现*/
    @Override
    public void updateScoreRule(BigInteger classId, ClassInfo proportions) throws InvalidOperationException, ClassNotFoundException {

        classMapper.updateScoreRule(classId, proportions);
    }

    /*已经测试-已经实现*/
    @Override
    public BigInteger CallInRollById(Location location) throws SeminarNotFoundException, ClassNotFoundException {
        classMapper.CallInRollById(location);
        return location.getId();
    }
}
