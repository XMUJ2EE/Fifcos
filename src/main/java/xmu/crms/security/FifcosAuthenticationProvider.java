package xmu.crms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import xmu.crms.security.FifcosAuthenticationToken;
import xmu.crms.security.UserDetailsImpl;
import xmu.crms.security.UserDetailsServiceImpl;

import java.util.List;

/**
 * @author mads
 */
public class FifcosAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        FifcosAuthenticationToken fifcosAuthenticationToken =
                (FifcosAuthenticationToken) authentication;
        UserDetails userDetails;
        if(fifcosAuthenticationToken.getOpenid() == null) {
            // web端认证
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String username = fifcosAuthenticationToken.getPhone();
            userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println(fifcosAuthenticationToken.getPassword());
            System.out.println(userDetails.getPassword());
            System.out.println(encoder.encode("123"));
            Integer a = 123;
            System.out.println(encoder.encode(a.toString()));
            if (!userDetails.getPassword().equals(fifcosAuthenticationToken.getPassword())) {
                throw new BadCredentialsException("密码错误");
            }
        }else {
            //小程序端认证
            String openid = fifcosAuthenticationToken.getOpenid();
            userDetails = userDetailsService.loadUserByOpenId(openid);
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthority = (List<SimpleGrantedAuthority>) userDetails.getAuthorities();
        if (simpleGrantedAuthority == null) {
            throw new BadCredentialsException("没有权限");
        } else {
            Integer type = ((UserDetailsImpl) userDetails).getType();
            String typeString = "";
            if (type == 0) {
                typeString = "student";
            } else if (type == 1) {
                typeString = "teacher";
            }
            if(((UserDetailsImpl) userDetails).getOpenid() == null){
                return new FifcosAuthenticationToken(((UserDetailsImpl) userDetails).getId(),
                        ((UserDetailsImpl) userDetails).getNumber(),
                        ((UserDetailsImpl) userDetails).getPhone(),
                        fifcosAuthenticationToken.getPassword(), typeString,
                        simpleGrantedAuthority);
            }else{
                return new FifcosAuthenticationToken(((UserDetailsImpl) userDetails).getOpenid(),
                        ((UserDetailsImpl) userDetails).getId(),
                        ((UserDetailsImpl) userDetails).getNumber(),
                        ((UserDetailsImpl) userDetails).getPhone()
                        , typeString,
                        simpleGrantedAuthority);
            }

        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (FifcosAuthenticationToken.class
            .isAssignableFrom(aClass));
    }
}
