package club.nwsuaf.util;

import org.apache.ibatis.session.SqlSession;

public class MyBatisUtilTest {
    public static void main(String[] args){
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            
        }
    }
}
