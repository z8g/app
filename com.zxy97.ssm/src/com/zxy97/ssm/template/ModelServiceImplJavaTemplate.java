package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;

public class ModelServiceImplJavaTemplate extends Template {

    private String getContent(Config config, String className) {

        StringBuilder result = new StringBuilder();
        String upperModel = StringUtil.toModelUpper(className);
        String lowerModel = StringUtil.toModelLower(className);
        String packageRoot = config.getPackageRoot();

        result.append(format("package %s.service.impl;\n\n", packageRoot))
                .append(format("import %s.mapper.%sMapper;\n", packageRoot, upperModel))
                .append(format("import %s.model.%s;\n", packageRoot, upperModel))
                .append(format("import %s.service.%sService;\n", packageRoot, upperModel))
                .append(format("import %s.util.DatetimeUtil;\n", packageRoot))
                .append(format("import %s.util.MyBatisUtil;\n", packageRoot))
                .append("import java.util.List;\n")
                .append("import org.apache.ibatis.session.SqlSession;\n")
                .append("import org.springframework.stereotype.Service;\n\n")
                .append("/**\n")
                .append(format(" * @名称 逻辑服务实现类 - %s\n", upperModel))
                .append(" * @作者 ${author}\n")
                .append(" * @日期 ${datetime}\n")
                .append(" */\n")
                .append("@Service\n")
                .append(format("public class %sServiceImpl implements %sService {\n\n", upperModel, upperModel))
                .append("    @Override\n")
                .append(format("    public boolean insert(%s %s) {\n", upperModel, lowerModel))
                .append("        boolean result;\n")
                .append("        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {\n")
                .append(format("            %sMapper %sMapper = sqlSession.getMapper(%sMapper.class);\n", upperModel, lowerModel, upperModel))
                .append(format("            //%s.setGmtCreate(DatetimeUtil.formatNow());//插入时添加创建时间\n", lowerModel))
                .append(format("            int n = %sMapper.insert(%s);\n", lowerModel, lowerModel))
                .append("            sqlSession.commit();\n")
                .append("            result = n > 0;\n")
                .append("        }\n")
                .append("        return result;\n")
                .append("    }\n\n")
                .append("    @Override\n")
                .append(format("    public boolean delete(%s %s) {\n", upperModel, lowerModel))
                .append("        boolean result;\n")
                .append("        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {\n")
                .append(format("            %sMapper %sMapper = sqlSession.getMapper(%sMapper.class);\n", upperModel, lowerModel, upperModel))
                .append(format("            %s search = new %s();\n", upperModel, upperModel))
                .append(format("            //search.setId(%sId);//根据ID删除\n", lowerModel))
                .append(format("            int n = %sMapper.delete(search);\n", lowerModel))
                .append("            sqlSession.commit();\n")
                .append("            result = n > 0;\n")
                .append("        }\n")
                .append("        return result;\n")
                .append("    }\n\n")
                .append("    @Override\n")
                .append(format("    public boolean update(%s %s) {\n", upperModel, lowerModel))
                .append("        boolean result;\n")
                .append("        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {\n")
                .append(format("            %sMapper %sMapper = sqlSession.getMapper(%sMapper.class);\n", upperModel, lowerModel, upperModel))
                .append(format("            %s search = new %s();\n", upperModel, upperModel))
                .append("            //search.setGmtUpdate(DatetimeUtils.formatNow());//修改时添加修改时间\n")
                .append(format("            int n = %sMapper.delete(search);\n", lowerModel))
                .append("            sqlSession.commit();\n")
                .append("            result = n > 0;\n")
                .append("        }\n")
                .append("        return result;\n")
                .append("    }\n\n")
                .append("    @Override\n")
                .append(format("    public List<%s> list(%s %s) {\n", upperModel, upperModel, lowerModel))
                .append(format("        List<%s> result;\n", upperModel))
                .append("        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {\n")
                .append(format("            %sMapper %sMapper = sqlSession.getMapper(%sMapper.class);\n", upperModel, lowerModel, upperModel))
                .append(format("            %s search = new %s();\n", upperModel, upperModel))
                .append("            //search.setUsername(username);\n")
                .append(format("            result = %sMapper.list(search);\n", lowerModel))
                .append("            sqlSession.commit();\n")
                .append("        }\n")
                .append("        return result;\n")
                .append("    }\n\n}");
        return result.toString();
    }

    private String getFilePath(Config config, String className) {
        String src = config.getSrc();
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        String upperModel = StringUtil.toModelUpper(className);
        return format("%s/%s/service/impl/%sServiceImpl.java", src, packagePath, upperModel);
    }

    @Override
    public void createFile(Config config) {
        config.getClassNameList().stream().forEach((className) -> {
            write(getFilePath(config, className), getContent(config, className));
        });
    }

}
