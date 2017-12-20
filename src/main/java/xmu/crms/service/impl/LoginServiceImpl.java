package xmu.crms.service.impl;

import xmu.crms.entity.User;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.LoginService;

import java.math.BigInteger;

/**
 * @author mads
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public User signInWeChat(BigInteger userId, String code, String state, String successUrl) throws UserNotFoundException {
        return null;
    }

    @Override
    public User signInPhone(User user) throws UserNotFoundException {
        return null;
    }
}
