package xmu.crms.view.vo;

import xmu.crms.entity.Attendance;
import xmu.crms.entity.Location;

public class AttendanceVO {
    private int numPresent;
    private int numStudent;
    private String status;
    private String group;

    public AttendanceVO(Location location, int numPresent, int numStudent) {
        this.numPresent = numPresent;
        this.numStudent = numStudent;
        this.status = (location.getStatus() == 0) ? "end" : ((location.getStatus() == 1) ? "calling" : "break");
        this.group = null;
    }


    public AttendanceVO(int numPresent, int numStudent, String status, String group) {
        this.numPresent = numPresent;
        this.numStudent = numStudent;
        this.status = status;
        this.group = group;
    }

    public int getNumPresent() {
        return numPresent;
    }

    public void setNumPresent(int numPresent) {
        this.numPresent = numPresent;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
