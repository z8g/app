/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.model;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 *
 * @author zhaoxuyang
 */
public class User {
    private Integer id;
    
    @Size(min=3,max=20)
    private String username;
    private String salt;
    private String password;
    
    @Email
    private String email;
    private Integer auth;
    
    private Date gmtCreate;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", salt=" + salt + ", password=" + password + ", email=" + email + ", auth=" + auth + ", gmtCreate=" + gmtCreate + '}';
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }


}
