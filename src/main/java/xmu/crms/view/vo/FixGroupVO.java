package xmu.crms.view.vo;

import xmu.crms.entity.FixGroup;
import xmu.crms.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mads
 * @date 2017/12/24 22:18
 */
public class FixGroupVO {
    private UserVO leader;
    private List<UserVO> member;

    public FixGroupVO() {
    }

    public FixGroupVO(FixGroup fixGroup, List<User> members) {
        this.leader = new UserVO(fixGroup.getLeader());
        List<UserVO> userVOS = new ArrayList<>();
        for(User user:members){
            userVOS.add(new UserVO(user));
        }
        this.member = userVOS;
    }

    public FixGroupVO(UserVO leader, List<UserVO> member) {
        this.leader = leader;
        this.member = member;
    }

    public UserVO getLeader() {
        return leader;
    }

    public void setLeader(UserVO leader) {
        this.leader = leader;
    }

    public List<UserVO> getMember() {
        return member;
    }

    public void setMember(List<UserVO> member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "FixGroupVO{" +
                "leader=" + leader +
                ", member=" + member +
                '}';
    }
}
