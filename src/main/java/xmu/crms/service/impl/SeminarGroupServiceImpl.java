package xmu.crms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.SeminarGroupMember;
import xmu.crms.entity.User;
import xmu.crms.exception.*;
import xmu.crms.mapper.SeminarGroupMapper;
import xmu.crms.service.SeminarGroupService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zw
 * @date 2017/12/20 0020
 * @time 14:19
 * @email 493703217@qq.com
 */
@Service
public class SeminarGroupServiceImpl implements SeminarGroupService {
      @Autowired(required = false)
      SeminarGroupMapper seminarGroupMapper;

//
//
      @Override
      public List<User> listSeminarGroupMemberByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
            return null;
      }
//
//
//
//
      @Override
      public BigInteger insertSeminarGroupMemberByGroupId(BigInteger groupId, SeminarGroupMember seminarGroupMember) {
            return null;
      }

//
//
//
      @Override
      public SeminarGroup getSeminarGroupById(BigInteger seminarId, BigInteger userId) throws IllegalArgumentException, GroupNotFoundException {
            BigInteger group_id;
            if((group_id = seminarGroupMapper.getSeminarGroupIdBySeminarIdAndUserId(seminarId,userId))==null) {
                  throw new GroupNotFoundException("未找到小组");
            }
            return  seminarGroupMapper.getSeminarGroupByGroupId(group_id);
      }
