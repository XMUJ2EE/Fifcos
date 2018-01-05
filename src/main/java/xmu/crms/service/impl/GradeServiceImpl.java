package xmu.crms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xmu.crms.dao.GradeDao;
import xmu.crms.dao.SeminarGroupDao;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.exception.GroupNotFoundException;
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

    @Autowired
    private GradeDao gradeDao;
    @Autowired
    SeminarGroupDao seminarGroupDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void deleteStudentScoreGroupByTopicId(BigInteger topicId) throws IllegalArgumentException{
            gradeDao.deleteStudentScoreGroupByTopicId(topicId);

    }

    @Override
    public SeminarGroup getSeminarGroupBySeminarGroupId(BigInteger seminarGroupId)
            throws GroupNotFoundException, IllegalArgumentException{
        return gradeDao.getSeminarGradeBySeminarGroupId(seminarGroupId);
    }

    @Override
    public void insertGroupGradeByUserId(BigInteger topicId, BigInteger userId, BigInteger groupId, BigInteger grade) throws IllegalArgumentException{
            BigInteger seminarGroupTopicId = gradeDao.getSeminarGroupTopicId(topicId, groupId);
            gradeDao.insertGroupGradeByUserId(userId, seminarGroupTopicId, grade);

    }

    @Override
    public void updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade)
            throws GroupNotFoundException, IllegalArgumentException{

            gradeDao.updateGroupByGroupId(seminarGroupId, grade);

    }

    @Override
    public List<SeminarGroup> listSeminarGradeByUserId(BigInteger userId) throws IllegalArgumentException{
        return gradeDao.listSeminarGradeByUserId(userId);
    }

    @Override
    public List<SeminarGroup> listSeminarGradeByCourseId(BigInteger userId, BigInteger courseId) throws IllegalArgumentException{
        return gradeDao.listSeminarGradeByCourseId(userId, courseId);
    }

    @Override
    public void countPresentationGrade(BigInteger seminarId) throws IllegalArgumentException {
        List<SeminarGroup> seminarGroups = seminarGroupDao.listSeminarGroupBySeminarId(seminarId);
        for(SeminarGroup seminarGroup :seminarGroups)
        {
            BigInteger seminarGroupId =seminarGroup.getId();
            List<BigInteger> list = gradeDao.listGrade(seminarGroupId);
            BigInteger sum = BigInteger.valueOf(0);
            for (BigInteger grade: list) {
                sum = sum.add(grade);
            }
            BigInteger grade = sum.divide(BigInteger.valueOf(list.size()));
            gradeDao.updatePresentationGradeByGroupId(seminarGroupId, grade);
        }

    }

    @Override
    public void countGroupGradeBySeminarId(BigInteger seminarId) throws IllegalArgumentException {
        countPresentationGrade(seminarId);
        List<SeminarGroup> seminarGroups = seminarGroupDao.listSeminarGroupBySeminarId(seminarId);
        for(SeminarGroup seminarGroup :seminarGroups) {
            BigInteger seminarGroupId =seminarGroup.getId();
            Seminar seminar = gradeDao.getSeminarBySeminarId(seminarId);
            BigInteger classId = gradeDao.getClassId(seminarGroupId, seminarId);
            BigInteger reportPercentage = gradeDao.getReportPresentationPercentage(classId);
            System.out.println(reportPercentage);
            BigInteger reportGrade = gradeDao.getReportGrade(seminarGroupId);
            System.out.println(reportGrade);
            BigInteger presentationGrade = gradeDao.getPresentationGrade(seminarGroupId);
            System.out.println(presentationGrade);
            BigInteger grade = reportGrade.multiply(reportPercentage).divide(BigInteger.valueOf(100)).
                    add(presentationGrade.multiply(BigInteger.valueOf(100).subtract(reportPercentage)).divide(BigInteger.valueOf(100)));
            System.out.println(grade);
            gradeDao.updateFinalGrade(seminarGroupId, grade);
        }
    }
    @Override
    public Integer getGradeByGroupIdAndTopicIdAndStudentId(BigInteger groupId, BigInteger topicId, BigInteger studentId) {
        Integer grade=gradeDao.getGradeByGroupIdAndTopicIdAndStudentId(groupId, topicId, studentId);
        if(grade==null){
            grade = new Integer("-1");
        }
        return grade;
    }
}
