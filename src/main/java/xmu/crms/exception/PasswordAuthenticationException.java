package xmu.crms.exception;

import javax.naming.AuthenticationException;

/**
 * @author mads
 * @date 2017/12/21 13:34
 */
public class PasswordAuthenticationException extends AuthenticationException {
    public PasswordAuthenticationException(String message) {
        super(message);
    }
}
