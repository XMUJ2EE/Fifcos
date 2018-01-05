package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.dao.GradeDao;
import xmu.crms.entity.*;
import xmu.crms.mapper.GradeMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 11:36
 */
@Repository("GradeDao")
public class GradeDaoImpl implements GradeDao {

    @Autowired(required = false)
    GradeMapper gradeMapper;

    @Override
    public void deleteStudentScoreGroupByTopicId(BigInteger topicId) {
        gradeMapper.deleteStudentScoreGroupByTopicId(topicId);
    }

    @Override
    public SeminarGroup getSeminarGradeBySeminarGroupId(BigInteger seminarGroupId) {
        return gradeMapper.getSeminarGradeBySeminarGroupId(seminarGroupId);
    }

    @Override
    public FixGroup getFixGroupByFixGroupId(BigInteger fixGroupId) {
        return gradeMapper.getFixGroupByFixGroupId(fixGroupId);
    }

    @Override
    public BigInteger getSeminarGroupTopicId(BigInteger topicId, BigInteger groupId) {
        return gradeMapper.getSeminarGroupTopicId(topicId, groupId);
    }

    @Override
    public void insertGroupGradeByUserId(BigInteger userId, BigInteger seminarGroupTopicId, BigInteger grade) {
        gradeMapper.insertGroupGradeByUserId(userId, seminarGroupTopicId, grade);
    }

    @Override
    public void updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade) {
        gradeMapper.updateGroupByGroupId(seminarGroupId, grade);
    }

    @Override
    public List<BigInteger> getSeminarGroupIdByStudentId(BigInteger userId) {
        return gradeMapper.getSeminarGroupIdByStudentId(userId);
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByUserId(BigInteger userId) {
        return gradeMapper.listSeminarGradeByUserId(userId);
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByCourseId(BigInteger userId, BigInteger courseId) {
        return gradeMapper.listSeminarGradeByCourseId(userId, courseId);
    }

    @Override
    public List<Seminar> listSeminarByCourseId(BigInteger courseId) {
        return gradeMapper.listSeminarByCourseId(courseId);
    }

    @Override
    public List<BigInteger> listGrade(BigInteger seminarGroupId) {
        return gradeMapper.listGrade(seminarGroupId);
    }

    @Override
    public void updatePresentationGradeByGroupId(BigInteger seminarGroupId, BigInteger grade) {
        gradeMapper.updatePresentationGradeByGroupId(seminarGroupId, grade);
    }

    @Override
    public BigInteger getClassId(BigInteger seminarGroupId, BigInteger seminarId) {
        return gradeMapper.getClassId(seminarGroupId, seminarId);
    }

    @Override
    public BigInteger getReportPresentationPercentage(BigInteger classId) {
        return gradeMapper.getReportPresentationPercentage(classId);
    }

    @Override
    public BigInteger getReportGrade(BigInteger seminarGroupId) {
        return getReportPresentationPercentage(seminarGroupId);
    }

    @Override
    public BigInteger getPresentationGrade(BigInteger seminarGroupId) {
        return gradeMapper.getPresentationGrade(seminarGroupId);
    }

    @Override
    public void updateFinalGrade(BigInteger seminarGroupId, BigInteger grade) {
        gradeMapper.updateFinalGrade(seminarGroupId, grade);
    }

    @Override
    public User getUserByUserId(BigInteger id) {
        return gradeMapper.getUserByUserId(id);
    }

    @Override
    public School getSchoolById(BigInteger id) {
        return gradeMapper.getSchoolById(id);
    }

    @Override
    public ClassInfo getClassInfoByClassId(BigInteger id) {
        return gradeMapper.getClassInfoByClassId(id);
    }

    @Override
    public Seminar getSeminarBySeminarId(BigInteger id) {
        return gradeMapper.getSeminarBySeminarId(id);
    }

    @Override
    public String getLeaderNameByLeaderId(BigInteger leaderId) {
        return gradeMapper.getLeaderNameByLeaderId(leaderId);
    }

    @Override
    public String getSeminarNameBySeminarId(BigInteger seminarId) {
        return gradeMapper.getSeminarNameBySeminarId(seminarId);
    }

    @Override
    public Integer getGradeByGroupIdAndTopicIdAndStudentId(BigInteger groupId, BigInteger topicId, BigInteger studentId) {
        return gradeMapper.getGradeByGroupIdAndTopicIdAndStudentId(groupId, topicId, studentId);
    }
}
