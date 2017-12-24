package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.FixGroupMapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @author zhangchubing
 */
@Component
public class FixGroupDao {

    @Autowired
    private FixGroupMapper fixGroupMapper;

    public Integer insertFixGroupByClassId(BigInteger classId, BigInteger userId) throws IllegalArgumentException {
         return fixGroupMapper.insertFixGroupByClassId(classId,userId);
    }

    public void deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if(listFixGroupByGroupId(fixGroupId).size()!=0) {
            fixGroupMapper.deleteFixGroupMemberByFixGroupId(fixGroupId);
        }
    }

    public void deleteFixGroupUserById(BigInteger fixGroupId, BigInteger userId) throws IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException{
        if(listFixGroupByGroupId(fixGroupId).size()!=0){
            Integer integer=fixGroupMapper.deleteFixGroupUserById(fixGroupId,userId);
            if(integer==0) {
                throw new UserNotFoundException("找不到想删除的组员");
            }
        }

    }

    public List<User> listFixGroupMemberByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if(listFixGroupByGroupId(groupId).size()!=0){
            return fixGroupMapper.listFixGroupMemberByGroupId(groupId);
        }
        return null;
    }

    public List<FixGroup> listFixGroupByClassId(BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException {
        List<FixGroup> list=fixGroupMapper.listFixGroupByClassId(classId);
        if(list==null) {
            throw new ClazzNotFoundException("该班级没有固定分组");
        }
        return list;
    }

    public void deleteFixGroupByClassId(BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException {
        //删掉小组成员表
        List<FixGroup> listFixGroup=listFixGroupByClassId(classId);
        if(listFixGroup==null) {
            throw new ClazzNotFoundException("该班级没有固定分组");
        }
        for(int i=0;i<listFixGroup.size();i++) {
            fixGroupMapper.deleteFixGroupMemberByFixGroupId(listFixGroup.get(i).getId());
        }
        //删掉小组表
        fixGroupMapper.deleteFixGroupByClassId(classId);
    }

    public void deleteFixGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if(getFixGroupByFixGroupId(groupId)){
            fixGroupMapper.deleteFixGroupByGroupId(groupId);
            fixGroupMapper.deleteFixGroupMemberByFixGroupId(groupId);
        }
    }

    public void updateFixGroupByGroupId(BigInteger groupId, FixGroup fixGroupBO) throws IllegalArgumentException, FixGroupNotFoundException {
        Integer integer=fixGroupMapper.updateFixGroupByGroupId(groupId,fixGroupBO);
        if(integer==0) {
            throw new FixGroupNotFoundException("没有找到要更新的固定小组");
        }
    }

    public List<FixGroupMember> listFixGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        List<FixGroupMember>list=fixGroupMapper.listFixGroupByGroupId(groupId);
        if(list==null) {
            throw new FixGroupNotFoundException("没有找到该id对应的固定小组");
        }
        return list;
    }

    public Integer insertStudentIntoGroup(BigInteger userId, BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException,
            InvalidOperationException {
        if(getFixGroupByFixGroupId(groupId)){
            List<User> users=fixGroupMapper.listFixGroupMemberByGroupId(groupId);
            for(int i=0;i<users.size();i++) {
                if (users.get(i).getId().equals(userId)) {
                    throw new InvalidOperationException("要添加的学生已经在固定分组里了");
                }
            }
            return fixGroupMapper.insertStudentIntoGroup(userId,groupId);
        }
        return null;
    }

    public FixGroup getFixedGroupById(BigInteger userId, BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException {
        FixGroup fixGroup=fixGroupMapper.getFixedGroupById(userId,classId);
        if(fixGroup==null) {
            throw new ClazzNotFoundException("没有找到该学生在该班级的固定小组");
        }
        return fixGroup;
    }

    public void fixedGroupToSeminarGroup(BigInteger semianrId, BigInteger fixedGroupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if(getFixGroupByFixGroupId(fixedGroupId)){
            BigInteger seminarGroupId=BigInteger.valueOf(fixGroupMapper.fixedGroupToSeminarGroup(semianrId,fixedGroupId));
            fixGroupMapper.fixedGroupMemberToSeminarGroupMember(seminarGroupId,fixedGroupId);
        }
    }

    /**借助的方法 通过id获得固定小组*/
    public Boolean getFixGroupByFixGroupId(BigInteger fixGroupId) throws IllegalArgumentException, FixGroupNotFoundException{
        FixGroup fixGroup=fixGroupMapper.getFixGroupByFixGroupId(fixGroupId);
        if(fixGroup==null) {
            throw new FixGroupNotFoundException("没有找到该id对应的固定小组");
        }
        return true;
    }
}
