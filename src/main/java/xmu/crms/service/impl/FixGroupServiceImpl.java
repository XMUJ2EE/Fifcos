package xmu.crms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xmu.crms.dao.FixGroupDao;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.service.ClassService;
import xmu.crms.service.FixGroupService;
import xmu.crms.service.SeminarService;
import xmu.crms.service.UserService;

import java.math.BigInteger;
import java.util.List;


/**
 * @author zhangchubing
 */
@Service
public class FixGroupServiceImpl implements FixGroupService {
    @Autowired
    private FixGroupDao fixGroupDao;
    @Autowired
    private UserService userService;
    @Autowired
    private SeminarService seminarService;

    @Autowired
    private ClassService classService;

    @Override
    public BigInteger insertFixGroupByClassId(FixGroup fixGroup) throws IllegalArgumentException, UserNotFoundException {
        User user=userService.getUserByUserId(fixGroup.getLeader().getId());
        if(user==null) {
            throw new UserNotFoundException("找不到该id对应的学生");
        }
        fixGroupDao.insertFixGroupByClassId(fixGroup);
        return fixGroup.getId();
    }

    @Override
    public void deleteFixGroupMemberByFixGroupId(BigInteger fixGroupId) throws IllegalArgumentException, FixGroupNotFoundException {
        fixGroupDao.deleteFixGroupMemberByFixGroupId(fixGroupId);
    }

    @Override
    public void deleteFixGroupUserById(BigInteger fixGroupId, BigInteger userId) throws IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException {
        fixGroupDao.deleteFixGroupUserById(fixGroupId,userId);
    }

    @Override
    public List<User> listFixGroupMemberByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        return fixGroupDao.listFixGroupMemberByGroupId(groupId);
    }

    @Override
    public List<FixGroup> listFixGroupByClassId(BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException {
        return fixGroupDao.listFixGroupByClassId(classId);
    }

    @Override
    public void deleteFixGroupByClassId(BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException {
        fixGroupDao.deleteFixGroupByClassId(classId);
    }

    @Override
    public void deleteFixGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        fixGroupDao.deleteFixGroupByGroupId(groupId);
    }

    @Override
    public void updateFixGroupByGroupId(BigInteger groupId, FixGroup fixGroupBO) throws IllegalArgumentException, FixGroupNotFoundException {
        fixGroupDao.updateFixGroupByGroupId(groupId,fixGroupBO);
    }

    @Override
    public List<FixGroupMember> listFixGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException {
        return fixGroupDao.listFixGroupByGroupId(groupId);
    }

    @Override
    public BigInteger insertStudentIntoGroup(BigInteger userId, BigInteger groupId) throws IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException, ClazzNotFoundException,InvalidOperationException {
        User user=userService.getUserByUserId(userId);
        if(user==null) {
            throw new UserNotFoundException("找不到该id对应的学生");
        }
        return BigInteger.valueOf(fixGroupDao.insertStudentIntoGroup(userId,groupId));
    }

    @Override
    public FixGroup getFixedGroupById(BigInteger userId, BigInteger classId) throws IllegalArgumentException, ClazzNotFoundException, UserNotFoundException {
        User user=userService.getUserByUserId(userId);
        if(user==null){
            throw new UserNotFoundException("找不到该id对应的学生");
        }
        ClassInfo classInfo = classService.getClassByClassId(classId);
        if(classInfo == null){
            throw new ClazzNotFoundException("未找到固定小组的班级");
        }
        return fixGroupDao.getFixedGroupById(userId,classId);
    }

    @Override
    public void fixedGroupToSeminarGroup(BigInteger seminarId, BigInteger fixedGroupId) throws IllegalArgumentException, FixGroupNotFoundException, SeminarNotFoundException {
        Seminar seminar=seminarService.getSeminarBySeminarId(seminarId);
        if(seminar!=null){
            fixGroupDao.fixedGroupToSeminarGroup(seminarId,fixedGroupId);
        }
    }
}
