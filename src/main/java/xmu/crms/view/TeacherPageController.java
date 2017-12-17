package xmu.crms.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("/teacher/school?action=create")
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
    @RequestMapping("/teacher/courses?action=create")
    public String createCourse() {
        return "teacher/course_create";
    }

    /**
     * 查看课程详细信息
     * @return
     */
    @RequestMapping("/teacher/course/{id}")
    public String courseDetail() {
        return "teacher/course";
    }

    /**
     * 更新课程信息
     * @return
     */
    @RequestMapping("/teacher/course/{id}?action=update")
    public String updateCourse() {
        return "teacher/course_update";
    }

    /**
     * 查看课程的班级
     * @return
     */
    @RequestMapping("/teacher/course/{id}/class/{id}")
    public String myClass() {
        return "teacher/class";
    }

    /**
     * 创建班级
     * @return
     */
    @RequestMapping("/teacher/course/{id}/class?action=create")
    public String createClass() {
        return "teacher/class_create";
    }

    /**
     * 查看该课程的讨论课
     * @return
     */
    @RequestMapping("/teacher/course/{id}/seminar/{id}")
    public String seminar() {
        return "teacher/seminar";
    }

    /**
     * 更新讨论课信息
     * @return
     */
    @RequestMapping("/teacher/course/{id}/seminar/{id}?action=update")
    public String updateSminar() {
        return "teacher/seminar_update";
    }

    /**
     * 创建讨论课
     * @return
     */
    @RequestMapping("/teacher/course/{id}/seminar?action=create")
    public String createSeminar() {
        return "teacher/seminar_create";
    }

    /**
     * 获取讨论课所有小组的分数
     * @return
     */
    @RequestMapping("/teacher/course/{id}/seminar/{id}/score")
    public String getScore() {
        return "teacher/score";
    }

    /**
     * 创建话题
     * @return
     */
    @RequestMapping("/teacher/course/{id}/seminar/{id}/topic?action=create")
    public String createTopic() {
        return "teacher/topic_create";
    }

    /**
     * 查看该讨论课的话题
     * @return
     */
    @RequestMapping("/teacher/course/{id}/seminar/{id}/topic/{id}")
    public String topic() {
        return "teacher/topic";
    }

    /**
     * 更新话题
     * @return
     */
    @RequestMapping("/teacher/course/{id}/seminar/{id}/topic/{id}?action=update")
    public String updateTopic() {
        return "teacher/topic_update";
    }

    /**
     * 查看报告以及打分
     * @return
     */
    @RequestMapping("/teacher/course/{id}/seminar/{id}/score?action=score")
    public String scoreReport() {
        return "teacher/report_socre";
    }


}
