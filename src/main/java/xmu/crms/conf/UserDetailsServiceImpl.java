package xmu.crms.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xmu.crms.entity.User;
import xmu.crms.mapper.UserMapper;
import xmu.crms.service.UserService;

/**
 * @author mads
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetailsImpl userDetails = userMapper.getUserByPhone(s);
        if(userDetails == null){
            throw new UsernameNotFoundException("未找到用户");
        }
        return userDetails;
    }
}
