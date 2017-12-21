package xmu.crms.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author mads
 */
@Controller
public class CommonPageController {
    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(){
        return "common/login";
    }


    @RequestMapping("/login?error")
    public String loginError(){
        return "/common/login_error";
    }
    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "common/register";
    }

    /**
     * 微信登陆
     * @return
     */
    @RequestMapping(value = "/wechat_login", method = RequestMethod.GET)
    public String wechatLogin(){
        return "common/wechat_login";
    }


}
