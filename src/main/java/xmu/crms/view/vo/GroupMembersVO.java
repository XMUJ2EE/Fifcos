package xmu.crms.view.vo;

import java.util.List;

public class GroupMembersVO {
    private int id;
    private String name;
    private UserVO leader;
    private List<UserVO> members;
    private String report;

    public GroupMembersVO(int id, String name, UserVO leader, List<UserVO> members, String report) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.members = members;
        this.report = report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserVO getLeader() {
        return leader;
    }

    public void setLeader(UserVO leader) {
        this.leader = leader;
    }

    public List<UserVO> getMembers() {
        return members;
    }

    public void setMembers(List<UserVO> members) {
        this.members = members;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
