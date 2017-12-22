package xmu.crms.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.exception.GroupNotFoundException;
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
    public Boolean deleteStudentScoreGroupByTopicId(BigInteger topicId) {
        try{
            gradeMapper.deleteStudentScoreGroupByTopicId(topicId);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public SeminarGroup getSeminarGroupBySeminarGroupId(BigInteger userId, BigInteger seminarGroupId)
            throws GroupNotFoundException, IllegalArgumentException{
        return gradeMapper.listSeminarGradeBySeminarGroupId(userId, seminarGroupId);
    }

    @Override
    public Boolean insertGroupGradeByUserId(BigInteger topicId, BigInteger userId, BigInteger groupId, BigInteger grade) throws IllegalArgumentException{
        try{
            BigInteger seminarGroupTopicId = gradeMapper.getSeminarGroupTopicId(topicId, groupId);
            gradeMapper.insertGroupGradeByUserId(userId, seminarGroupTopicId, grade);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade) {
        try{
            gradeMapper.updateGroupByGroupId(seminarGroupId, grade);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByUserId(BigInteger userId) throws IllegalArgumentException{
        return gradeMapper.listSeminarGradeByStudentId(userId);
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByCourseId(BigInteger userId, BigInteger courseId) throws IllegalArgumentException{
        return gradeMapper.listSeminarGradeByCourseId(courseId);
    }

    @Override
    public void countPresentationGrade(BigInteger seminarId, BigInteger seminarGroupId) throws IllegalArgumentException {

    }

    @Override
    public void countGroupGradeBySerminarId(BigInteger seminarId, BigInteger seminarGroupId) throws IllegalArgumentException {

    }
}
