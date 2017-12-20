package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.view.vo.SeminarGradeDetail;

import java.math.BigInteger;
import java.util.List;

/**
 * Grade Mapper
 * @author wang
 */

@Mapper
public interface GradeMapper {

    void deleteStudentScoreGroupByTopicId(BigInteger topicId);


    List<BigInteger> listSeminarGradeBySeminarGroupId(BigInteger userId, BigInteger seminarGroupId);

    BigInteger getSeminarGroupTopicId(BigInteger topicId, BigInteger groupId);
    void insertGroupGradeByUserId(BigInteger userId, BigInteger seminarGroupTopicId, BigInteger grade);



    void updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade);


    BigInteger getSeminarGroupIdByStudentId(BigInteger userId);
    List<BigInteger> listSeminarGradeByStudentId(BigInteger seminarGroupId);
    //List<BigInteger> listSeminarGradeByStudentId(BigInteger userId);






    List<BigInteger> listSeminar(BigInteger courseId);
    List<SeminarGradeDetail> listSeminarGrade(List<BigInteger> list);
    String getLeaderNameByLeaderId(BigInteger leaderId);
    String getSeminarNameBySeminarId(BigInteger seminarId);           //按courseId获取所有讨论课成绩

}
