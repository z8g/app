package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;

public class BaseMapperJavaTemplate extends Template {

    private String getContent(Config config) {
        StringBuilder result = new StringBuilder();
        result.append("package ")
                .append(config.getPackageRoot())
                .append(".mapper;\n\n")
                .append("import java.util.List;\n\n")
                .append("/**\n")
                .append(" * @名称 Mabatis映射接口 - 基础 Model\n")
                .append(" * @作者 ${username}\n")
                .append(" * @日期 ${datetime}\n")
                .append(" * @param <T> Model\n")
                .append(" */\n")
                .append("public interface BaseMapper <T>{\n")
                .append("    public int insert(T t);\n")
                .append("    public int update(T t);\n")
                .append("    public int delete(T t);\n")
                .append("    public List<T> list(T t);\n")
                .append("}");

        return result.toString();
    }

    private String getFilePath(Config config) {
        StringBuilder result = new StringBuilder();
        result.append(config.getSrc())
                .append("/")
                .append(config.getPackageRoot().replaceAll("\\.", "/"))
                .append("/mapper/BaseMapper.java");
        return result.toString();
    }

    
    
    @Override
    public void createFile(Config config) {
        write(getFilePath(config), getContent(config));
    }
}
