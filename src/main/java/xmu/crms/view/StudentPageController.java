package xmu.crms.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mads
 */
@Controller
public class StudentPageController {

    /**
     * 个人信息
     * @return
     */
    @RequestMapping("/student/profile")
    public String home(){
        return "student/baseinfo";
    }

    /**
     * 更新个人信息
     * @return
     */
    @RequestMapping("/student/update")
    public String update(){
        return "student/baseinfo_update";
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
        return "student/course_list";
    }

    /**
     * 获取某个课程详情
     * @return
     */
    @RequestMapping("/student/course/{courseId}")
    public String courseDetail(@PathVariable String courseId){
        return "student/course";
    }

    /**
     * 选课表单
     * @return
     */
    @RequestMapping("/student/course/select")
    public String courseSelect(){
        return "student/course_select";
    }

    /**
     * seminar
     * @return
     */
    @RequestMapping("/student/course/{courseId}/seminar/{seminerId}")
    public String seminarFixed(@PathVariable String courseId, @PathVariable String seminarId,
                               @RequestParam(value = "type",required = true) String type){
        if(type == "fixed"){
            return "student/fixed_seminar";
        }else if(type == "random"){
            return "student/random_seminar";
        }
        return "error";
    }


    /**
     * 课程固定小组
     * @return
     */
    @RequestMapping("/student/course/group")
    public String group(@RequestParam(value = "action", required = false)String action){
        return "student/group";
    }

    /**
     * 更新固定小组
     * @return
     */
    @RequestMapping("/student/course/group/update")
    public String groupUpdate(){
        return "student/group_update";
    }

    /**
     * 话题
     * @return
     */
    @RequestMapping("/student/course/{courseId}/seminar/{seminarId}/topic")
    public String getFixedTopic(@RequestParam(value = "type") String type){
        if(type == "fixed") {
            return "student/fixed_topic";
        }else if(type == "random"){
            return "student/random_topic";
        }
        return "error";
    }

    /**
     * 分数
     * @return
     */
    @RequestMapping("/student/course/{courseId}/seminar/{seminar}/grade")
    public String grade(){
        return "student/grade";
    }
}
