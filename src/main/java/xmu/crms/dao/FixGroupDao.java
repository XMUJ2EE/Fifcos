package xmu.crms.dao;

import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupMember;
import xmu.crms.entity.User;
import xmu.crms.exception.ClazzNotFoundException;
import xmu.crms.exception.FixGroupNotFoundException;
import xmu.crms.exception.InvalidOperationException;
import xmu.crms.exception.UserNotFoundException;

import java.math.BigInteger;
import java.util.List;

/**
 * @author mads
 * @date 2018/1/5 11:21
 */
public interface FixGroupDao {
    /**
     * 插入固定小组
     * @param fixGroup
     * @return
     * @throws IllegalArgumentException
     */
    Integer insertFixGroupByClassId(FixGroup fixGroup) throws IllegalArgumentException;

    /**
     * 书喊出固定小组成员
     * @param fixGroupId
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     */
    void deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId) throws IllegalArgumentException, FixGroupNotFoundException;

    /**
     * 删除组员
     * @param fixGroupId
     * @param userId
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     * @throws UserNotFoundException
     */
    void deleteFixGroupUserById(BigInteger fixGroupId, BigInteger userId) throws IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException;

    /**
     * 列出固定小组人员
     * @param groupId
     * @return
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     */
    List<User> listFixGroupMemberByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException;


    /**
     * 列出固定小组
     * @param classId
     * @return
     * @throws IllegalArgumentException
     * @throws ClazzNotFoundException
     */
    List<FixGroup> listFixGroupByClassId(BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException;

    /**
     * 删除固定小组
     * @param classId
     * @throws IllegalArgumentException
     * @throws ClazzNotFoundException
     */
    void deleteFixGroupByClassId(BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException;

    /**
     * 删除固定小组
     * @param groupId
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     */
    void deleteFixGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException;

    /**
     * 更新固定小组
     * @param groupId
     * @param fixGroupBO
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     */
    void updateFixGroupByGroupId(BigInteger groupId, FixGroup fixGroupBO) throws IllegalArgumentException, FixGroupNotFoundException;

    /**
     * 列出固定小组成员
     * @param groupId
     * @return
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     */
    List<FixGroupMember> listFixGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException;

    /**
     * 插入学生到固定小组
     * @param userId
     * @param groupId
     * @return
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     * @throws ClazzNotFoundException
     * @throws InvalidOperationException
     */
    Integer insertStudentIntoGroup(BigInteger userId, BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException,ClazzNotFoundException, InvalidOperationException;

    /**
     * 获得固定小组
     * @param userId
     * @param classId
     * @return
     * @throws IllegalArgumentException
     */
    FixGroup getFixedGroupById(BigInteger userId, BigInteger classId) throws IllegalArgumentException;

    /**
     * 固定小组转讨论课小组
     * @param semianrId
     * @param fixedGroupId
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     */
    void fixedGroupToSeminarGroup(BigInteger semianrId, BigInteger fixedGroupId) throws IllegalArgumentException, FixGroupNotFoundException ;

    /**
     * 获得固定小组
     * @param fixGroupId
     * @return
     * @throws IllegalArgumentException
     * @throws FixGroupNotFoundException
     */
    FixGroup getFixGroupByFixGroupId(BigInteger fixGroupId) throws IllegalArgumentException, FixGroupNotFoundException;

}
