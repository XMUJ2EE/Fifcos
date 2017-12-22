package xmu.crms.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.AuthenticationManagerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Service;
import xmu.crms.security.FifcosAuthenticationProvider;
import xmu.crms.security.FifcosAuthenticationToken;
import xmu.crms.security.UserDetailsImpl;
import xmu.crms.entity.User;
import xmu.crms.exception.UserDuplicatedException;
import xmu.crms.security.UserDetailsServiceImpl;
import xmu.crms.service.UserService;
import xmu.crms.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mads
 * @date 2017/12/22 8:49
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    FifcosAuthenticationProvider fifcosAuthenticationProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;



    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getNumber();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        try{
            User user = userService.signUpPhone(userToAdd);
            return user;
        }catch (UserDuplicatedException e){
            System.out.println(e.toString());
            return null;
        }
    }


    @Override
    public String login(String username, String password) {
        FifcosAuthenticationToken upToken = new FifcosAuthenticationToken(username, password);
        final Authentication authentication = fifcosAuthenticationProvider.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Map<String, Object> claims = new HashMap<String, Object>();
//        claims.put("id", ((FifcosAuthenticationToken)authentication).ge);
        claims.put("type", ((FifcosAuthenticationToken)authentication).getType());
        final String token = jwtTokenUtil.doGenerateToken(claims, ((FifcosAuthenticationToken)authentication).getPhone(),null);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }
}
