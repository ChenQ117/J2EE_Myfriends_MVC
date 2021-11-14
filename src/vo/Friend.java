package vo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version v1.0
 * @ClassName: Friends
 * @Description:
 * @Author: ChenQ
 * @Date: 2021/11/13 on 17:38
 */
public class Friend {
    private int id;
    private int userid;
    private String name;
    private String sex;
    private int age;
    private String qq;
    private String telephone;
    private String email;
    private String address;


    public Friend(){

    };

    public Friend(int userid, String name, String sex, String qq, String telephone, String email, String address) {
        this.userid = userid;
        this.name = name;
        this.sex = sex;
        this.qq = qq;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public Friend(int userid, String name, String sex, int age, String qq, String telephone, String email, String address) {
        this.userid = userid;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.qq = qq;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setInfo(ResultSet resultSet) throws SQLException {
        setName(resultSet.getString("name"));
        setAddress(resultSet.getString("address"));
        setAge(resultSet.getInt("age"));
        setEmail(resultSet.getString("email"));
        setQq(resultSet.getString("qq"));
        setTelephone(resultSet.getString("telephone"));
        setSex(resultSet.getString("sex"));
        setUserid(resultSet.getInt("userid"));
        setId(resultSet.getInt("id"));
    }
}
