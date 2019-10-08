package com.zxy97.blog.service;

import com.zxy97.blog.database.MyBatisUtil;
import com.zxy97.blog.mapper.AuthorMapper;
import com.zxy97.blog.model.Author;
import org.apache.ibatis.session.SqlSession;

public class LoginService {
    
    public static void main(String[] args){
        Author resultAuthor = login("zhaoxuyang","zhaoxuyang");
        System.out.println(resultAuthor);
    }
    
    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功与否
     */
    public static Author login(String username, String password){
        Author resultAuthor = new Author();
        Author author = new Author();
        author.setUsername(username);
        author.setPassword(password);
        
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                AuthorMapper mapper=sqlSession.getMapper(AuthorMapper.class);
                resultAuthor = mapper.authorLogin(author);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
            }
         }
         return resultAuthor;
    }
}
