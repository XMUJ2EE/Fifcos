package xmu.crms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xmu.crms.mapper.AuthMapper;
import xmu.crms.mapper.UserMapper;

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

    public UserDetails loadUserByOpenId(String openid) throws UsernameNotFoundException{
        UserDetailsImpl userDetails = userMapper.getUserByOpenId(openid);
        System.out.println(userDetails.toString());
        if(userDetails == null){
            throw new UsernameNotFoundException("未找到openId 对应用户");
        }
        return userDetails;
    }
}
