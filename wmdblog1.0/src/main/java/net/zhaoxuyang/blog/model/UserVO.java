/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zhaoxuyang
 */
public class UserVO implements Serializable{
    private Integer userId;
    private String username;
    List<Article> articles;

    @Override
    public String toString() {
        return "UserVO{" + "userId=" + userId + ", username=" + username + ", articleList=" + articles + '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articleList) {
        this.articles = articleList;
    }
    
}
