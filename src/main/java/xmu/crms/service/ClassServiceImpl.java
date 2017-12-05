package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.Class;
import xmu.crms.entity.Group;
import xmu.crms.entity.User;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Override
    public List<Class> getClassListByUserId(int userId, String courseName, String courseTeacher) {
        return null;
    }

    @Override
    public Class getClassById(int classId) {
        return null;
    }

    @Override
    public Boolean updateClassById(int classId, Class myClass) {
        return null;
    }

    @Override
    public Boolean deleteClassById(int classId) {
        return null;
    }

    @Override
    public List<User> getStudentListByClassId(int classId, String numBeginWith, String nameBeginWith) {
        return null;
    }

    @Override
    public Boolean addStudentToClassByClassIdAndStudentId(int classId, int studentId) {
        return null;
    }

    @Override
    public Boolean deleteStudentFromClassByClassIdAndStudentId(int classId, int studentId) {
        return null;
    }

    @Override
    public Group getGroupByClassId(int classId, int studentId) {
        return null;
    }

    @Override
    public Boolean deleteLeaderFromGroupByClassId(int classId, int groupId) {
        return null;
    }

    @Override
    public Boolean addLeaderInGroupByClassId(int classId, int studentId, int groupId) {
        return null;
    }

    @Override
    public Boolean addMemberInGroupByClassId(int classId, int studentId, int groupId) {
        return null;
    }

    @Override
    public Boolean deleteMemberFromGroupByClassId(int classId, int studentId, int groupId) {
        return null;
    }
}
