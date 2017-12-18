package xmu.crms.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import xmu.crms.entity.User;
import xmu.crms.mapper.UserMapper;

import java.math.BigInteger;

/**
 * User 的DAO层
 * @author mads
 */

@Component
public class UserDao {

    private final SqlSession sqlSession;

    public UserDao(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public User getUserByUserId(BigInteger id){
        return this.sqlSession.getMapper(UserMapper.class).getUserByUserId(id);
    }

    public void updateUserByUserId(BigInteger id, User user){
        this.sqlSession.getMapper(UserMapper.class).updateUserByUserId(id, user);
        return;
    }

}
