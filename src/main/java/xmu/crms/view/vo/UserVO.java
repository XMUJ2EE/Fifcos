package xmu.crms.view.vo;

import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * @author mads
 */
public class UserVO {
    private BigInteger id;
    private String name;

    public UserVO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public UserVO(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
