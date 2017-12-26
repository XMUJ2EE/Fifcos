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

    public void deleteFixGroupByClassId(BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException{
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
        if(getFixGroupByFixGroupId(groupId)!=null){
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

    public Integer insertStudentIntoGroup(BigInteger userId, BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException,ClazzNotFoundException, InvalidOperationException {
        if(getFixGroupByFixGroupId(groupId)!=null){
            ClassInfo classInfo = getFixGroupByFixGroupId(groupId).getClassInfo();
            if (classInfo == null) {
                throw new ClazzNotFoundException("未找到Group id 为："+groupId.toString()+"的班级！");
            }
            BigInteger classId = classInfo.getId();
            //找到该班级下所有固定分组
            List<FixGroup> fixGroupList=listFixGroupByClassId(classId);
            //搜索每个固定分组下的成员
            if(fixGroupList == null){
                throw new FixGroupNotFoundException("未找到班级id："+classId.toString()+"的固定小组");
            }
            for(int i=0;i<fixGroupList.size();i++){
                List<FixGroupMember> members=listFixGroupByGroupId(fixGroupList.get(i).getId());
                for(int j=0;j<members.size();j++){
                    if(members.get(j).getId().equals(userId)){
                        throw new InvalidOperationException("要添加的学生已经在固定分组里了");
                        }
                }
            }
            return fixGroupMapper.insertStudentIntoGroup(userId,groupId);
        }else{
            throw new FixGroupNotFoundException("没找到固定小组id：" + groupId.toString());
        }
    }

    public FixGroup getFixedGroupById(BigInteger userId, BigInteger classId) throws IllegalArgumentException{
        FixGroup fixGroup=fixGroupMapper.getFixedGroupById(userId,classId);
        if(fixGroup==null) {
            fixGroupMapper.insertFixGroupByClassId(classId, userId);
        }
        return fixGroup;
    }

    public void fixedGroupToSeminarGroup(BigInteger semianrId, BigInteger fixedGroupId) throws IllegalArgumentException, FixGroupNotFoundException {
        if(getFixGroupByFixGroupId(fixedGroupId)!=null){
            BigInteger seminarGroupId=BigInteger.valueOf(fixGroupMapper.fixedGroupToSeminarGroup(semianrId,fixedGroupId));
            fixGroupMapper.fixedGroupMemberToSeminarGroupMember(seminarGroupId,fixedGroupId);
        }
    }

    /**借助的方法 通过id获得固定小组*/
    public FixGroup getFixGroupByFixGroupId(BigInteger fixGroupId) throws IllegalArgumentException, FixGroupNotFoundException{
        FixGroup fixGroup=fixGroupMapper.getFixGroupByFixGroupId(fixGroupId);
        if(fixGroup==null) {
            throw new FixGroupNotFoundException("没有找到该id对应的固定小组");
        }
        return fixGroup;
    }
}
