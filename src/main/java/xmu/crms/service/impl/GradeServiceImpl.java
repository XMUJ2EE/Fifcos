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
import xmu.crms.mapper.SeminarGroupMapper;
import xmu.crms.service.GradeService;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * GradeService Impl
 * @author wang
 */

@Service("GradeService")
@Component
public class GradeServiceImpl implements GradeService {

    @Autowired(required = false)
    private GradeMapper gradeMapper;
    @Autowired(required = false)
    SeminarGroupMapper seminarGroupMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void deleteStudentScoreGroupByTopicId(BigInteger topicId) throws IllegalArgumentException{
            gradeMapper.deleteStudentScoreGroupByTopicId(topicId);

    }

    @Override
    public SeminarGroup getSeminarGroupBySeminarGroupId(BigInteger seminarGroupId)
            throws GroupNotFoundException, IllegalArgumentException{
        return gradeMapper.getSeminarGradeBySeminarGroupId(seminarGroupId);
    }

    @Override
    public void insertGroupGradeByUserId(BigInteger topicId, BigInteger userId, BigInteger groupId, BigInteger grade) throws IllegalArgumentException{
            BigInteger seminarGroupTopicId = gradeMapper.getSeminarGroupTopicId(topicId, groupId);
            gradeMapper.insertGroupGradeByUserId(userId, seminarGroupTopicId, grade);

    }

    @Override
    public void updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade)
            throws GroupNotFoundException, IllegalArgumentException{

            gradeMapper.updateGroupByGroupId(seminarGroupId, grade);

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
    public void countPresentationGrade(BigInteger seminarId) throws IllegalArgumentException {
        List<SeminarGroup> seminarGroups = seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
        for(SeminarGroup seminarGroup :seminarGroups)
        {
            BigInteger seminarGroupId =seminarGroup.getId();
            List<BigInteger> list = gradeMapper.listGrade(seminarGroupId);
            BigInteger sum = BigInteger.valueOf(0);
            for (BigInteger grade: list) {
                sum = sum.add(grade);
            }
            BigInteger grade = sum.divide(BigInteger.valueOf(list.size()));
            gradeMapper.updatePresentationGradeByGroupId(seminarGroupId, grade);
        }

    }

    @Override
    public void countGroupGradeBySeminarId(BigInteger seminarId) throws IllegalArgumentException {
        countPresentationGrade(seminarId);
        List<SeminarGroup> seminarGroups = seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
        for(SeminarGroup seminarGroup :seminarGroups) {
            BigInteger seminarGroupId =seminarGroup.getId();
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
}
