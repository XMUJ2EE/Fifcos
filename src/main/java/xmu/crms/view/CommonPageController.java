package xmu.crms.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mads
 */
@Controller
public class CommonPageController {

    /**
     * 登陆
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "common/login";
    }


    /**
     * 注册
     * @return
     */
    @RequestMapping("/register")
    public String register(){
        return "common/register";
    }

    /**
     * 微信登陆
     * @return
     */
    @RequestMapping("/wechat_login")
    public String wechatLogin(){
        return "common/wechat_login";
    }


}
