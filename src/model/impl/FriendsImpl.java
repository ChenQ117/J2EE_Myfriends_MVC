package model.impl;

import model.dao.FriendsDao;
import utils.ConnectionPool;
import vo.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ClassName: FriendsImpl
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/11/13 on 16:33
 */
public class FriendsImpl implements FriendsDao {
    private ConnectionPool pool;
    public FriendsImpl(){
        pool = ConnectionPool.getInstance();
    }
    @Override
    public List<Friend> selectByUserId(int userId) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from myfriend where userid =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1,userId);
                ResultSet resultSet = pst.executeQuery();
                List<Friend> friendList = new ArrayList<>();
                while (resultSet.next()){
                    Friend friend = new Friend();
                    friend.setInfo(resultSet);
                    friendList.add(friend);
                }
                pool.release(connection);
                return friendList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            return null;
        }
        return null;
    }

    @Override
    public List<Friend> selectAll() {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from myfriend";
                PreparedStatement pst = connection.prepareStatement(sql);
                ResultSet resultSet = pst.executeQuery();
                List<Friend> friendList = new ArrayList<>();
                while (resultSet.next()){
                    Friend friend = new Friend();
                    friend.setInfo(resultSet);
                    friendList.add(friend);
                }
                pool.release(connection);
                return friendList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            return null;
        }
        return null;
    }

    @Override
    public List<Friend> selectLikeName(int userId,String name) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from myfriend where userid = ? and name like '%"+name+"%'";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1,userId);
//                pst.setString(2,name);
                ResultSet resultSet = pst.executeQuery();
                List<Friend> friendList = new ArrayList<>();
                while (resultSet.next()){
                    Friend friend = new Friend();
                    friend.setInfo(resultSet);
                    friendList.add(friend);
                }
                pool.release(connection);
                return friendList;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            return null;
        }
        return null;
    }

    @Override
    public boolean insert(Friend friend) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "insert into myfriend(userid,name,sex,age,qq,telephone,email,address) values (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1,friend.getUserid());
                pst.setString(2,friend.getName());
                pst.setString(3,friend.getSex());
                pst.setInt(4,friend.getAge());
                pst.setString(5,friend.getQq());
                pst.setString(6,friend.getTelephone());
                pst.setString(7,friend.getEmail());
                pst.setString(8,friend.getAddress());
                pst.executeUpdate();
                pool.release(connection);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Friend friend) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "update myfriend " +
                        "set userid=?, name=?,sex=?,age=?,qq=?,telephone=?," +
                        "email=?,address=?" +
                        "where id=?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1,friend.getUserid());
                pst.setString(2,friend.getName());
                pst.setString(3,friend.getSex());
                pst.setInt(4,friend.getAge());
                pst.setString(5,friend.getQq());
                pst.setString(6,friend.getTelephone());
                pst.setString(7,friend.getEmail());
                pst.setString(8,friend.getAddress());
                pst.setInt(9,friend.getId());
                pst.executeUpdate();
                pool.release(connection);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "delete from myfriend where id=?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1,id);
                pst.executeUpdate();
                pool.release(connection);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Friend selectFriendById(int id) {
        Connection connection = pool.getConnection();
        if (connection!=null){
            try {
                String sql = "select * from myfriend where id =?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1,id);
                ResultSet resultSet = pst.executeQuery();
                Friend friend = null;
                if (resultSet.next()){
                    friend = new Friend();
                    friend.setInfo(resultSet);
                }
                pool.release(connection);
                return friend;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
