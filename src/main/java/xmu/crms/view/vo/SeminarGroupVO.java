package xmu.crms.view.vo;

import java.util.List;

public class SeminarGroupVO {
    private ClassStudentVO leader;
    private List<ClassStudentVO> members;

    public SeminarGroupVO(ClassStudentVO leader, List<ClassStudentVO> members) {
        this.leader = leader;
        this.members = members;
    }

    public ClassStudentVO getLeader() {
        return leader;
    }

    public void setLeader(ClassStudentVO leader) {
        this.leader = leader;
    }

    public List<ClassStudentVO> getMembers() {
        return members;
    }

    public void setMembers(List<ClassStudentVO> members) {
        this.members = members;
    }
}
