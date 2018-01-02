package xmu.crms.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;
import xmu.crms.entity.User;
import xmu.crms.security.UserDetailsImpl;

/**
 * @author mads
 * @date 2017/12/25 13:27
 */
public interface LoginMapper {
    /**
     * 根据手机号登陆用
     * @param phone
     * @return
     */
    UserDetailsImpl getUserByPhone(@Param("phone") String phone);

    /**
     * 根据openid登录用
     * @param openid
     * @return
     */
    UserDetailsImpl getUserByOpenId(@Param("openid") String openid);
}
