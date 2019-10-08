package com.zxy97.blog.service;

import com.zxy97.blog.database.MyBatisUtil;
import com.zxy97.blog.mapper.AuthorMapper;
import com.zxy97.blog.model.Author;
import com.zxy97.blog.util.string.GetDateTime;
import org.apache.ibatis.session.SqlSession;

public class RegisterService {

    public static void main(String[] args) {
        boolean isSuccess = registerCheck("zha3oxuyang");
        System.out.println(isSuccess);
    }

    /**
     * 注册查重
     * @param username
     * @return 是否可以注册
     */
     public static boolean registerCheck(String username) {
        boolean isSuccess;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                AuthorMapper mapper = sqlSession.getMapper(AuthorMapper.class);
                String str = mapper.authorRegisterCheck(username);
                sqlSession.commit();
                isSuccess = (str == null);
            } catch (Exception e) {
                sqlSession.rollback();
                isSuccess = false;
            }
        }
        return isSuccess;
    }

     /**
      * 注册
      * @param username 用户名
      * @param password 密码
      * @return 注册成功与否
      */
    public static boolean register(String username, String password) {
        boolean isSuccess;

        Author author = new Author();
        author.setUsername(username);
        author.setPassword(password);
        author.setGmtCreate(GetDateTime.getNow());

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            try {
                AuthorMapper mapper = sqlSession.getMapper(AuthorMapper.class);
                mapper.authorRegister(author);
                sqlSession.commit();
                isSuccess = true;
            } catch (Exception e) {
                sqlSession.rollback();
                isSuccess = false;
            }
        }
        return isSuccess;
    }
}
