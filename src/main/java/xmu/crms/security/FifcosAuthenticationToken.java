package xmu.crms.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.math.BigInteger;
import java.util.Collection;

/**
 * @author mads
 * @date
 */
public class FifcosAuthenticationToken extends AbstractAuthenticationToken {
    private BigInteger id;
    private String openid;
    private String number;
    private String phone;
    private String password;
    private String type;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    // web认证之前
    public FifcosAuthenticationToken(String phone, String password) {
        super(null);
        this.id=null;
        this.phone = phone;
        this.password = password;
        this.type = null;
        super.setAuthenticated(false);
    }

    // web认证之后，带一个type
    public FifcosAuthenticationToken(BigInteger id, String number,String phone, String password, String type, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.openid = null;
        this.id = id;
        this.number = number;
        this.phone = phone;
        this.password = password;
        this.type = type;
        super.setAuthenticated(true);
    }

    // 小程序认证之前
    public FifcosAuthenticationToken(String openid, Integer type){
        super(null);
        this.id = null;
        this.openid = openid;
        this.phone = null;
        this.password = null;
        this.type = (type==0?"student":"teacher" );
        super.setAuthenticated(false);
    }
    // 小程序认证之后
    public FifcosAuthenticationToken(String openid, BigInteger id, String number, String phone, String type, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.password = null;
        this.openid = openid;
        this.id = id;
        this.number = number;
        this.phone = phone;
        this.type = type;
    }
    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.id;
    }
}
