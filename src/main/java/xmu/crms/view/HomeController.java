package xmu.crms.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mads
 */
@Controller
public class HomeController {

    @RequestMapping("/login")
    public String login(){
        return "common/AccountLoginPage";
    }
}
