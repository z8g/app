package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;

public class ModelServiceJavaTemplate extends Template {

    private String getContent(Config config, String className) {

        StringBuilder result = new StringBuilder();
        String upperModel = StringUtil.toModelUpper(className);
        String lowerModel = StringUtil.toModelLower(className);
        String packageRoot = config.getPackageRoot();

        result.append(format("package %s.service;\n\n", packageRoot))
                .append(format("import %s.model.%s;\n", packageRoot, upperModel))
                .append("import java.util.List;\n\n")
                .append("/**\n")
                .append(format(" * @名称 逻辑服务 - %s\n", upperModel))
                .append(" * @作者 ${author}\n")
                .append(" * @日期 ${datetime}\n")
                .append(" */\n")
                .append(format("public interface %sService {\n", upperModel, lowerModel))
                .append(format("    boolean insert(%s %s);\n", upperModel, lowerModel))
                .append(format("    boolean delete(%s %s);\n", upperModel, lowerModel))
                .append(format("    boolean update(%s %s);\n", upperModel, lowerModel))
                .append(format("    List<%s> list(%s %s);\n", upperModel, upperModel, lowerModel))
                .append("}");
        return result.toString();
    }

    private String getFilePath(Config config, String className) {
        String src = config.getSrc();
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        String model = StringUtil.toModelUpper(className);
        return format("%s/%s/service/%sService.java", src, packagePath, model);
    }

    @Override
    public void createFile(Config config) {
        config.getClassNameList().stream().forEach((className) -> {
            write(getFilePath(config, className), getContent(config, className));
        });
    }

}
