package xmu.crms.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mads
 * @date 2017/12/21 16:40
 */

@RestController
public class TestController {
    @RequestMapping("/whoami")
    public Object whoIm()
    {

        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
