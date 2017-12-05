package xmu.crms.service;

import xmu.crms.entity.Class;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;
import xmu.crms.view.vo.SeminarGradeDetail;

import java.util.List;
import java.util.Map;

/**
 * Course的服务接口
 * @author Xuezhang.Liu
 *
 */
public interface CourseService {

    /**
     * 获取与当前用户相关联的课程列表
     * @param userId 当前用户的id
     * @return Course 列表
     */
    List<Course> getCourseListById(int userId);

    /**
     * 创建课程，将课程对象映射到数据库
     * @param userId 创建课程的用户id
     * @param course 课程对象
     * @return 新创建课程的id
     */
    int addCourse(int userId, Course course);

    /**
     * 按照课程id查找课程
     * @param courseId 课程id
     * @return 课程对象，失败返回0
     */
    Course getCourseById(int courseId);

    /**
     * 修改课程信息
     * @param courseId Course 的id
     * @param course 课程对象
     * @return True or False
     */
    Boolean updateCourseById(int courseId, Course course);

    /**
     * 按照id删除课程
     * @param userId 当前用户的id
     * @param courseId 课程id
     * @return True or False
     */
    Boolean deleteCourseById(int userId, int courseId);

    /**
     * 按照课程id寻找课程的班级列表
     * @param courseId 课程id
     * @return 班级列表
     */
    List<Map<String, Object>> getClassListByCourseId(int courseId);

    /**
     * 在指定id的课程下创建班级
     * @param courseId 课程id
     * @param myClass class对象
     * @return class 的id和name
     */
    int addClassByCourseId(int courseId, Class myClass);

    /**
     * 按照课程id获取讨论课详情列表
     * @param courseId 课程id
     * @return Seminar列表
     */
    List<Seminar> getSeminarListByCourseId(int courseId);

    /**
     * 在指定课程下创建seminar
     * @param courseId 课程id
     * @param seminar 讨论课对象
     * @return Seminar id
     */
    int addSeminarByCourseId(int courseId, Seminar seminar);

    /**
     * 获取当前正在进行的讨论课
     * @param courseId 课程id
     * @return 当前正在进行的讨论课详情或者null
     */
    Seminar getCurrentSeminarByCourseId(int courseId);

    /**
     * 获取课程下所有的讨论课成绩
     * @param courseId 课程id
     * @param userId 用户id
     * @return 课程下所有小组的讨论课成绩列表
     */
    List<SeminarGradeDetail> getAllSeminarGradeByCourseId(int courseId, int userId);

}
