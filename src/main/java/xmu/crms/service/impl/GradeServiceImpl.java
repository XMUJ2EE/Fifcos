package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.mapper.GradeMapper;
import xmu.crms.service.GradeService;

import java.math.BigInteger;
import java.util.List;

/**
 * GradeService Impl
 * @author wang
 */

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired(required = false)
    private GradeMapper gradeMapper;

    @Override
    public void deleteStudentScoreGroupByTopicId(BigInteger topicId) {
        gradeMapper.deleteStudentScoreGroupByTopicId(topicId);
    }

    @Override
    public SeminarGroup listSeminarGradeBySeminarGroupId(BigInteger userId, BigInteger seminarGroupId) {
        return gradeMapper.listSeminarGradeBySeminarGroupId(userId, seminarGroupId);
    }

    @Override
    public void insertGroupGradeByUserId(BigInteger topicId, BigInteger userId,
                                         BigInteger seminarId, BigInteger groupId, BigInteger grade) {

        BigInteger seminarGroupTopicId = gradeMapper.getSeminarGroupTopicId(topicId, groupId);
        gradeMapper.insertGroupGradeByUserId(userId, seminarGroupTopicId, grade);
    }

    @Override
    public void updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade) {
        gradeMapper.updateGroupByGroupId(seminarGroupId, grade);
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByStudentId(BigInteger userId) {
        return gradeMapper.listSeminarGradeByStudentId(userId);
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByCourseId(BigInteger courseId) {
        return gradeMapper.listSeminarGradeByCourseId(courseId);
    }
}
