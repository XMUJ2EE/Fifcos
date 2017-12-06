package xmu.crms.view;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mads
 */
public class StudentPageController {

    @RequestMapping("/student/home")
    public String home(){
        return "student/StudentHomePage";
    }
}
