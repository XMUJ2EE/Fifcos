package xmu.crms.view.vo;

import xmu.crms.entity.SeminarGroup;

public class GroupVO {

    private int id;
    private String name;

    public GroupVO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public GroupVO(SeminarGroup seminarGroup) {
        this.id = seminarGroup.getId().intValue();
        this.name = seminarGroup.getId().toString();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
