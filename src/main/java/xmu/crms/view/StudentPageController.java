package xmu.crms.view;

import org.springframework.stereotype.Controller;
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
     * 课程详情
     * @return
     */
    @RequestMapping("/student/course")
    public String courseDetail(@RequestParam(value = "action",required = false)String action,
                               @RequestParam(value = "id",required = false)String id){
        if(null == action){
            if(null == id){
                return null;
            }
            return "student/course";
        }
        return "student/course_select";
    }

    /**
     * seminar
     * @return
     */
    @RequestMapping("/student/course/seminar")
    public String seminarFixed(@RequestParam(value = "type",required = true) String type,
                               @RequestParam(value = "id", required = true) String id,
                               @RequestParam(value = "cid",required = true)String cid){
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
        if(null == action) {
            return "student/group";
        }
        return "student/group_update";
    }

    /**
     * 话题
     * @return
     */
    @RequestMapping("/student/course/seminar/topic")
    public String getFixedTopic(@RequestParam(value = "type") String type,
                                @RequestParam(value = "sid")String sid,
                                @RequestParam(value = "cid")String cid){
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
    @RequestMapping("/student/course/seminar/grade")
    public String grade(@RequestParam(value = "sid",required = true)String sid,
                        @RequestParam(value = "cid",required = true)String cid){
        return "student/grade";
    }


}
