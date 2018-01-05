package xmu.crms.dao;

import org.apache.ibatis.annotations.Param;
import xmu.crms.entity.*;
import xmu.crms.exception.GroupNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 11:50
 */
public interface SeminarGroupDao {

    /**
     * 在某小组中添加某个学生成员
     * @param userId
     * @param groupId
     */
    void insertSeminarGroupMemberById(BigInteger userId, BigInteger groupId);

    /**
     * 插入小组成员
     * @param groupId
     * @param seminarGroupMember
     * @return
     */
    int insertSeminarGroupMemberByGroupId(BigInteger groupId, SeminarGroupMember seminarGroupMember);


    /**
     * 根据话题列出小组
     * @param topicId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    List<SeminarGroupTopic> listGroupByTopicId(BigInteger topicId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * 选题
     * @param groupId
     * @param topicId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    String insertTopicByGroupId(BigInteger groupId, BigInteger topicId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * 当组长
     * @param groupId
     * @param userId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    boolean resignLeaderById(BigInteger groupId, BigInteger userId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * 取消选题
     * @param groupId
     * @return
     * @throws GroupNotFoundException
     */
    boolean deleteTopicByGroupId(BigInteger groupId) throws GroupNotFoundException;

    /**
     * 组长辞职
     * @param groupId
     * @param userId
     */
    void assignLeaderById(BigInteger groupId, BigInteger userId);

    /**
     * 自动分组
     * @param seminarId
     * @param classId
     */
    void automaticallyGrouping(BigInteger seminarId, BigInteger classId);

    /**
     * 删除讨论课小组
     * @param seminarGroupId
     */
    void deleteSeminarGroupByGroupId(BigInteger seminarGroupId);

    /**
     * 删除讨论课小组
     * @param seminarId
     */
    void deleteSeminarGroupBySeminarId(BigInteger seminarId);

    /**
     * 删除讨论课小组成员
     * @param seminarGroupId
     */
    void deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId);

    /**
     * 获得讨论课小组
     * @param groupId
     * @return
     */
    SeminarGroup getSeminarGroupByGroupId(BigInteger groupId);

    /**
     * 获得讨论课小组
     * @param seminarId
     * @param userId
     * @return
     */
    BigInteger getSeminarGroupIdBySeminarIdAndUserId(BigInteger seminarId,BigInteger userId);

    /**
     * 获得讨论课小组队长
     * @param groupId
     * @return
     */
    BigInteger getSeminarGroupLeaderByGroupId(BigInteger groupId);

    /**
     * 获得讨论课小组所有成员
     * @param groupId
     * @return
     */
    List<SeminarGroupMember> listSeminarGroupMemberByGroupId(BigInteger groupId);

    /**
     * 创建讨论课小组
     * @param seminarGroup
     * @return
     */
    int insertSeminarGroupBySeminarId(SeminarGroup seminarGroup);

    /**
     * 列出讨论课小组
     * @param seminarId
     * @return
     */
    List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId);

    /**
     * 获得User
     * @param userId
     * @return
     */
    BigInteger getUserIdByUserId(BigInteger userId);

    /**
     * 删除讨论课小组成员
     * @param grouId
     * @param userId
     */
    void deleteSeminarGroupMemberByuId(BigInteger grouId, BigInteger userId);

    /**
     * 获得讨论课id
     * @param seminarId
     * @return
     */
    BigInteger getSeminarIdBySeminarId(BigInteger seminarId);

    /**
     * 列出学生
     * @param SeminarId
     * @param ClassId
     * @return
     */
    List<BigInteger> listStudentIdBySeminarIdAndClassId(BigInteger SeminarId,BigInteger ClassId);
    /**
     *获取某学生所有的讨论课小组.
     *
     * @param studentId 学生id
     * @return
     * @author: YellowPure
     * @date: 21:34 2017/12/22
     */
    List<SeminarGroupMember> listSeminarGroupIdByStudentId(BigInteger studentId);

    /**
     * 列出选了话题的小组
     * @param topicId
     * @return
     */
    List<BigInteger> listGroupIdByTopicId(BigInteger topicId);
    //throw

    /**
     * 获得讨论课
     * @param id
     * @return
     */
    ClassInfo getSeminarById(BigInteger id);

    /**
     * 获得class
     * @param id
     * @return
     */
    ClassInfo getClassInfoById(BigInteger id);

    /**
     * 获得课程
     * @param id
     * @return
     */
    Course getCourseById(BigInteger id);

    /**
     * 获得school
     * @param id
     * @return
     */
    School getSchoolById(BigInteger id);

    /**
     * 获得话题
     * @param id
     * @return
     */
    Topic getTopicById(BigInteger id);
    /**
     *根据id获取seminarGroup
     *
     * @param id 小组id
     * @return
     * @author: YellowPure
     * @date: 20:57 2017/12/22
     */
    SeminarGroup getSeminarGroupById(BigInteger id);

    /**
     *根据id获取user
     *
     * @param id 用户id
     * @return
     * @author: YellowPure
     * @date: 21:00 2017/12/22
     */
    User getUserById(BigInteger id);

    /**
     *查询该学生是否已经在小组里
     *
     * @param studentId 学生id
     * @param seminarGroupId 讨论组id
     * @return
     * @author: YellowPure
     * @date: 21:02 2017/12/22
     */
    SeminarGroupMember getSeminarGroupMemberByStudentIdAndSeminarGroupId(BigInteger studentId, BigInteger seminarGroupId);

    /**
     *小组按id选择话题.
     *
     * @param topicId 话题id
     * @param seminarGroupId 讨论组id
     * @return
     * @author: YellowPure
     * @date: 22:10 2017/12/22
     */
    void insertSeminarGroupTopicByTopicIdAndSeminarGroupId(BigInteger topicId, BigInteger seminarGroupId);
}
