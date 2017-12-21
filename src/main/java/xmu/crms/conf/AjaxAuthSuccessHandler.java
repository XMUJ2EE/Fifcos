package xmu.crms.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mads
 * @date 2017/12/21 19:38
 */
public class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        FifcosAuthenticationToken fifcosAuthenticationToken = (FifcosAuthenticationToken) authentication;
        String student = "student";
        String teacher = "teacher";
        Map<String,Object> map = new HashMap<String, Object>();
        PrintWriter printWriter=response.getWriter();
        if(student.equals(fifcosAuthenticationToken.getType())){
            map.put("type","student");
        }else if(teacher.equals(fifcosAuthenticationToken.getType())){
            map.put("type","teacher");
        }
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        printWriter.print(objectMapper.writeValueAsString(map));
        printWriter.flush();
    }
}