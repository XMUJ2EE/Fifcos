package xmu.crms.conf;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * @author mads
 * @date
 */
public class FifcosAuthenticationToken extends AbstractAuthenticationToken {
    private String phone;
    private String password;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 认证之前
    public FifcosAuthenticationToken(String phone, String password) {
        super(null);
        this.phone = phone;
        this.password = password;
        super.setAuthenticated(false);
    }

    // 认证之后，带一个type
    public FifcosAuthenticationToken(String phone, String password, String type, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.phone = phone;
        this.password = password;
        this.type = type;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.phone;
    }
}