//
//
//




      /**
       *
       * @param
       * @return
       * @author zhouwei
       * @date 19:49 2017/12/23 0023
       */

      /**
       * 成为组长.
       * <p>同学按小组id和自身id成为组长<br>
       * @param groupId 小组id
       * @param userId  学生id
       * @exception IllegalArgumentException 信息不合法，id格式错误
       * @exception GroupNotFoundException 未找到小组
       * @exception UserNotFoundException 不存在该学生
       * @exception InvalidOperationException 已经有组长了
       */
      @Override
      public void assignLeaderById(BigInteger groupId, BigInteger userId) throws IllegalArgumentException, UserNotFoundException, GroupNotFoundException, InvalidOperationException {
            if(seminarGroupMapper.getSeminarGroupByGroupId(groupId)==null)
            {
                  throw new GroupNotFoundException("未找到该课程");
            }
            if(seminarGroupMapper.getUserIdByUserId(userId)==null)
            {
                  throw new UserNotFoundException("未找到该学生");
            }
            if(seminarGroupMapper.getSeminarGroupLeaderByGroupId(groupId)!=null)
            {
                  throw new InvalidOperationException("已经有组长了");
            }
            seminarGroupMapper.assignLeaderById(groupId,userId);
      }
      @Override
      public void automaticallyGrouping(BigInteger seminarId, BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException, SeminarNotFoundException {
      }
      /**
       * 删除讨论课小组.
       * <p>按照id删除讨论课小组<br>
       * @author YeHongjie
       * @param seminarGroupId 讨论课小组的id
       * @see SeminarGroupService #deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId)
       * @exception IllegalArgumentException 信息不合法，id格式错误
       */
      @Override
      public void deleteSeminarGroupByGroupId(BigInteger seminarGroupId) throws IllegalArgumentException {
            seminarGroupMapper.deleteSeminarGroupByGroupId(seminarGroupId);
      }
      /**
       * 按seminarId删除讨论课小组信息.
       * <p>根据seminarId获得SeminarGroup，然后根据SeminarGroupId删除SeminarGroupMember信息，最后再删除SeminarGroup信息<br>
       * @author zhouzhongjun
       * @param seminarId 讨论课Id
       * @see SeminarGroupService #listSeminarGroupBySeminarId(BigInteger seminarId)
       * @see SeminarGroupService #deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId)
       * @exception IllegalArgumentException 信息不合法，id格式错误
       */
      @Override
      public void deleteSeminarGroupBySeminarId(BigInteger seminarId) throws IllegalArgumentException {
            seminarGroupMapper.deleteSeminarGroupBySeminarId(seminarId);
      }
      /**
       * 按seminarGroupId删除SeminarGroupMember信息.
       * <p>按seminarGroupId删除SeminarGroupMember信息<br>
       * @author zhouzhongjun
       * @param seminarGroupId 讨论课小组Id
       */
      @Override
      public void deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId) {
            seminarGroupMapper.deleteSeminarGroupMemberBySeminarGroupId(seminarGroupId);
      }
      /**
       * 查询讨论课小组.
       * <p>按照id查询某一讨论课小组的信息（包括成员）<br>
       * @author YeHongjie
       * @param groupId 小组的id
       * @return seminarGroup 讨论课小组对象，若未找到相关小组返回空(null)
       * @see SeminarGroupService #listSeminarGroupMemberByGroupId(BigInteger groupId)
       * @exception IllegalArgumentException (信息不合法，id格式错误)
       * @exception GroupNotFoundException (未找到小组)
       */
      @Override
      public SeminarGroup getSeminarGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
            if(seminarGroupMapper.getSeminarGroupByGroupId(groupId)==null) {
                  throw new GroupNotFoundException("未找到小组");
            }
            return seminarGroupMapper.getSeminarGroupByGroupId(groupId);
      }

      /**
       * 获取学生所在讨论课队长.
       * <p>按照用户id和讨论课id获取学生所在讨论课小组队长<br>
       * @author YeHongjie
       * @param userId 用户的id
       * @param seminarId 讨论课id
       * @return BigInteger 讨论课小组的队长id，若未找到相关小组队长返回空(null)
       * @see SeminarGroupService #getSeminarGroupById(BigInteger userId, BigInteger seminarId)
       * @see SeminarGroupService #getSeminarGroupLeaderByGroupId(BigInteger groupId)
       * @exception IllegalArgumentException 信息不合法，id格式错误
       */

      @Override
      public BigInteger getSeminarGroupLeaderById(BigInteger userId, BigInteger seminarId) throws IllegalArgumentException {
            BigInteger groupId ;
            groupId=seminarGroupMapper.getSeminarGroupIdBySeminarIdAndUserId(seminarId,userId);
            return seminarGroupMapper.getSeminarGroupLeaderByGroupId(groupId);
      }
      /**
       * 查询讨论课小组队长id.
       * <p>按照讨论课小组id查询该小组的队长id<br>
       * @author YeHongjie
       * @param groupId 要查询的讨论课小组id
       * @return leaderId 讨论课小组队长id
       * @exception IllegalArgumentException 信息不合法，id格式错误
       * @exception GroupNotFoundException 未找到小组
       */
      @Override
      public BigInteger getSeminarGroupLeaderByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
            if(seminarGroupMapper.getSeminarGroupByGroupId(groupId)==null) {
                  throw new GroupNotFoundException("未找到小组");
            }
            return seminarGroupMapper.getSeminarGroupByGroupId(groupId).getLeader().getId();
      }
      /**
       * 创建讨论课小组.
       * <p>在指定讨论课下创建讨论课小组<br>
       * @author YeHongjie
       * @param seminarId 讨论课的id
       * @param seminarGroup 小组信息
       * @see SeminarGroupService #insertSeminarGroupMemberByGroupId(BigInteger groupId,SeminarGroupMember SeminarGroupMember)
       * @return BigInteger 若创建成功返回该小组的id，失败则返回-1
       * @exception IllegalArgumentException 信息不合法，id格式错误
       */
      @Override
      public BigInteger insertSeminarGroupBySeminarId(BigInteger seminarId, SeminarGroup seminarGroup) throws IllegalArgumentException {
            Seminar seminar = new Seminar();
            seminar.setId(seminarId);
            seminarGroup.setSeminar(seminar);
            seminarGroupMapper.insertSeminarGroupBySeminarId(seminarGroup);
            return seminarGroup.getId();
      }
      /**
       * 按seminarId获取SeminarGroup.
       * <p>按seminarId获取SeminarGroup<br>
       * @author zhouzhongjun
       * @param seminarId 课程Id
       * @return 讨论课小组列表
       * @exception IllegalArgumentException 信息不合法，id格式错误
       * @exception SeminarNotFoundException 未找到讨论课
       */
      @Override
      public List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId) throws IllegalArgumentException, SeminarNotFoundException {
            if(seminarGroupMapper.getSeminarIdBySeminarId(seminarId)==null)
            {
                  throw new SeminarNotFoundException("未找到讨论课");
            }
            return seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
      }

      /*chenjie*/

      /**
       *将学生加入讨论课小组.
       *
       * @param userId 用户Id
       * @param groupId 小组Id
       * @author YellowPure
       * @date 20:43 2017/12/22
       */
      @Override
      public void insertSeminarGroupMemberById(BigInteger userId, BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException, UserNotFoundException, InvalidOperationException {
            if(seminarGroupMapper.getSeminarGroupById(groupId)==null){
                  throw new GroupNotFoundException("未找到小组");
            }
            if(seminarGroupMapper.getUserById(userId)==null){
                  throw new UserNotFoundException("不存在该学生");
            }
            if(seminarGroupMapper.getSeminarGroupMemberByStudentIdAndSeminarGroupId(userId,groupId)!=null){
                  throw new InvalidOperationException("待添加学生已经在小组里了");
            }
            seminarGroupMapper.insertSeminarGroupMemberById(userId,groupId);
      }




      /**
       *获取某学生所有的讨论课小组.
       *
       * @param userId 用户id
       * @return List<SeminarGroup>
       * @author YellowPure
       * @date 22:41 2017/12/22
       */
      @Override
      public List<SeminarGroup> listSeminarGroupIdByStudentId(BigInteger userId) throws IllegalArgumentException {
            List<SeminarGroupMember> seminarGroupMembers = seminarGroupMapper.listSeminarGroupIdByStudentId(userId);
            List<SeminarGroup> seminarGroups = new ArrayList<>();
            for (SeminarGroupMember seminarGroupMember:seminarGroupMembers){
                  seminarGroups.add(seminarGroupMember.getSeminarGroup());
            }
            return seminarGroups;
      }



      /**
       *按seminarId获取SeminarGroup.
       *
       * @param seminarId 讨论课id
       * @return List<SeminarGroup>
       * @author YellowPure
       * @date 22:50 2017/12/22
       */
//      @Override
//      public List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId) throws IllegalArgumentException, SeminarNotFoundException {
//            if(seminarGroupMapper.getSeminarById(seminarId)==null){
//                  throw new SeminarNotFoundException("未找到讨论课");
//            }
//            return seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
//      }

      /**
       *根据话题Id获得选择该话题的所有小组的信息.
       *
       * @param topicId 话题Id
       * @return List<SeminarGroup>
       * @author YellowPure
       * @date 22:51 2017/12/22
       */
      @Override
      public List<SeminarGroup> listGroupByTopicId(BigInteger topicId) throws IllegalArgumentException, TopicNotFoundException {
            if(seminarGroupMapper.getTopicById(topicId)==null){
                  throw new TopicNotFoundException("未找到话题");
            }
            List<BigInteger> seminarGroupId = new ArrayList<>();
            seminarGroupId = seminarGroupMapper.listGroupIdByTopicId(topicId);
            SeminarGroup seminarGroup = new SeminarGroup();
            List<SeminarGroup> seminarGroups = new ArrayList<>();
            for (int i = 0;i<seminarGroupId.size();i++){
                  seminarGroup = seminarGroupMapper.getSeminarGroupByGroupId(seminarGroupId.get(i));
            seminarGroups.add(seminarGroup);
      }
            return seminarGroups;
      }

      /**
       * 小组按id选择话题.
       *
       * @param groupId 讨论组id
       * @param topicId 话题Id
       * @author YellowPure
       * @date 23:00 2017/12/22
       */
      @Override
      public void insertTopicByGroupId(BigInteger groupId, BigInteger topicId) throws IllegalArgumentException, GroupNotFoundException {
            if(seminarGroupMapper.getSeminarGroupById(groupId)==null){
                  throw new GroupNotFoundException("该小组不存在时抛出");
            }
            seminarGroupMapper.insertSeminarGroupTopicByTopicIdAndSeminarGroupId(topicId,groupId);
      }


      /**
       *组长辞职.
       *
       * @param groupId 讨论组id
       * @param userId 用户Id
       * @author YellowPure
       * @date 23:02 2017/12/22
       */
      @Override
      public void resignLeaderById(BigInteger groupId, BigInteger userId) throws IllegalArgumentException, GroupNotFoundException {
            if (seminarGroupMapper.getSeminarGroupById(groupId)==null){
                  throw new GroupNotFoundException("GroupNotFoundException");
            }
            seminarGroupMapper.resignLeaderById(groupId,userId);
      }
}
