package xmu.crms.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xmu.crms.entity.ClassInfo;
import xmu.crms.entity.FixGroup;
import xmu.crms.entity.FixGroupMember;
import xmu.crms.entity.User;
import xmu.crms.exception.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * @author zhangchubing
 */

// done 20171225 17.59
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class FixGroupServiceImplTest {

    @Autowired
    private FixGroupService fixGroupService;

    @Test
    public void insertFixGroupByClassId() throws IllegalArgumentException, UserNotFoundException {
//        BigInteger integer=fixGroupService.insertFixGroupByClassId(new BigInteger("11"),new BigInteger("27"));
//        Assert.assertNotNull(integer);
    }

    @Test
    public void deleteFixGroupMemberByFixGroupId() throws IllegalArgumentException, FixGroupNotFoundException {
        fixGroupService.deleteFixGroupMemberByFixGroupId(new BigInteger("2"));
    }

    @Test
    public void deleteFixGroupUserById() throws IllegalArgumentException, FixGroupNotFoundException, UserNotFoundException{
        fixGroupService.deleteFixGroupUserById(new BigInteger("3"),new BigInteger("13"));
    }

    @Test
    public void listFixGroupMemberByGroupId() throws IllegalArgumentException, FixGroupNotFoundException{
        List<User> users=new ArrayList<User>();
        users=fixGroupService.listFixGroupMemberByGroupId(new BigInteger("1"));
        Assert.assertNotEquals(new Integer("0"),new Integer(users.size()));
        for(int i=0;i<users.size();i++)
            System.out.println(users.get(i).getName());
    }

    @Test
    public void listFixGroupByClassId() throws IllegalArgumentException, ClazzNotFoundException {
        List<FixGroup> fixGroups=new ArrayList<FixGroup>();
        fixGroups=fixGroupService.listFixGroupByClassId(new BigInteger("1"));
        Assert.assertNotEquals(new Integer("0"),new Integer(fixGroups.size()));
        for(int i=0;i<fixGroups.size();i++)
            System.out.println(fixGroups.get(i).getLeader().getName());
    }

    @Test
    public void deleteFixGroupByClassId() throws IllegalArgumentException, ClazzNotFoundException{
        fixGroupService.deleteFixGroupByClassId(new BigInteger("3"));
    }

    @Test
    public void deleteFixGroupByGroupId() throws IllegalArgumentException, FixGroupNotFoundException{
        fixGroupService.deleteFixGroupByGroupId(new BigInteger("1"));
    }

    @Test
    public void updateFixGroupByGroupId() throws IllegalArgumentException, FixGroupNotFoundException{
        ClassInfo classInfo=new ClassInfo();
        classInfo.setId(new BigInteger("3"));
        User leader=new User();
        leader.setId(new BigInteger("88"));
        FixGroup fixGroup=new FixGroup();
        fixGroup.setClassInfo(classInfo);
        fixGroup.setLeader(leader);
        fixGroupService.updateFixGroupByGroupId(new BigInteger("5"),fixGroup);
    }

    @Test
    public void listFixGroupByGroupId() throws IllegalArgumentException, FixGroupNotFoundException{
        List<FixGroupMember> members=new ArrayList<FixGroupMember>();
        members=fixGroupService.listFixGroupByGroupId(new BigInteger("1"));
        Assert.assertNotEquals(new Integer("0"),new Integer(members.size()));
        for(int i=0;i<members.size();i++)
            System.out.println(members.get(i).getStudent().getName());
    }

    @Test
    public void insertStudentIntoGroup() {
        try{
            fixGroupService.insertStudentIntoGroup(new BigInteger("89"),new BigInteger("1"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getFixedGroupById(){
        try{
            FixGroup fixGroup=fixGroupService.getFixedGroupById(new BigInteger("3"),new BigInteger("1"));
            System.out.println(fixGroup.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void fixedGroupToSeminarGroup() throws IllegalArgumentException, FixGroupNotFoundException, SeminarNotFoundException {
    }
}