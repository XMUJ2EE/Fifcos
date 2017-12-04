package xmu.crms.service;

import org.springframework.stereotype.Service;
import xmu.crms.entity.Gender;
import xmu.crms.entity.School;
import xmu.crms.entity.Type;
import xmu.crms.entity.User;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int id) {
        School school = new School(32,"厦门大学","福建省","厦门");
        return new User(123, Type.STUDENT,"24320152202777","张三","18888888888","mad.s@qq.com", Gender.MALE,school,"","12312412","451565","/avator/213.png");
    }

    @Override
    public Boolean updateUserById(int id, User user) {
        return true;
    }
}
