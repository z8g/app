package pan.model;

import java.io.Serializable;

/**
 * @名称 用户模型
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class User implements Serializable{
    private Integer id;         //用户id
    private String username;    //用户名
    private String password;    //用户密码
    private String email;       //用户邮箱
    private String gmtCreate;   //创建时间

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", gmtCreate=" + gmtCreate + '}';
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
