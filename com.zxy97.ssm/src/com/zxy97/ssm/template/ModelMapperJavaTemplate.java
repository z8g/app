package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;

public class ModelMapperJavaTemplate extends Template {

    private String getContent(Config config, String className) {

        StringBuilder result = new StringBuilder();
        String model = StringUtil.toModelUpper(className);
        String packageRoot = config.getPackageRoot();

        result.append(format("package %s.mapper;\n\n", packageRoot))
                .append(format("import %s.model.%s;\n", packageRoot, model))
                .append("/**\n")
                .append(format(" * @名称 Mabatis映射接口 - %s\n", model))
                .append(" * @作者 ${author}\n")
                .append(" * @日期 ${datetime}\n")
                .append(" */\n")
                .append(format("public interface %sMapper extends BaseMapper<%s>{}\n", model, model));
        return result.toString();
    }

    private String getFilePath(Config config, String className) {
        String src = config.getSrc();
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        String model = StringUtil.toModelUpper(className);
        return format("%s/%s/mapper/%sMapper.java", src, packagePath, model);
    }

    @Override
    public void createFile(Config config) {
        config.getClassNameList().stream().forEach((className) -> {
            write(getFilePath(config, className), getContent(config, className));
        });
    }

}
