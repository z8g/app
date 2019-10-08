package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;

public class DatetimeUtilJavaTemplate extends Template {

    private String getContent(Config config) {

        StringBuilder result = new StringBuilder();
        String packageRoot = config.getPackageRoot();

        result.append(format("package %s.util;\n\n", packageRoot))
                .append("import java.text.SimpleDateFormat;\n")
                .append("import java.util.Date;\n\n")
                .append("/**\n")
                .append(" * @名称 工具 - 日期时间\n")
                .append(" * @作者 zhaoxuyang\n")
                .append(" * @版本 v1.0\n")
                .append(" * @日期 2019-01-01\n")
                .append(" */\n")
                .append("public class DatetimeUtil {\n\n")
                .append("    private DatetimeUtil() {}\n\n")
                .append("    private static final SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n")
                .append("    private static final SimpleDateFormat sdfFileName = new SimpleDateFormat(\"yyyyMMddHHmmss\");\n\n")
                .append("    /**\n")
                .append("     * @描述 获取当前系统时间并格式化\n")
                .append("     * @return yyyy-MM-dd HH:mm:ss 形式的当前系统时间\n")
                .append("     */\n")
                .append("    public static String formatNow() {\n")
                .append("        return sdf.format(new Date());\n")
                .append("    }\n\n")
                .append("    public static String formatDateTime(long length) {\n")
                .append("        return sdf.format(new Date(length));\n")
                .append("    }\n\n")
                .append("    public static String formatFileName(String fileName) {\n")
                .append("        int lastIndex = fileName.lastIndexOf(\".\");\n")
                .append("        String e = lastIndex < 0  ? \"\" : fileName.substring(lastIndex);\n")
                .append("        return String.format(\"%s%s\", sdfFileName.format(new Date()), e);\n")
                .append("    }\n\n")
                .append("}");
        return result.toString();
    }

    private String getFilePath(Config config) {
        String src = config.getSrc();
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        return format("%s/%s/util/DatetimeUtil.java", src, packagePath);
    }

    @Override
    public void createFile(Config config) {
        write(getFilePath(config), getContent(config));
    }

}
