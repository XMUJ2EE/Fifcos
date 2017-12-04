package xmu.crms.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import xmu.crms.entity.Course;
import xmu.crms.entity.Seminar;

import java.util.List;

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
     * @return 课程对象，失败返回False
     */
    Course getCourseById(int courseId);

    /**
     * 修改课程信息
     * @param course 课程对象
     * @return True or False
     */
    Boolean updateCourseById(Course course);

    /**
     * 按照id删除课程
     * @param courseId 课程id
     * @return True or False
     */
    Boolean deleteCourseById(int courseId);

    /**
     * 按照课程id寻找课程的班级列表
     * @param courseId 课程id
     * @return 班级列表
     */
    List<Class> getClassListByCourseId(int courseId);

    /**
     * 在指定id的课程下创建班级
     * @param courseId 课程id
     * @return True or False
     */
    Boolean addClassByCourseId(int courseId);

    /**
     * 按照课程id获取讨论课详情列表
     * @param courseId 课程id
     * @return Seminar列表
     */
    List<Seminar> getSeminarListByCourseId(int courseId);


}
