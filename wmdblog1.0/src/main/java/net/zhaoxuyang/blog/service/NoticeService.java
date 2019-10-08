/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zhaoxuyang.blog.service;

import java.util.List;
import net.zhaoxuyang.blog.model.User;

/**
 *
 * @author wyx
 */
public interface NoticeService {
    public boolean insert(Integer user_id, Integer notice_id);//插入

    public boolean delete(Integer user_id, Integer notice_id);//删除

    public boolean isFriend(Integer user_id, Integer notice_id);//判断    
    
    public List<User> listNotice(Integer user_id);//列出所有关注用户
    
    public List<User> listFan(Integer notice_id);//列出所有粉丝用户

    /**
     * 关注数
     */
    public int countNotice(Integer user_id);
    /**
     * 粉丝数
     */
    public int countFan(Integer notice_id);
}
