package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ModelMapperXmlTemplate extends Template {

    //去掉最后一个逗号
    private String removeCommas(StringBuilder builder) {
        return builder.substring(0, builder.length() - 1);
    }

    private String getContent(Config config, String className) {

        StringBuilder result = new StringBuilder();
        String upperModel = StringUtil.toModelUpper(className);
        String lowerModel = StringUtil.toModelLower(className);
        String packageRoot = config.getPackageRoot();

        StringBuilder resultMapResult = new StringBuilder();
        StringBuilder insertInto = new StringBuilder();
        StringBuilder insertValues = new StringBuilder();
        StringBuilder updateSet = new StringBuilder();
        StringBuilder selectSelect = new StringBuilder();
        StringBuilder selectWhereIf = new StringBuilder();
        try {
            Class<?> clazz = Class.forName(className);
            for (Field field : clazz.getDeclaredFields()) {
                String fieldName = field.getName();
                resultMapResult.append(format("        <result property=\"%s\" column=\"%s\"/>\n", fieldName, fieldName));
                insertInto.append(format(" %s ,", fieldName));
                insertValues.append(format(" #{ %s },", fieldName, fieldName));
                updateSet.append(format(" %s =#{ %s },", fieldName, fieldName));
                selectSelect.append(format(" %s,", fieldName));
                selectWhereIf.append(format("            <if test=\"%s != null\"> and %s = #{ %s } </if>\n", fieldName, fieldName, fieldName));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelJavaTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }

        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n")
                .append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n\n")
                .append(format("<mapper namespace=\"%s.mapper.%sMapper\">\n", packageRoot, upperModel))
                .append(format("<resultMap id=\"%sResultMap\" type=\"%s\">\n", upperModel, upperModel))
                .append("<!-- <id property=\"id\" column=\"id\"/> -->\n")
                .append(resultMapResult)
                .append("    </resultMap>\n\n")
                .append(format("    <insert id=\"insert\" parameterType=\"%s\">\n", upperModel))
                .append(format("        insert into %s(%s) \n", lowerModel, removeCommas(insertInto)))
                .append(format("        values(%s)\n", removeCommas(insertValues)))
                .append("    </insert>\n\n")
                .append(format("    <update id=\"update\" parameterType=\"%s\">\n", upperModel))
                .append(format("        update %s\n", lowerModel))
                .append(format("        set %s\n", removeCommas(updateSet)))
                .append("        -- where id=#{id}\n")
                .append("    </update>\n\n")
                .append(format("    <delete id=\"delete\" parameterType=\"%s\">\n", upperModel))
                .append(format("        delete from %s\n", lowerModel))
                .append("        -- where id=#{id}\n")
                .append("    </delete>\n\n")
                .append(format("    <select id=\"list\" resultMap=\"%sResultMap\" parameterType=\"%s\">\n", upperModel, upperModel))
                .append(format("        select %s\n", removeCommas(selectSelect)))
                .append(format("        from %s\n", lowerModel))
                .append("        <where>\n")
                .append(removeCommas(selectWhereIf))
                .append("        </where>\n")
                .append("        -- order by gmt_update desc\n")
                .append("    </select>\n")
                .append("</mapper>\n");
        return result.toString();
    }

    private String getFilePath(Config config, String className) {
        String src = config.getSrc();
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        String model = StringUtil.toModelUpper(className);
        return format("%s/%s/mapper/%sMapper.xml", src, packagePath, model);
    }

    @Override
    public void createFile(Config config) {
        config.getClassNameList().stream().forEach((className) -> {
            write(getFilePath(config, className), getContent(config, className));
        });
    }

}
