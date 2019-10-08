package pan.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @名称 MyBatis工具类，单例模式
 * @作者 zhaoxuyang
 * @版本 v1.0
 * @日期 2019-01-01
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory = null;

    private MyBatisUtil() {
    }

    private static SqlSessionFactory getSqlSessionFactory() {
        InputStream inputStream;
        if (null == sqlSessionFactory) {
            String resource = "mybatis-conf.xml";
            try {
                // Reader reader=Resources.getResourceAsReader(resource);  // 可以用Reader替换inputStream
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
                return sqlSessionFactory;
            } catch (IOException e) {
                System.err.println(e);
                e.getStackTrace();
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();
    }

}
