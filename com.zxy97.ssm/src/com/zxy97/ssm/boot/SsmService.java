package com.zxy97.ssm.boot;

/**
 * @描述 RMI服务
 * @作者 赵栩旸
 */
public interface SsmService{
    
    /**
     * 
     * @param jdbcDriverClass JDBC连接驱动的类名，如
     * @param jdbcUrl JDBC的连接URL
     * @param jdbcUsername 数据库用户名
     * @param jdbcPassword 数据库密码
     * @param src 生成到哪个包下。一般JavaSE是"src"，JavaEE是"src/java"
     * @param packageRoot 组织名+项目名，如"com.xxx.blog"
     * @param classNameList POJO类的类名列表，如"com.xxx.Project;com.xxx.User"
     * @param interceptorList 设置是拦截器，如"com.xxx.User:user;com.xxx.User:operator"
     * @return 
     * 示例代码：
     * SsmService ssmServiceImpl = SsmServiceImpl.getInstance();
        ssmServiceImpl.create(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/nwsuaf?useSSL=true",
                "root", "password",
                "src","com.xxx", "com.xxx.Project;com.xxx.User",
                "User:user;User:operator");
     */
    public boolean create(
            String jdbcDriverClass,
            String jdbcUrl,
            String jdbcUsername,
            String jdbcPassword,
            String src,
            String packageRoot,
            String classNameList,
            String interceptorList
    ) ;
}
