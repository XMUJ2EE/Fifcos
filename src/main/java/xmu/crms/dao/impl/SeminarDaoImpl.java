package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.dao.SeminarDao;
import xmu.crms.entity.*;
import xmu.crms.mapper.SeminarMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 19:38
 */
@Repository("SeminarDao")
public class SeminarDaoImpl implements SeminarDao {

    @Autowired(required = false)
    SeminarMapper seminarMapper;

    @Override
    public List<Seminar> listSeminarByCourseId(BigInteger courseId) {
        return seminarMapper.listSeminarByCourseId(courseId);
    }

    @Override
    public Seminar getSeminarBySeminarId(BigInteger seminarId) {
        return seminarMapper.getSeminarBySeminarId(seminarId);
    }

    @Override
    public void deleteSeminarByCourseId(BigInteger seminarId) {
        seminarMapper.deleteSeminarByCourseId(seminarId);
    }

    @Override
    public void deleteTopicBySeminarId(BigInteger seminarId) {
        seminarMapper.deleteTopicBySeminarId(seminarId);
    }

    @Override
    public void deleteSeminarGroupBySeminarId(BigInteger seminarId) {
        seminarMapper.deleteSeminarGroupBySeminarId(seminarId);
    }

    @Override
    public void updateSeminarBySeminarId(Seminar seminar) {
        seminarMapper.updateSeminarBySeminarId(seminar);
    }

    @Override
    public SeminarGroupMember getSeminarGroupMemberByStudentId(BigInteger studentId) {
        return seminarMapper.getSeminarGroupMemberByStudentId(studentId);
    }

    @Override
    public SeminarGroup getSeminarGroupById(BigInteger id) {
        return seminarMapper.getSeminarGroupById(id);
    }

    @Override
    public Course getCourseById(BigInteger id) {
        return seminarMapper.getCourseById(id);
    }

    @Override
    public User getUserById(BigInteger id) {
        return seminarMapper.getUserById(id);
    }

    @Override
    public int insertSeminarByCourseId(Seminar seminar) {
        return seminarMapper.insertSeminarByCourseId(seminar);
    }

    @Override
    public void deleteSeminarById(BigInteger id) {
        seminarMapper.deleteSeminarById(id);
    }

    @Override
    public School getSchoolById(BigInteger id) {
        return seminarMapper.getSchoolById(id);
    }
}
