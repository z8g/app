package com.zxy97.ssm.boot;

import com.zxy97.ssm.config.Config;
import com.zxy97.ssm.template.AbstractFactory;
import com.zxy97.ssm.template.BaseMapperJavaTemplate;
import com.zxy97.ssm.template.DatetimeUtilJavaTemplate;
import com.zxy97.ssm.template.JdbcPropertiesTemplate;
import com.zxy97.ssm.template.ModelControllerJavaTemplate;
import com.zxy97.ssm.template.ModelIntercetorJavaTemplate;
import com.zxy97.ssm.template.ModelJavaTemplate;
import com.zxy97.ssm.template.ModelMapperJavaTemplate;
import com.zxy97.ssm.template.ModelMapperXmlTemplate;
import com.zxy97.ssm.template.ModelServiceImplJavaTemplate;
import com.zxy97.ssm.template.ModelServiceJavaTemplate;
import com.zxy97.ssm.template.MybatisConfigXmlTemplate;
import com.zxy97.ssm.template.MybatisUtilJavaTemplate;
import com.zxy97.ssm.template.SpringConfigXmlTemplate;
import com.zxy97.ssm.template.SpringMvcConfigXmlTemplate;
import com.zxy97.ssm.template.TemplateFactory;
import com.zxy97.ssm.template.WebXmlTemplate;
import com.zxy97.ssm.util.StringUtil;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SsmServiceImpl implements SsmService {

    private static final long serialVersionUID = -2015014117L;

    private static final SsmServiceImpl instance = new SsmServiceImpl();

    private SsmServiceImpl() {
    }

    public static SsmServiceImpl getInstance() {
        System.out.println("------------------------------------------------");
        System.out.println("     SSM实例已创建！ |  主页：https://zxy97.com  ");
        System.out.println("------------------------------------------------");
        return instance;
    }

    @Override
    public boolean create(
            String jdbcDriverClass,
            String jdbcUrl,
            String jdbcUsername,
            String jdbcPassword,
            String src,
            String packageRoot,
            String classNameList,
            String interceptorList
    ) {
        boolean result = false;
        try {

            //加载配置信息
            Config.ConfigBuilder builder = new Config.ConfigBuilder();
            Config config = builder.jdbcDriverClass(jdbcDriverClass)
                    .jdbcUrl(jdbcUrl)
                    .jdbcUsername(jdbcUsername)
                    .jdbcPassword(jdbcPassword)
                    .src(src)
                    .packageRoot(packageRoot)
                    .classNameList(classNameList)
                    .interceptorList(interceptorList)
                    .build();
            
            
            System.out.format("> 加载配置信息：\n%s\n", config);

            createDirectories(config);
            createTemlateFiles(config);

            result = true;
        } catch (IOException ex) {
            Logger.getLogger(MethodHandles.lookup().lookupClass().getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * 创建包
     *
     * @param config
     * @throws IOException
     */
    private void createDirectories(Config config) throws IOException {
        System.out.format("> 创建包：%s\n", config.getPackageRoot());
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        
        Files.createDirectories(Paths.get(String.format("%s/%s/%s", config.getSrc(), packagePath, "mapper")));
        Files.createDirectories(Paths.get(String.format("%s/%s/%s", config.getSrc(), packagePath, "service/impl")));
        Files.createDirectories(Paths.get(String.format("%s/%s/%s", config.getSrc(), packagePath, "model")));
        Files.createDirectories(Paths.get(String.format("%s/%s/%s", config.getSrc(), packagePath, "controller")));
        Files.createDirectories(Paths.get(String.format("%s/%s/%s", config.getSrc(), packagePath, "util")));
        Files.createDirectories(Paths.get(String.format("%s/%s/%s", config.getSrc(), packagePath, "interceptor")));
    }

    private void createTemlateFiles(Config config) {
        AbstractFactory templateFactory = new TemplateFactory();
        templateFactory.createTemplate(MybatisConfigXmlTemplate.class).createFile(config);
        templateFactory.createTemplate(JdbcPropertiesTemplate.class).createFile(config);
        templateFactory.createTemplate(MybatisUtilJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(DatetimeUtilJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(BaseMapperJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(ModelJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(ModelMapperJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(ModelMapperXmlTemplate.class).createFile(config);
        templateFactory.createTemplate(ModelServiceJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(ModelServiceImplJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(ModelControllerJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(ModelIntercetorJavaTemplate.class).createFile(config);
        templateFactory.createTemplate(SpringMvcConfigXmlTemplate.class).createFile(config);
        templateFactory.createTemplate(SpringConfigXmlTemplate.class).createFile(config);
        templateFactory.createTemplate(WebXmlTemplate.class).createFile(config);
    }

}
