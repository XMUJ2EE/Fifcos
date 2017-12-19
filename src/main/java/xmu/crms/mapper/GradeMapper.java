package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import xmu.crms.service.SeminarGroupService;

import java.math.BigInteger;
import java.util.List;

/**
 * Grade Mapper
 * @author wang
 */

@Mapper
public interface GradeMapper {

    //void deleteStudentScoreGroupByTopicId(BigInteger topicId);

    List<BigInteger> listSeminarGroupTopicId(BigInteger topicId);
    void deleteStudentScoreGroupByTopicId(List<BigInteger> list);

    List<BigInteger> listSeminarGradeBySeminarGroupId(BigInteger userId, BigInteger seminarGroupId);




    void insertGroupGradeByUserId(BigInteger userId, BigInteger seminarId, BigInteger groupId, BigInteger grade);





    void updateGroupByGroupId(BigInteger seminarGroupId, BigInteger grade);

    BigInteger getSeminarGroupIdByStudentId(BigInteger userId);
    List<BigInteger> listSeminarGradeByStudentId(BigInteger seminarGroupId);
    //List<BigInteger> listSeminarGradeByStudentId(BigInteger userId);
}
