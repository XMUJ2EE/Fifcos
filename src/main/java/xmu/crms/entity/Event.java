package xmu.crms.entity;

import java.math.BigInteger;
import java.util.Date;

public class Event {
    private BigInteger id;
    private String bean;
    private String parameter;
    private Date time;

    public Event(BigInteger id, String bean, String parameter, Date time) {
        this.id = id;
        this.bean = bean;
        this.parameter = parameter;
        this.time = time;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
