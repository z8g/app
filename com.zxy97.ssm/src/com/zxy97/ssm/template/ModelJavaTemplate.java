package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ModelJavaTemplate extends Template {

    private String getContent(Config config, String className) {

        StringBuilder result = new StringBuilder();
        String model = StringUtil.toModelUpper(className);
        String packageRoot = config.getPackageRoot();

        result.append(format("package %s.model;\n\n", packageRoot))
                .append("/**\n")
                .append(format(" * @名称 POJO类 - %s\n", model))
                .append(" * @作者 ${author}\n")
                .append(" * @日期 ${datetime}\n")
                .append(" */\n")
                .append(format("public class %s {\n", model));
        try {
            Class<?> clazz = Class.forName(className);
            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                String upperFieldName = StringUtil.toUpperCaseFirstOne(fieldName);
                String typeName = field.getGenericType().getTypeName();
                result.append(format("    private %s %s;\n", typeName, fieldName))
                        .append(format("    public %s get%s() {\n", typeName, upperFieldName))
                        .append(format("        return %s;\n    }\n", fieldName))
                        .append(format("    public void set%s(%s %s) {\n", upperFieldName, typeName, fieldName))
                        .append(format("        this.%s = %s;\n    }\n\n", fieldName, fieldName));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelJavaTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.append("\n}");
        return result.toString();
    }

    private String getFilePath(Config config, String className) {
        String src = config.getSrc();
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        String model = StringUtil.toModelUpper(className);
        return format("%s/%s/model/%s.java", src, packagePath, model);
    }

    @Override
    public void createFile(Config config) {
        config.getClassNameList().stream().forEach((className) -> {
            write(getFilePath(config, className), getContent(config, className));
        });
    }

}
