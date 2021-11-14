package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: User
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/11/13 on 17:37
 */
public class User {
    private String name;
    private String password;
    private int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setInfo(ResultSet resultSet) throws SQLException {
        setName(resultSet.getString("name"));
        setPassword(resultSet.getString("password"));
        setUserid(resultSet.getInt("userid"));
    }
}
