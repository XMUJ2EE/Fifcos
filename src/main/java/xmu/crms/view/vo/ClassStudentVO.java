package xmu.crms.view.vo;

import java.math.BigInteger;

public class ClassStudentVO {
    private BigInteger id;
    private String name;
    private String number;

    public ClassStudentVO(BigInteger id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
