package xmu.crms.mapper;

import org.apache.ibatis.annotations.Mapper;
import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * User的mapper
 * @author mads
 */

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查找用户
     * @param id
     * @return
     */
    User getUserByUserId(BigInteger id);

    /**
     * 更新用户
     * @param id
     * @param user
     */
    void updateUserByUserId(BigInteger id, User user);

}
