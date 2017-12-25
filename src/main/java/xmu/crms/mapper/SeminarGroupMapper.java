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
     * 删除某小组所有成员
     * @param seminarGroupId
     * @return boolean
     * @author zhouwei
     * @date 18:00 2017/12/21 0021
     */

    /**
     * 在某小组中添加某个学生成员
     * @param userId groupId
     * @return boolean
     * @author zhouwei
     * @date 17:51 2017/12/21 0021
     */
    void insertSeminarGroupMemberById(@Param("userId") BigInteger userId, @Param("groupId") BigInteger groupId);
    /**
     *  根据小组的id找到小组的组长
     * @param groupId
     * @return BigInteger
     * @author zhouwei
     * @date 21:08 2017/12/21 0021
     */

    /**
     * 找到课堂下的所有小组
     * @param seminarId
     * @return List<SeminarGroup>
     * @author zhouwei
     * @date 22:17 2017/12/21 0021
     */




    int insertSeminarGroupMemberByGroupId(@Param("groupId") BigInteger groupId, @Param("seminarGroupMember") SeminarGroupMember seminarGroupMember);


    List<SeminarGroupTopic> listGroupByTopicId(@Param("topicId") BigInteger topicId)
            throws IllegalArgumentException,GroupNotFoundException;
    String insertTopicByGroupId(@Param("groupId") BigInteger groupId, @Param("topicId") BigInteger topicId)
            throws IllegalArgumentException,GroupNotFoundException;
    boolean resignLeaderById(@Param("groupId") BigInteger groupId, @Param("userId") BigInteger userId)
            throws IllegalArgumentException,GroupNotFoundException;
    boolean deleteTopicByGroupId(@Param("groupId") BigInteger groupId) throws GroupNotFoundException;
/*完成的*/
    void assignLeaderById(@Param("groupId") BigInteger groupId, @Param("userId") BigInteger userId);
    void automaticallyGrouping(@Param("seminarId") BigInteger seminarId, @Param("classId") BigInteger classId);

    void deleteSeminarGroupByGroupId(@Param("seminarGroupId") BigInteger seminarGroupId);

    void deleteSeminarGroupBySeminarId(@Param("seminarId") BigInteger seminarId);
    void deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId);
    SeminarGroup getSeminarGroupByGroupId(@Param("groupId") BigInteger groupId);

    BigInteger getSeminarGroupIdBySeminarIdAndUserId(@Param("seminarId") BigInteger seminarId, @Param("userId") BigInteger userId);
    BigInteger getSeminarGroupLeaderByGroupId(@Param("groupId") BigInteger groupId);
    List<SeminarGroupMember> listSeminarGroupMemberByGroupId(@Param("groupId") BigInteger groupId);
    int insertSeminarGroupBySeminarId(SeminarGroup seminarGroup);
    List<SeminarGroup> listSeminarGroupBySeminarId(@Param("seminarId") BigInteger seminarId);
    BigInteger getUserIdByUserId(@Param("userId") BigInteger userId);
    void deleteSeminarGroupMemberByuId(@Param("groupId")BigInteger grouId, @Param("userId")BigInteger userId);
    BigInteger getSeminarIdBySeminarId(@Param("seminarId") BigInteger seminarId);
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

    List<BigInteger> listGroupIdByTopicId(BigInteger topicId);
    //throw

    /**
     *根据id获取topic
     *
     * @param
     * @return
     * @author: YellowPure
     * @date: 21:44 2017/12/22
     */
    ClassInfo getSeminarById(BigInteger id);
    ClassInfo getClassInfoById(BigInteger id);
    Course getCourseById(BigInteger id);
    School getSchoolById(BigInteger id);
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