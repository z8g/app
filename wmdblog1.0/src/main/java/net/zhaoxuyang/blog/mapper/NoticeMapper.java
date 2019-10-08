/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.zhaoxuyang.blog.mapper;

import java.util.List;
import java.util.Map;
import net.zhaoxuyang.blog.model.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author wyx
 */
@Repository
public interface NoticeMapper {
    public List<User> listNotice(User user);
    public List<User> listFan(User user);
    public int insert(Map map);
    public int delete(Map map);  
    public int select(Map map);
    public int countNotice(User user);
    public int countFan(User user);
}
