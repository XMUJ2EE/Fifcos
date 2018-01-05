package xmu.crms.dao;

import org.apache.ibatis.annotations.Param;
import xmu.crms.entity.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 11:32
 */
public interface GradeDao {

    /**
     * 删除学生打分表
     * @param topicId
     */
    void deleteStudentScoreGroupByTopicId(BigInteger topicId);

    /**
     * 查看组的讨论课成绩
     * @param seminarGroupId
     * @return
     */
    SeminarGroup getSeminarGradeBySeminarGroupId(BigInteger seminarGroupId);


    /**
     * 获取FixGroup
     * @param fixGroupId
     * @return
     */
    FixGroup getFixGroupByFixGroupId(BigInteger fixGroupId);


    /**
     * 获取seminarGroupTopicId
     * @param topicId
     * @param groupId
     * @return
     */
    BigInteger getSeminarGroupTopicId(BigInteger topicId,
                                      BigInteger groupId);
    /**
     * 插入学生打分表
     * @param userId
     * @param seminarGroupTopicId
     * @param grade
     */
    void insertGroupGradeByUserId(BigInteger userId,
                                  BigInteger seminarGroupTopicId,
                                  BigInteger grade);


    /**
     * 更新设置小组报告分
     * @param seminarGroupId
     * @param grade
     */
    void updateGroupByGroupId(BigInteger seminarGroupId,
                              BigInteger grade);

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
    List<SeminarGroup> listSeminarGradeByUserId(BigInteger userId);


    /**
     * 获取所有讨论课成绩
     * @param courseId
     * @param userId
     * @return
     */
    List<SeminarGroup> listSeminarGradeByCourseId(BigInteger userId,
                                                  BigInteger courseId);

    /**
     * 获取课程的所有讨论课
     * @param courseId
     * @return
     */
    List<Seminar> listSeminarByCourseId(BigInteger courseId);

    /**
     * 获取学生对该组的打分
     * @param seminarGroupId
     * @return
     */
    List<BigInteger> listGrade(BigInteger seminarGroupId);

    /**
     * 更新展示得分
     * @param seminarGroupId
     * @param grade
     */
    void updatePresentationGradeByGroupId(BigInteger seminarGroupId,
                                          BigInteger grade);

    /**
     * 获取ClassId
     * @param seminarGroupId
     * @param seminarId
     * @return
     */
    BigInteger getClassId(BigInteger seminarGroupId,
                          BigInteger seminarId);

    /**
     * 获取报告分百分比
     * @param classId
     * @return
     */
    BigInteger getReportPresentationPercentage(BigInteger classId);

    /**
     * 获取报告分
     * @param seminarGroupId
     * @return
     */
    BigInteger getReportGrade(BigInteger seminarGroupId);

    /**
     * 获取展示分
     * @param seminarGroupId
     * @return
     */
    BigInteger getPresentationGrade(BigInteger seminarGroupId);

    /**
     * 更新最终成绩
     * @param seminarGroupId
     * @param grade
     */
    void updateFinalGrade(BigInteger seminarGroupId,
                          BigInteger grade);

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
    String getSeminarNameBySeminarId(BigInteger seminarId);

    /**
     * 学生打分方法
     * @param groupId
     * @param topicId
     * @param studentId
     * @return
     */
    Integer getGradeByGroupIdAndTopicIdAndStudentId(BigInteger groupId,BigInteger topicId,BigInteger studentId);
}
