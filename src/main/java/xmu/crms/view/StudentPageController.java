package xmu.crms.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mads
 */
@Controller
public class StudentPageController {

    /**
     * 个人信息
     * @return
     */
    @RequestMapping("/student/home")
    public String home(){
        return "student/baseinfo";
    }

    /**
     * 更新个人信息
     * @return
     */
    @RequestMapping("/student/update")
    public String update(){
        return "student/form_baseinfo.html";
    }

    /**
     * 绑定账户
     * @return
     */
    @RequestMapping("/student/bind")
    public String bind(){
        return "student/bind";
    }

    /**
     * 课程列表
     * @return
     */
    @RequestMapping("/student/courses")
    public String courses(){
        return "student/courses";
    }

    /**
     * 课程详情
     * @return
     */
    @RequestMapping("/student/course/{id}")
    public String courseDetail(){
        return "student/course";
    }

    /**
     * 固定分组的seminar
     * @return
     */
    @RequestMapping("/student/course/seminar/fixed/{id}")
    public String seminarFixed(){
        return "student/fixed_seminar";
    }

    /**
     * 随机分组的seminar
     * @return
     */
    @RequestMapping("/student/course/seminar/random/{id}")
    public String seminarRandom(){
        return "student/random_seminar";
    }

    /**
     * 课程固定小组
     * @return
     */
    @RequestMapping("/student/course/group")
    public String group(){
        return "student/group";
    }

    /**
     * 更新课程小组表单
     * @return
     */
    @RequestMapping("/student/course/group?action=update")
    public String updateGroup(){
        return "student/form_group";
    }

    /**
     * 固定分组的话题
     * @return
     */
    @RequestMapping("/student/course/seminar/fixed/{id}/topic")
    public String getFixedTopic(){
        return "student/fixed_topic";
    }

    /**
     * 随机分组的话题
     * @return
     */
    @RequestMapping("/student/course/seminar/random/{id}/topic")
    public String getRandomTopic(){
        return "student/random_topic";
    }

    /**
     * 选择课程表单
     * @return
     */
    @RequestMapping("/student/course?action=select")
    public String selectTopic(){
        return "student/form_course";
    }

    /**
     * 分数
     * @return
     */
    @RequestMapping("/student/course/seminar/grade")
    public String grade(){
        return "student/grade";
    }


}
