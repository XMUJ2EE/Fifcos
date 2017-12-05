package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.Gender;
import xmu.crms.entity.School;
import xmu.crms.entity.Type;
import xmu.crms.entity.User;
import xmu.crms.view.vo.SchoolVO;
import xmu.crms.view.vo.UserDetailVO;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDetailVO getUserById(int id) {
        SchoolVO school = new SchoolVO(32,"厦门大学");
        return new UserDetailVO(3486, Type.STUDENT,"23320152202333","张三","18911114514","23320152202333@stu.xmu.edu.cn", Gender.MALE,school,"","/avatar/3486.png");
    }

    @Override
    public Boolean updateUserById(int id, User user) {
        return true;
    }
}
