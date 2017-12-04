package xmu.crms.service;

import xmu.crms.entity.User;

/**
 * User的服务接口
 * @author XueZhang.Liu
 *
 */

public interface UserService {

    /**
     * 根据Session中的id查找当前登录用户的User对象
     * @param id 数据库的自增id主键
     * @return 查询到的User对象
     */
    User getUserById(int id);

    /**
     * 根据Session中的id更新当前登录用户
     * @param id 数据库中的自增id主键
     * @param  user 传过来的更新信息
     * @return True or False
     */
    Boolean updateUserById(int id, User user);
}
