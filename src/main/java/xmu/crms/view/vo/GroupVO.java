package xmu.crms.view.vo;

import xmu.crms.entity.SeminarGroup;

import java.math.BigInteger;

/**
 * @author mads
 */
public class GroupVO {

    private BigInteger id;
    private String name;


    public GroupVO(SeminarGroup seminarGroup) {
        this.id = seminarGroup.getId();
        this.name = seminarGroup.getName();
    }

    public GroupVO(BigInteger id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
