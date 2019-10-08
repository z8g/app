package com.zxy97.blog.model;

/**
 * 作者类
 * @author zhaoxuyang
 */

public class Author {
    private String username;
    private String password;
    private String gmtCreate;
    private String gmtUpdate;
    private String gmtDelete;
    private String nickname;

    @Override
    public String toString() {
        return "Author{" + "username=" + username + ", password=" + password + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", gmtDelete=" + gmtDelete + ", nickname=" + nickname + '}';
    }
    
    public Author() {}
    public Author(String username, String password, String gmtCreate, String gmtUpdate, String gmtDelete, String nickname, String passowrd) {
        this.username = username;
        this.password = password;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.gmtDelete = gmtDelete;
        this.nickname = nickname;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(String gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getGmtDelete() {
        return gmtDelete;
    }

    public void setGmtDelete(String gmtDelete) {
        this.gmtDelete = gmtDelete;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    
    
}
