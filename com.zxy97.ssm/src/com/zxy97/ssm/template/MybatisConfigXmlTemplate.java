package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import com.zxy97.ssm.util.StringUtil;

public class MybatisConfigXmlTemplate extends Template {

    
    @Override
    public void createFile(Config config) {
        write(getFilePath(config), getContent(config));
    }
    
    public String getContent(Config config) {
        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n")
                .append("<configuration>\n")
                .append("    <properties resource=\"jdbc.properties\"></properties>\n")
                .append("    <typeAliases>\n");

        config.getClassNameList().stream().forEach((className) -> {
            result.append("       <typeAlias alias=\"")
                    .append(StringUtil.toModelUpper(className))
                    .append("\" type=\"")
                    .append(config.getPackageRoot())
                    .append(".model.")
                    .append(StringUtil.toModelUpper(className))
                    .append("\"/>\n");
        });

        result.append("    </typeAliases>\n")
                .append("    <environments default=\"development\">\n")
                .append("        <environment id=\"development\">\n")
                .append("            <transactionManager type=\"JDBC\" />\n")
                .append("            <dataSource type=\"POOLED\">\n")
                .append("                <property name=\"driver\" value=\"${jdbc.driver}\" />\n")
                .append("                <property name=\"url\" value=\"${jdbc.url}\" />\n")
                .append("                <property name=\"username\" value=\"${jdbc.username}\" />\n")
                .append("                <property name=\"password\" value=\"${jdbc.password}\" />\n")
                .append("            </dataSource>\n")
                .append("        </environment>\n")
                .append("    </environments>\n")
                .append("    <mappers>\n")
                .append("        <package name=\"")
                .append(config.getPackageRoot().replaceAll("\\.", "/"))
                .append("/mapper\"/>\n")
                .append("    </mappers>\n")
                .append("    \n")
                .append("</configuration>");

        return result.toString();
    }

    public String getFilePath(Config config) {
        StringBuilder result = new StringBuilder();
        result.append(config.getSrc()).append("/mybatis-conf.xml");
      
        return result.toString();
    }

    

}
