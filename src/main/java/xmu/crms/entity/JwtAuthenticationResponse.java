package xmu.crms.entity;

import java.math.BigInteger;

/**
 * @author mads
 * @date 2017/12/22 9:33
 */
public class JwtAuthenticationResponse {
    private BigInteger id;
    private String type;
    private String name;
    private String jwt;

    public JwtAuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public JwtAuthenticationResponse(BigInteger id, String type, String name, String jwt) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.jwt = jwt;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationResponse{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
