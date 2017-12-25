package xmu.crms.mapper;

import org.springframework.security.core.userdetails.UserDetails;
import xmu.crms.entity.User;

/**
 * @author mads
 * @date 2017/12/25 13:27
 */
public interface AuthMapper {

    /**
     * 根据openid拿用户
     * @param openid
     * @return
     */
    User getUserByOpenId(String openid);
}
