package xmu.crms.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
        String username = fifcosAuthenticationToken.getPhone();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(!userDetails.getPassword().equals(fifcosAuthenticationToken.getPassword())){
            throw new BadCredentialsException("密码错误");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthority = (List<SimpleGrantedAuthority>) userDetails.getAuthorities();
        if(simpleGrantedAuthority == null){
            return null;
        }else{
            Integer type = ((UserDetailsImpl)userDetails).getType();
            String typeString = "";
            if(type == 0){
                typeString = "student";
            }else if(type == 1){
                typeString = "teacher";
            }
            return new FifcosAuthenticationToken(username,
                    fifcosAuthenticationToken.getPassword(),typeString,
                    simpleGrantedAuthority);
         }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (FifcosAuthenticationToken.class
            .isAssignableFrom(aClass));
    }
}
