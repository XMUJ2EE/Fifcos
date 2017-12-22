package xmu.crms.view.vo;

import java.math.BigInteger;

/**
 * @author mads
 */
public class UserVO {
    private BigInteger id;
    private String name;

    public UserVO() {
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
