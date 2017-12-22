package xmu.crms.conf;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @author mads
 * @date 2017/12/21 13:20
 */
public class FifcosAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    FifcosAuthenticationProcessingFilter(){
        super(new AntPathRequestMatcher("/signin"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        Map<String, Object> o = new ObjectMapper().readValue(wholeStr, Map.class);
        return getAuthenticationManager().authenticate(new FifcosAuthenticationToken((String)o.get("phone"),(String)o.get("password")));
    }
}
