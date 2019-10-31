package club.nwsuaf.envi.config;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Beetl视图 - 配置类
 *
 * @author zhaoxuyang
 */
@Configuration
public class BeetlConfig {

    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * @Value application.properties中取键(默认为templates)
     */
    @Value("${beetl.templatesPath:templates}")
    String templatesPath;//模板根目录 ，比如 "templates"

    /**
     * 添加共享变量
     *
     * @param conf
     * @return
     */
    @Bean
    public GroupTemplate getGroupTemplate(BeetlGroupUtilConfiguration conf) {
        GroupTemplate template = conf.getGroupTemplate();
        Map<String, Object> shared = new HashMap<>();
        shared.put("author", "zhaoxuyang");
        shared.put("appTitle", "zhaoxuyang");
        template.setSharedVars(shared);
        return template;
    }

    /**
     * 配置Beetl
     *
     * @return
     */
    @Bean
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration conf = new BeetlGroupUtilConfiguration();
        //获取Spring Boot的ClassLoader
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = BeetlConfig.class.getClassLoader();
        }
        ClasspathResourceLoader cploder
                = new ClasspathResourceLoader(loader, templatesPath);
        conf.setResourceLoader(cploder);
        conf.init();
        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
        conf.getGroupTemplate().setClassLoader(loader);
        return conf;

    }

    /**
     * Beetl视图
     *
     * @param conf
     * @return
     */
    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(
            BeetlGroupUtilConfiguration conf) {
        BeetlSpringViewResolver resolver = new BeetlSpringViewResolver();
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setOrder(0);
        resolver.setConfig(conf);
        return resolver;
    }
}
