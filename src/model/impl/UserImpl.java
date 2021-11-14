package model.impl;

import model.dao.UserDao;
import utils.ConnectionPool;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: UserImpl
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/11/13 on 16:33
 */
public class UserImpl implements UserDao {
    private ConnectionPool pool;
    public UserImpl(){
        pool = ConnectionPool.getInstance();
    }
    @Override
    public List<User> selectUser() {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from user";
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet resultSet = pst.executeQuery();
                List<User> userList = new ArrayList<>();
                while (resultSet.next()){
                    User user = new User();
                    user.setInfo(resultSet);
                    userList.add(user);
                }
                pool.release(connection);
                connection.close();
                return userList;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            return null;
        }
        return null;
    }

    @Override
    public User selectUserByNameAndPassword(String name,String password) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from user where name =? and password =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,name);
                pst.setString(2,password);
                ResultSet resultSet = pst.executeQuery();
                User user = null;
                if (resultSet.next()){
                    user = new User();
                    user.setInfo(resultSet);
                }
                pool.release(connection);
                return user;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            return null;
        }
        return null;
    }

    @Override
    public User selectUserByName(String name) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from user where name =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,name);
                ResultSet resultSet = pst.executeQuery();
                User user = null;
                if (resultSet.next()){
                    user = new User();
                    user.setInfo(resultSet);
                }
                pool.release(connection);
                return user;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            return null;
        }
        return null;
    }

    @Override
    public boolean insertUser(String name, String password) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "insert into user(name ,password) values (?,?)";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1,name);
                pst.setString(2,password);
                pst.executeUpdate();
                pool.release(connection);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
