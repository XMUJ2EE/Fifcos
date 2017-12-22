package xmu.crms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.mapper.GradeMapper;
import xmu.crms.service.GradeService;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * GradeService Impl
 * @author wang
 */

@Service
@Component
public class GradeServiceImpl implements GradeService {

    @Autowired(required = false)
    private GradeMapper gradeMapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        return gradeMapper.getSeminarGradeBySeminarGroupId(userId, seminarGroupId);
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
        return gradeMapper.listSeminarGradeByUserId(userId);
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByCourseId(BigInteger userId, BigInteger courseId) throws IllegalArgumentException{
        return gradeMapper.listSeminarGradeByCourseId(userId, courseId);
    }

    @Override
    public void countPresentationGrade(BigInteger seminarId, BigInteger seminarGroupId) throws IllegalArgumentException {
        List<BigInteger> list = gradeMapper.listGrade(seminarGroupId);
        BigInteger sum = BigInteger.valueOf(0);
        for (BigInteger grade: list) {
            sum = sum.add(grade);
        }
        BigInteger grade = sum.divide(BigInteger.valueOf(list.size()));
        gradeMapper.updatePresentationGradeByGroupId(seminarGroupId, grade);
    }

    @Override
    public void countGroupGradeBySerminarId(BigInteger seminarId, BigInteger seminarGroupId) throws IllegalArgumentException {
        Seminar seminar = gradeMapper.getSeminarBySeminarId(seminarId);
        BigInteger classId = gradeMapper.getClassId(seminarGroupId, seminarId);
        BigInteger reportPercentage = gradeMapper.getReportPresentationPercentage(classId);
        System.out.println(reportPercentage);
        BigInteger reportGrade = gradeMapper.getReportGrade(seminarGroupId);
        System.out.println(reportGrade);
        BigInteger presentationGrade = gradeMapper.getPresentationGrade(seminarGroupId);
        System.out.println(presentationGrade);
        BigInteger grade = reportGrade.multiply(reportPercentage).divide(BigInteger.valueOf(100)).
                add(presentationGrade.multiply(BigInteger.valueOf(100).subtract(reportPercentage)).divide(BigInteger.valueOf(100)));
        System.out.println(grade);
        gradeMapper.updateFinalGrade(seminarGroupId, grade);
    }
}
