package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;

/**
 *
 * @author Administrator
 */
public class MybatisUtilJavaTemplate extends Template {

    public String getContent(Config config) {

        StringBuilder result = new StringBuilder();
        result.append("package ").append(config.getPackageRoot()).append(".util;\n\n")
                .append("import java.io.IOException;\n")
                .append("import java.io.InputStream;\n")
                .append("import org.apache.ibatis.io.Resources;\n")
                .append("import org.apache.ibatis.session.SqlSession;\n")
                .append("import org.apache.ibatis.session.SqlSessionFactory;\n")
                .append("import org.apache.ibatis.session.SqlSessionFactoryBuilder;\n\n")
                .append("/**\n")
                .append(" * @名称 MyBatis工具类，单例模式\n")
                .append(" * @作者 ${author}\n")
                .append(" * @日期 ${datetime}\n")
                .append(" */\n")
                .append("public class MyBatisUtil {\n")
                .append("\n")
                .append("    private static SqlSessionFactory sqlSessionFactory = null;\n")
                .append("\n")
                .append("    private MyBatisUtil() {\n")
                .append("    }\n")
                .append("\n")
                .append("    private static SqlSessionFactory getSqlSessionFactory() {\n")
                .append("        InputStream inputStream;\n")
                .append("        if (null == sqlSessionFactory) {\n")
                .append("            String resource = \"mybatis-conf.xml\";\n")
                .append("            try {\n")
                .append("                // Reader reader=Resources.getResourceAsReader(resource); \n")
                .append("                inputStream = Resources.getResourceAsStream(resource);\n")
                .append("                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);\n")
                .append("                return sqlSessionFactory;\n")
                .append("            } catch (IOException e) {\n")
                .append("                System.err.println(e);\n")
                .append("                e.getStackTrace();\n")
                .append("            }\n")
                .append("        }\n")
                .append("        return sqlSessionFactory;\n")
                .append("    }\n")
                .append("\n")
                .append("    public static SqlSession getSqlSession() {\n")
                .append("        return getSqlSessionFactory().openSession();\n")
                .append("    }\n")
                .append("\n")
                .append("}\n");

        return result.toString();
    }

    public String getFilePath(Config config) {
        StringBuilder result = new StringBuilder();
        result.append(config.getSrc())
                .append("/")
                .append(config.getPackageRoot().replaceAll("\\.", "/"))
                .append("/util/MyBatisUtil.java");
        return result.toString();
    }

    @Override
    public void createFile(Config config) {
        write(getFilePath(config), getContent(config));
    }

}
