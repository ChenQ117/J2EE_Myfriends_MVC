package model.dao;

import vo.Friend;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: Friends
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/11/13 on 16:32
 */
public interface FriendsDao {
    List<Friend> selectByUserId(int userId);
    List<Friend> selectAll();
    List<Friend> selectLikeName(int userId,String name);
    boolean insert(Friend friend);
    boolean update(Friend friend);
    boolean deleteById(int id);
    Friend selectFriendById(int id);
}
