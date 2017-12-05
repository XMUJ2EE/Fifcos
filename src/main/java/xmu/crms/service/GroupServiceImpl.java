package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.*;

@Service
public class GroupServiceImpl implements GroupService {
    @Override
    public Group getGroupById(int id, boolean embedTopics, boolean embedGrade) {

        //School school = new School(32,"厦门大学","福建省","厦市门");
       // User leader = new User(8888, Type.STUDENT, "", "张三", "", "", Gender.MALE, school, "", "", "", "");
       // Group group = new Group(28, "", leader, "5", "", "", "");

        return null;
    }

    @Override
    public Boolean resignGroupLeader(int groupid, int studentId) {
        return null;
    }

    @Override
    public Boolean assignGroupLeader(int groupid, int studentId) {
        return null;
    }

    @Override
    public Boolean addGroupMember(int groupid, int studentId) {
        return null;
    }

    @Override
    public Boolean removeGroupMember(int groupid, int studentId) {
        return null;
    }

    @Override
    public Boolean selectTopic(int id) {
        return null;
    }

    @Override
    public Boolean deselectTopic(int id) {
        return null;
    }

    @Override
    public int getGradeByGroupId(int id) {
        return 0;
    }

    @Override
    public int finalGradeByGroupId(int grade) {
        return 0;
    }
}
