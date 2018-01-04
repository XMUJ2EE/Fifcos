package xmu.crms.dao;

import org.apache.ibatis.annotations.Param;
import xmu.crms.security.UserDetailsImpl;

/**
 * @author mads
 * @date 2017/12/30 22:21
 */
public interface LoginDao {
    /**
     * 根据手机号登陆用
     * @param phone
     * @return
     */
    UserDetailsImpl getUserByPhone(String phone);

    /**
     * 根据openid登录用
     * @param openid
     * @return
     */
    UserDetailsImpl getUserByOpenId(String openid);
}
