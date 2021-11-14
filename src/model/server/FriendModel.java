package model.server;

import model.dao.FriendsDao;
import model.impl.FriendsImpl;
import vo.Friend;

import java.util.List;

/**
 * @version v1.0
 * @ClassName: FriendModel
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/11/13 on 19:27
 */
public class FriendModel {
    private FriendsDao friendsDao = new FriendsImpl();
    public FriendModel(){

    }
    //获得某个用户的所有friend
    public List<Friend> getFriends(int userId){
        List<Friend> friendList = friendsDao.selectByUserId(userId);
        return friendList;
    }
    //模糊查找某个用户的friend
    public List<Friend> getLikeFriends(int userId,String likeName){
        List<Friend> friendList = friendsDao.selectLikeName(userId, likeName);
        return friendList;
    }
    //添加好友
    public boolean addFriend(Friend friend){
        return friendsDao.insert(friend);
    }
    //修改好友
    public boolean updateFriend(Friend friend){
        return friendsDao.update(friend);
    }
    //删除多个好友
    public void deleteFriends(String[] ids){
        for (int i =0;i<ids.length;i++){
            int id = Integer.parseInt(ids[i]);
            friendsDao.deleteById(id);
        }
    }
    public Friend getFriendById(int id){
        return friendsDao.selectFriendById(id);
    }
}
