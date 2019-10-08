/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zhaoxuyang.blog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.zhaoxuyang.blog.mapper.NoticeMapper;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;
    
    @Override
    public boolean insert(Integer user_id, Integer notice_id) {
        Map<String, Object> map = new HashMap<>();
        Date now = new Date();
        map.put("user_id", user_id);
        map.put("notice_id", notice_id);
        map.put("gmt_create", now);
        
        int n = noticeMapper.insert(map);
        return n > 0;       
    }

    @Override
    public boolean delete(Integer user_id, Integer notice_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("notice_id", notice_id);
        
        int n = noticeMapper.delete(map);
        return n > 0;       
    }

    @Override
    public boolean isFriend(Integer user_id, Integer notice_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("notice_id", notice_id);
        
        int n = noticeMapper.select(map);
        return n > 0;    
    }

    @Override
    public List<User> listNotice(Integer user_id) {
        User user = new User();
        user.setId(user_id);
        return noticeMapper.listNotice(user);
    }

    @Override
    public List<User> listFan(Integer notice_id) {
        User user = new User();
        user.setId(notice_id);
        return noticeMapper.listFan(user);
    }

    @Override
    public int countNotice(Integer user_id) {
        User user=new User();
        user.setId(user_id);    
        int result = noticeMapper.countNotice(user);
        System.out.println("用户关注量："+result);
        return result;   
    }

    @Override
    public int countFan(Integer notice_id) {
        User user = new User();
        user.setId(notice_id);
        int result = noticeMapper.countFan(user);
        System.out.println("用户粉丝量：" + result);
        return result;
    }
    
}
