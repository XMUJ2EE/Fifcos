package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.Grade;
import xmu.crms.entity.PresentationGrade;
import xmu.crms.view.VO.GroupDetailsVO;
import xmu.crms.view.VO.TopicIdNameVO;
import xmu.crms.view.VO.UserIdNameVO;

@Service
public class GroupServiceImpl implements GroupService {
    @Override
    public GroupDetailsVO getGroupById(int id, boolean embedTopics, boolean embedGrade) {

        //School school = new School(32,"厦门大学","福建省","厦市门");
        UserIdNameVO leader = new UserIdNameVO(8888, "张三");
        UserIdNameVO member1 = new UserIdNameVO(5324, "李四");
        UserIdNameVO member2 = new UserIdNameVO(5678, "王五");

        UserIdNameVO members[] = {member1, member2};

        TopicIdNameVO topicIdNameVO = new TopicIdNameVO(257, "领域模型与模块");

        GroupDetailsVO groupDetailsVO = new GroupDetailsVO(28, leader, members, topicIdNameVO, "");


        return groupDetailsVO;
    }

    @Override
    public Boolean resignGroupLeader(int groupId, int studentId) {
        return true;
    }

    @Override
    public Boolean assignGroupLeader(int groupId, int studentId) {
        return true;
    }

    @Override
    public Boolean addGroupMember(int groupId, int studentId) {
        return true;
    }

    @Override
    public Boolean removeGroupMember(int groupId, int studentId) {
        return true;
    }

    @Override
    public String selectTopic(int groupId, int topicId) {

        return "\"url\": \"/group/27/topic/23\"";
    }

    @Override
    public Boolean deselectTopic(int groupId, int topicId) {
        return true;
    }

    @Override
    public Grade getGradeByGroupId(int groupId) {
        PresentationGrade presentationGrade1 = new PresentationGrade(257, 4);
        PresentationGrade presentationGrade2 = new PresentationGrade(258, 5);

        PresentationGrade[] presentationGrades = {presentationGrade1, presentationGrade2};

        Grade grade = new Grade(presentationGrades, 3, 4);

        return grade;
    }

    @Override
    public Boolean finalGradeByGroupId(int groupId, int grade) {
        return true;
    }

    @Override
    public Boolean submitGradeByGroupId(int groupId, int studentId) {

        return true;
    }
}
