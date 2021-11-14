package model.dao;

import vo.User;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: UserDao
 * @Description: 用户数据库操作接口
 * @Author: ChenQ
 * @Date: 2021/11/13 on 16:26
 */
public interface UserDao {
    List<User> selectUser();
    User selectUserByNameAndPassword(String name,String password);
    User selectUserByName(String name);
    /**
     * 根据用户名和密码进行插入
     * @param name
     * @param password
     */
    boolean insertUser(String name,String password);
}
