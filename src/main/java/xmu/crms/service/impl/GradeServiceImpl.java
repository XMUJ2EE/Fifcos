package xmu.crms.service.impl;

import org.springframework.stereotype.Service;
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

    private GradeMapper gradeMapper;

    @Override
    public void deleteStudentScoreGroupByTopicId(BigInteger topicId) {
        gradeMapper.deleteStudentScoreGroupByTopicId(gradeMapper.listSeminarGroupTopicId(topicId));
    }

    @Override
    public List<BigInteger> listSeminarGradeBySeminarGroupId(BigInteger userId, BigInteger seminarGroupId) {
        return gradeMapper.listSeminarGradeBySeminarGroupId(userId, seminarGroupId);
    }

    @Override
    public void insertGroupGradeByUserId(BigInteger userId, BigInteger seminarId, BigInteger groupId, BigInteger grade) {

    }

    @Override
    public void updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade) {
        gradeMapper.updateGroupByGroupId(seminarGroupId, grade);
    }

    @Override
    public List<BigInteger> listSeminarGradeByStudentId(BigInteger userId) {
        BigInteger seminarGroupId = gradeMapper.getSeminarGroupIdByStudentId(userId);
        return gradeMapper.listSeminarGradeByStudentId(seminarGroupId);
    }
}
