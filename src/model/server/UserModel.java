package model.server;

import model.dao.UserDao;
import model.impl.UserImpl;
import vo.User;

/**
 * @version v1.0
 * @ClassName: UserModel
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/11/13 on 17:19
 */
public class UserModel {
    private UserDao userDao = new UserImpl();
    //登录验证
    public int loginCheck(String name,String password){
        User user = userDao.selectUserByNameAndPassword(name, password);
        if (user!=null){
            return user.getUserid();
        }
        return -1;
    }
    //注册账户 返回是否注册成功
    public boolean registerUser(String name,String password){
        User user = userDao.selectUserByName(name);
        if (user == null){
            userDao.insertUser(name,password);
            return true;
        }
        return false;
    }
}
