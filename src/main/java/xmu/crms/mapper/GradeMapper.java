package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xmu.crms.entity.*;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.view.vo.SeminarGradeDetail;

import java.math.BigInteger;
import java.util.List;

/**
 * Grade Mapper
 * @author wang
 */

public interface GradeMapper {

    /**
     * 删除学生打分表
     * @param topicId
     */
    void deleteStudentScoreGroupByTopicId(BigInteger topicId);

    /**
     * 查看组的讨论课成绩
     * @param userId
     * @param seminarGroupId
     * @return
     */
    SeminarGroup listSeminarGradeBySeminarGroupId(@Param("userId") BigInteger userId,
                                                  @Param("seminarGroupId") BigInteger seminarGroupId);


    /**
     * 获取seminarGroupTopicId
     * @param topicId
     * @param groupId
     * @return
     */
    BigInteger getSeminarGroupTopicId(@Param("topicId")BigInteger topicId,
                                      @Param("groupId")BigInteger groupId);
    /**
     * 插入学生打分表
     * @param userId
     * @param seminarGroupTopicId
     * @param grade
     */
    void insertGroupGradeByUserId(@Param("userId")BigInteger userId,
                                  @Param("seminarGroupTopicId")BigInteger seminarGroupTopicId,
                                  @Param("grade")BigInteger grade);


    /**
     * 更新设置小组报告分
     * @param seminarGroupId
     * @param grade
     */
    void updateGroupByGroupId(@Param("seminarGroupId")BigInteger seminarGroupId,
                              @Param("grade")BigInteger grade);

    /**
     * 获取SeminarGroupId
     * @param userId
     * @return
     */
    List<BigInteger> getSeminarGroupIdByStudentId(BigInteger userId);

    /**
     * 获取学生所有讨论课成绩
     * @param userId
     * @return
     */
    List<SeminarGroup> listSeminarGradeByStudentId(BigInteger userId);


    /**
     * 获取所有讨论课成绩
     * @param courseId
     * @return
     */
    List<SeminarGroup> listSeminarGradeByCourseId(BigInteger courseId);

    /**
     * 获取课程的所有讨论课
     * @param courseId
     * @return
     */
    List<Seminar> listSeminarByCourseId(BigInteger courseId);

    /**
     * 获取用户
     * @param id
     * @return
     */
    User getUserByUserId(BigInteger id);

    /**
     * 获取学校
     * @param id
     * @return
     */
    School getSchoolById(BigInteger id);


    /**
     * 获取班级信息
     * @param id
     * @return
     */
    ClassInfo getClassInfoByClassId(BigInteger id);

    /**
     * 获取Seminar
     * @param id
     * @return
     */
    Seminar getSeminarBySeminarId(BigInteger id);

    /**
     * 获取学生姓名
     * @param leaderId
     * @return
     */
    String getLeaderNameByLeaderId(BigInteger leaderId);

    /**
     * 获取讨论课名称
     * @param seminarId
     * @return
     */
    String getSeminarNameBySeminarId(BigInteger seminarId);           //按courseId获取所有讨论课成绩

}
