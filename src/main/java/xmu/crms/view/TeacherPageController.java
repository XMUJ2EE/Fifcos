package xmu.crms.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author wang
 *
 */


@Controller
public class TeacherPageController {

    /**
     * 个人信息
     * @return
     */
    @RequestMapping("/teacher/home")
    public String home() {
        return "teacher/baseinfo";
    }

    /**
     * 更新个人信息
     * @return
     */
    @RequestMapping("/teacher/update")
    public String update() {
        return "teacher/baseinfo_update";
    }

    /**
     * 创建学校
     * @return
     */
    @RequestMapping("/teacher/school/create")
    public String createSchool() {
        return "teacher/school_create";
    }

    /**
     * 教师账号绑定
     * @return
     */
    @RequestMapping("/teacher/bind")
    public String bind() {
        return "/teacher/bind";
    }

    /**
     * 查看自己的所有课程
     * @return
     */
    @RequestMapping("/teacher/courses")
    public String courses() {
        return "teacher/courses";
    }

    /**
     * 创建课程
     * @return
     */
    @RequestMapping("/teacher/courses/create")
    public String createCourse() {
        return "teacher/course_create";
    }

    /**
     * 查看课程详细信息
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}")
    public String courseDetail(@PathVariable String courseId) {
        return "teacher/course";
    }

    /**
     * 更新课程信息
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/update")
    public String updateCourse(@PathVariable String courseId) {
        return "teacher/course_update";
    }

    /**
     * 查看课程的班级
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/class/{classId}")
    public String myClass(@PathVariable String courseId, @PathVariable String classId) {
        return "teacher/class";
    }

    /**
     * 修改班级信息
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/class/{classId}/update")
    public String updateClass(@PathVariable String courseId, @PathVariable String classId) {
        return "teacher/class_update";
    }

    /**
     * 创建班级
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/class/create")
    public String createClass(@PathVariable String courseId) {
        return "teacher/class_create";
    }

    /**
     * 查看该课程的讨论课
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/seminar/{seminarId}")
    public String seminar(@PathVariable String courseId, @PathVariable String seminarId) {
        return "teacher/seminar";
    }

    /**
     * 更新讨论课信息
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/seminar/{seminarId}/update")
    public String updateSminar(@PathVariable String courseId, @PathVariable String seminarId) {
        return "teacher/seminar_update";
    }

    /**
     * 创建讨论课
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/seminar/create")
    public String createSeminar(@PathVariable String courseId) {
        return "teacher/seminar_create";
    }

    /**
     * 获取讨论课所有小组的分数
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/seminar/{seminarId}/score")
    public String getScore(@PathVariable String courseId, @PathVariable String seminarId) {
        return "teacher/score";
    }

    /**
     * 创建话题
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/seminar/{seminarId}/topic/create")
    public String createTopic(@PathVariable String courseId, @PathVariable String seminarId) {
        return "teacher/topic_create";
    }

    /**
     * 查看该讨论课的话题
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/seminar/{seminarId}/topic/{topicId}")
    public String topic(@PathVariable String courseId, @PathVariable String seminarId, @PathVariable String topicId) {
        return "teacher/topic";
    }

    /**
     * 更新话题
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/seminar/{seminarId}/topic/{topicId}/update")
    public String updateTopic(@PathVariable String courseId, @PathVariable String seminarId, @PathVariable String topicId) {
        return "teacher/topic_update";
    }

    /**
     * 查看报告以及打分
     * @return
     */
    @RequestMapping("/teacher/course/{courseId}/seminar/{seminarId}/score/score")
    public String scoreReport(@PathVariable String courseId, @PathVariable String seminarId) {
        return "teacher/report_socre";
    }

    @RequestMapping("/teacher/course/{courseId}/seminar/{seminarId}/status")
    public String topicStatus(@PathVariable String courseId, @PathVariable String seminarId) {
        return "teacher/topic_view";
    }

}
