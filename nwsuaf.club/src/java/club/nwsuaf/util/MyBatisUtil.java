package club.nwsuaf.util;

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

    private static final SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

    private MyBatisUtil() {
    }

    private static SqlSessionFactory getSqlSessionFactory() {
        String resource = "mybatis-conf.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (final Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
