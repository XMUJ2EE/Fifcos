package xmu.crms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xmu.crms.dao.LoginDao;
import xmu.crms.mapper.LoginMapper;
import xmu.crms.security.UserDetailsImpl;

/**
 * @author mads
 * @date 2017/12/30 22:23
 */
@Repository("LoginDao")
public class LoginDaoImpl implements LoginDao {

    @Autowired(required = false)
    LoginMapper authMapper;

    @Override
    public UserDetailsImpl getUserByPhone(String phone) {
        return authMapper.getUserByPhone(phone);
    }

    @Override
    public UserDetailsImpl getUserByOpenId(String openid) {
        return authMapper.getUserByOpenId(openid);
    }
}
