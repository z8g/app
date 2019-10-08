package com.zxy97.blog.database;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory = null ;
    private MyBatisUtil(){
    }
    private static SqlSessionFactory getSqlSessionFactory(){
        InputStream inputStream;
        if(null == sqlSessionFactory){
            String resource = "mybatis-conf.xml";
            try {
                // Reader reader=Resources.getResourceAsReader(resource);  // 可以用Reader替换inputStream
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                return sqlSessionFactory ;
            } catch (IOException e) {
                System.err.println(e);
                e.getStackTrace();
            }
        }
        return sqlSessionFactory ;
    }
    
    public static SqlSession getSqlSession(){
        return getSqlSessionFactory().openSession();
    }

}