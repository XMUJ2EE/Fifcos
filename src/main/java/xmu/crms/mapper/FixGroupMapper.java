package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupMember;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.List;


/**
 * @author zhangchubing
 */
@Mapper
@Component
public interface FixGroupMapper {
    /**
     * 按班级Id添加固定分组.
     * <p>按ClassId和UserId添加带有队长的固定分组<br>
     * @param classId 固定分组Id
     * @param leaderId  队长的Id
     * @return Integer 若创建成功返回该条记录的id，失败则返回-1
     */
    Integer insertFixGroupByClassId(FixGroup fixGroup);


    /**
     * 按FixGroupId删除FixGroupMember.
     * <p>按FixGroupId删除FixGroupMember<br>
     * @param fixGroupId 固定分组Id
     * @return Integer 若删除成功返回该条记录的id，失败则返回-1
     */
    Integer deleteFixGroupMemberByFixGroupId(@Param("fix_group_id") BigInteger fixGroupId);


    /**
     * 按FixGroupId和UserId删除FixGroupMember中某个学生.
     * <p>按FixGroupId和UserId删除FixGroupMember中的某个学生<br>
     * @param fixGroupId 固定分组Id
     * @param userId     组员的Id
     * @return Integer 若删除成功返回该条记录的id，失败则返回-1
     */
    Integer deleteFixGroupUserById(@Param("fix_group_id") BigInteger fixGroupId,@Param("student_id")  BigInteger userId);


    /**
     * 查询固定小组成员.
     * ＜p＞按照固定小组id查询该小组的成员<br>
     * @param groupId 要查询的固定小组id
     * @return List 固定小组成员信息
     */
    List<User> listFixGroupMemberByGroupId(@Param("fix_group_id") BigInteger groupId);


    /**
     * 按classId查找FixGroup信息.
     * <p>按classId查找FixGroup信息<br>
     * @param classId 班级Id
     * @return List 固定分组列表
     */
    List<FixGroup> listFixGroupByClassId(@Param("class_id") BigInteger classId);


    /**
     * 按classId删除FixGroup
     * <p>先根据classId得到所有的FixGroup信息，再根据FixGroupid删除FixGroupMember表的信息，最后再将FixGroup信息删除<br>*
     * @param classId 班级Id
     * @return Integer 若删除成功返回该条记录的id，失败则返回-1
     * @see FixGroupService #listFixGroupByClassId(BigInteger classId)
     * @see FixGroupService #deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId)
     */
    Integer deleteFixGroupByClassId(@Param("class_id") BigInteger classId);


    /**
     * 删除固定小组.
     * ＜p＞按照id删除固定小组<br>
     * @param groupId 固定小组的id
     * @return Integer 若删除成功返回该条记录的id，失败则返回-1
     * @see FixGroupService #deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId)
     */
    Integer deleteFixGroupByGroupId(@Param("group_id") BigInteger groupId);


    /**
     * 修改固定小组.
     * ＜p＞修改固定小组的信息（不包括成员）<br>
     * @param groupId    小组的id
     * @param fixGroupBO 小组信息
     * @return Integer 若更新成功返回该条记录的id，失败则返回-1
     */
    Integer updateFixGroupByGroupId(@Param("group_id") BigInteger groupId,@Param("fixGroupBO") FixGroup fixGroupBO);

    /**
     * 查询固定小组.
     * ＜p＞按照id查询某一固定小组的信息（包括成员）<br>
     * @param groupId 小组的id
     * @return List 固定小组成员列表对象，若未找到相关成员返回空(null)
     * @see FixGroupService #listFixGroupMemberByGroupId(BigInteger groupId)
     */
    List<FixGroupMember> listFixGroupByGroupId(@Param("fix_group_id") BigInteger groupId);


    /**
     * 将学生加入小组.
     * ＜p＞将用户加入指定的小组<br>
     * @param userId  学生的id
     * @param groupId 要加入小组的id
     * @return Integer 若创建成功返回该条记录的id，失败则返回-1
     */
    Integer insertStudentIntoGroup(@Param("student_id") BigInteger userId,@Param("fix_group_id") BigInteger groupId);


    /**
     * 按id获取小组.
     * <p>通过学生id和班级id获取学生所在的班级固定小组<br>
     * @param userId  学生id
     * @param classId 班级id
     * @return FixGroup 返回班级固定小组的信息
     * @see UserService#getUserByUserId(BigInteger UserId)
     */
    FixGroup getFixedGroupById(@Param("student_id") BigInteger userId,@Param("class_id")  BigInteger classId);


    /**
     * 将固定小组作为讨论课小组名单.
     * @param seminarId    讨论课ID
     * @param fixedGroupId 小组ID
     * @return Integer 若创建成功返回该条记录的id，失败则返回-1
     */
    Integer fixedGroupToSeminarGroup(@Param("seminar_id") BigInteger seminarId, @Param("fix_group_id") BigInteger fixedGroupId);
    /**
     * 将固定小组作为讨论课小组名单.
     *
     * @param seminarId    讨论课ID
     * @param fixedGroupId 小组ID
     * @return Integer 若创建成功返回该条记录的id，失败则返回-1
     */
    Integer fixedGroupMemberToSeminarGroupMember(@Param("seminar_group_id") BigInteger seminarId, @Param("fix_group_id") BigInteger fixedGroupId);


    /**
     * 自定义接口通过id得到固定分组.
     * @param fixedGroupId 小组ID
     * @return FixGroup 返回小组信息
     */
    FixGroup getFixGroupByFixGroupId(@Param("fix_group_id") BigInteger fixedGroupId);



}
