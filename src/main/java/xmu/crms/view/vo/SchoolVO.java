package xmu.crms.view.vo;

import xmu.crms.entity.School;

import java.math.BigInteger;

/**
 * @author mads
 */
public class SchoolVO {
    private BigInteger id;
    private String name;


    public SchoolVO() {
    }

    public SchoolVO(School school) {
        this.id = school.getId();
        this.name = school.getName();
    }

    public SchoolVO(BigInteger id, String name) {
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
        return "SchoolVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
