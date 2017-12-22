package xmu.crms.service.security;

import xmu.crms.entity.User;

/**
 * @author mads
 * @date 2017/12/22 8:48
 */
public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
