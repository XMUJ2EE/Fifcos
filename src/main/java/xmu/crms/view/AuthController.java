package xmu.crms.view;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xmu.crms.entity.School;
import xmu.crms.exception.UserDuplicatedException;
import xmu.crms.security.FifcosAuthenticationToken;
import xmu.crms.entity.JwtAuthenticationResponse;
import xmu.crms.entity.User;
import xmu.crms.mapper.UserMapper;
import xmu.crms.security.UserDetailsImpl;
import xmu.crms.security.UserDetailsServiceImpl;
import xmu.crms.service.security.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @author mads
 * @date 2017/12/22 9:18
 */
@Controller
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/auth")
    public ResponseEntity createAuthenticationToken(HttpServletRequest httpServletRequest,
                                                       HttpServletResponse httpServletResponse) throws AuthenticationException,IOException {

        BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        if(wholeStr == null){
            return ResponseEntity.status(500).build();
        }
        Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);

        System.out.println("post登陆信息"+o.toString());

        final String token = authService.login((String)o.get("phone"), (String)o.get("password"));
        UserDetailsImpl user = userMapper.getUserByPhone((String)o.get("phone"));
        // Return the token
        String type;
        if(user.getType() == 0){
            type = "student";
        }else if(user.getType() == 1){
            type = "teacher";
        }else{
            type = "unbind";
        }
        return ResponseEntity.ok(new JwtAuthenticationResponse(user.getId(),type,user.getName(),token));
    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public User register(@RequestBody User addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }

    @RequestMapping(value = "/auth/weChat", method = RequestMethod.POST)
    public ResponseEntity weChatLogin(HttpServletRequest request) throws IOException{
        BufferedReader br = request.getReader();
        String str, code = "";
        while((str = br.readLine()) != null){
            code += str;
        }
        Map<String, Object> o = new ObjectMapper().readValue(code, Map.class);
        try{
            Map<String, Object> ne = authService.weChatLogin((String)o.get("code"), (Integer)o.get("type"));
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(ne);
        }catch (UserDuplicatedException e){
            return ResponseEntity.status(403).build();
        }
    }

}