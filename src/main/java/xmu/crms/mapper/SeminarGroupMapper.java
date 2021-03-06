package xmu.crms.mapper;

import org.apache.ibatis.annotations.Param;
import xmu.crms.entity.*;
import xmu.crms.exception.GroupNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zw
 * @date 2017/12/20 0020
 * @time 14:19
 * @email 493703217@qq.com
 */
public interface SeminarGroupMapper {

    /**
     * 在某小组中添加某个学生成员
     * @param userId
     * @param groupId
     */
    void insertSeminarGroupMemberById(@Param("userId") BigInteger userId, @Param("groupId") BigInteger groupId);

    /**
     * 插入小组成员
     * @param groupId
     * @param seminarGroupMember
     * @return
     */
    int insertSeminarGroupMemberByGroupId(@Param("groupId") BigInteger groupId, @Param("seminarGroupMember") SeminarGroupMember seminarGroupMember);


    /**
     * 根据话题列出小组
     * @param topicId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    List<SeminarGroupTopic> listGroupByTopicId(@Param("topicId") BigInteger topicId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * 选题
     * @param groupId
     * @param topicId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    String insertTopicByGroupId(@Param("groupId") BigInteger groupId, @Param("topicId") BigInteger topicId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * 当组长
     * @param groupId
     * @param userId
     * @return
     * @throws IllegalArgumentException
     * @throws GroupNotFoundException
     */
    boolean resignLeaderById(@Param("groupId") BigInteger groupId, @Param("userId") BigInteger userId)
            throws IllegalArgumentException,GroupNotFoundException;

    /**
     * 取消选题
     * @param groupId
     * @return
     * @throws GroupNotFoundException
     */
    boolean deleteTopicByGroupId(@Param("groupId") BigInteger groupId) throws GroupNotFoundException;

    /**
     * 组长辞职
     * @param groupId
     * @param userId
     */
    void assignLeaderById(@Param("groupId") BigInteger groupId, @Param("userId") BigInteger userId);

    /**
     * 自动分组
     * @param seminarId
     * @param classId
     */
    void automaticallyGrouping(@Param("seminarId") BigInteger seminarId, @Param("classId") BigInteger classId);

    /**
     * 删除讨论课小组
     * @param seminarGroupId
     */
    void deleteSeminarGroupByGroupId(@Param("seminarGroupId") BigInteger seminarGroupId);

    /**
     * 删除讨论课小组
     * @param seminarId
     */
    void deleteSeminarGroupBySeminarId(@Param("seminarId") BigInteger seminarId);

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
    SeminarGroup getSeminarGroupByGroupId(@Param("groupId") BigInteger groupId);

    /**
     * 获得讨论课小组
     * @param seminarId
     * @param userId
     * @return
     */
    BigInteger getSeminarGroupIdBySeminarIdAndUserId(@Param("seminarId") BigInteger seminarId, @Param("userId") BigInteger userId);

    /**
     * 获得讨论课小组队长
     * @param groupId
     * @return
     */
    BigInteger getSeminarGroupLeaderByGroupId(@Param("groupId") BigInteger groupId);

    /**
     * 获得讨论课小组所有成员
     * @param groupId
     * @return
     */
    List<SeminarGroupMember> listSeminarGroupMemberByGroupId(@Param("groupId") BigInteger groupId);

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
    List<SeminarGroup> listSeminarGroupBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * 获得User
     * @param userId
     * @return
     */
    BigInteger getUserIdByUserId(@Param("userId") BigInteger userId);

    /**
     * 删除讨论课小组成员
     * @param grouId
     * @param userId
     */
    void deleteSeminarGroupMemberByuId(@Param("groupId")BigInteger grouId, @Param("userId")BigInteger userId);

    /**
     * 获得讨论课id
     * @param seminarId
     * @return
     */
    BigInteger getSeminarIdBySeminarId(@Param("seminarId") BigInteger seminarId);

    /**
     * 列出学生
     * @param SeminarId
     * @param ClassId
     * @return
     */
    List<BigInteger> listStudentIdBySeminarIdAndClassId(@Param("seminarId")BigInteger SeminarId,@Param("classId")BigInteger ClassId);
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
    SeminarGroupMember getSeminarGroupMemberByStudentIdAndSeminarGroupId(@Param("studentId") BigInteger studentId, @Param("seminarGroupId") BigInteger seminarGroupId);

    /**
     *小组按id选择话题.
     *
     * @param topicId 话题id
     * @param seminarGroupId 讨论组id
     * @return
     * @author: YellowPure
     * @date: 22:10 2017/12/22
     */
    void insertSeminarGroupTopicByTopicIdAndSeminarGroupId(@Param("topicId") BigInteger topicId, @Param("seminarGroupId") BigInteger seminarGroupId);



}