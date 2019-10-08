package com.zxy97.ssm.test;

import com.zxy97.ssm.boot.SsmService;
import com.zxy97.ssm.boot.SsmServiceImpl;

public class SsmClient {

    public static void main(String[] args) throws Exception {
        SsmService ssmService = SsmServiceImpl.getInstance();
        ssmService.create(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/nwsuaf?useSSL=true",
                "root", "password",
                "src/java", //JavaWeb项目一般是src/java，请根据实际情况选择
                "com.example",//以哪个包为根目录
                "com.zxy97.ssm.test.pojo.Project;com.zxy97.ssm.test.pojo.User",
                "User:administrator;User:operator"
        );
    }
}
