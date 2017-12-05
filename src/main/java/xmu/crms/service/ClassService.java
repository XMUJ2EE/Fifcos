package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.Class;
import xmu.crms.entity.Group;
import xmu.crms.entity.User;

import java.util.List;

/**
 * Class服务接口
 * @author Xuezhang.Liu
 */

public interface ClassService {

    /**
     * 根据用户id获取用户的班级列表
     * @param userId 用户id
     * @param courseName 课程名称，可选
     * @param courseTeacher 课程老师名字，可选
     * @return 班级列表
     */
    List<Class> getClassListByUserId(int userId, String courseName, String courseTeacher);

    /**
     * 根据班级id获取班级详情
     * @param classId 班级id
     * @return 班级对象详情
     */
    Class getClassById(int classId);

    /**
     * 根据id修改班级信息
     * @param classId 班级id
     * @param myClass 班级对象
     * @return 执行结果
     */
    Boolean updateClassById(int classId, Class myClass);

    /**
     * 根据id删除班级
     * @param classId 班级id
     * @return 执行结果
     */
    Boolean deleteClassById(int classId);

    /**
     * 根据条件获取班级学生列表
     * @param classId 班级id
     * @param numBeginWith 学号开头
     * @param nameBeginWith 姓名开头
     * @return 学生列表
     */
    List<User> getStudentListByClassId(int classId, String numBeginWith, String nameBeginWith);

    /**
     * 把学生加入到班级（建立一条学生班级映射记录）
     * @param classId 班级id
     * @param studentId 学生id
     * @return 执行结果
     */
    Boolean addStudentToClassByClassIdAndStudentId(int classId, int studentId);

    /**
     * 把学生从班级移除（删除一条学生班级映射）
     * @param classId 班级id
     * @param studentId 学生id
     * @return 执行结果
     */
    Boolean deleteStudentFromClassByClassIdAndStudentId(int classId, int studentId);

    /**
     * 查找学生id对应班级id内的小组(固定小组)信息
     * @param classId 班级id
     * @param studentId 学生id
     * @return 小组信息
     */
    Group getGroupByClassId(int classId, int studentId);

    /**
     * 移除班级固定小组的组长
     * @param classId 班级id
     * @param groupId 小组id
     * @return 执行结果
     */
    Boolean deleteLeaderFromGroupByClassId(int classId, int groupId);

    /**
     * 添加班级固定小组的组长
     * @param classId 班级id
     * @param studentId 学生id
     * @param groupId 小组id
     * @return 执行结果
     */
    Boolean addLeaderInGroupByClassId(int classId, int studentId, int groupId);

    /**
     * 班级固定小组添加成员
     * @param classId 班级id
     * @param studentId 学生id
     * @param groupId 小组id
     * @return 执行结果
     */
    Boolean addMemberInGroupByClassId(int classId, int studentId, int groupId);

    /**
     * 从固定小组删除成员
     * @param classId 班级id
     * @param studentId 学生id
     * @param groupId 小组id
     * @return 执行结果
     */
    Boolean deleteMemberFromGroupByClassId(int classId, int studentId, int groupId);


}
