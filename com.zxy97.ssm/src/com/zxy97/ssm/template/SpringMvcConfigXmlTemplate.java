package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;

public class SpringMvcConfigXmlTemplate extends Template {

    private String getContent(Config config) {

        StringBuilder result = new StringBuilder();
        String packageRoot = config.getPackageRoot();
        StringBuilder interceptorTags = new StringBuilder();
        
        config.getInterceptorList().stream().map((interceptor) -> interceptor.split(";")).forEach((interceptorConf) -> {
            for (String item : interceptorConf) {
                String[] modelAndUri = item.split(":");
                String upperModel = StringUtil.toUpperCaseFirstOne(modelAndUri[0]);
                String upperUri = StringUtil.toUpperCaseFirstOne(modelAndUri[1]);
                String lowerModel = StringUtil.toLowerCaseFirstOne(modelAndUri[0]);
                String lowerUri = StringUtil.toLowerCaseFirstOne(modelAndUri[1]);
                interceptorTags.append("        <mvc:interceptor>\n")
                        .append(format("            <mvc:mapping path=\"/%s/%s/**\"/>\n", lowerModel,lowerUri))
                        .append("            <mvc:exclude-mapping path=\"\"/>\n")
                        .append(format("            <bean class=\"%s.interceptor.%s%sInterceptor\"/>\n", packageRoot, upperModel, upperUri))
                        .append("        </mvc:interceptor>\n\n");
            }
        });

        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<beans xmlns=\"http://www.springframework.org/schema/beans\"\n")
                .append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n")
                .append("    xmlns:mvc=\"http://www.springframework.org/schema/mvc\"\n")
                .append("    xmlns:context=\"http://www.springframework.org/schema/context\"\n")
                .append("    xsi:schemaLocation=\"\n")
                .append("        http://www.springframework.org/schema/beans\n")
                .append("        http://www.springframework.org/schema/beans/spring-beans.xsd\n")
                .append("        http://www.springframework.org/schema/mvc\n")
                .append("        http://www.springframework.org/schema/mvc/spring-mvc.xsd\n")
                .append("        http://www.springframework.org/schema/context\n")
                .append("        http://www.springframework.org/schema/context/spring-context.xsd\">\n\n")
                .append(format("    <context:component-scan base-package=\"%s.mapper\" />\n", packageRoot))
                .append(format("    <context:component-scan base-package=\"%s.service\" />\n", packageRoot))
                .append(format("    <context:component-scan base-package=\"%s.controller\">\n", packageRoot))
                .append("        <context:include-filter type=\"annotation\" \n")
                .append("            expression=\"org.springframework.stereotype.Controller\"/>\n")
                .append("        <context:include-filter type=\"annotation\" \n")
                .append("            expression=\"org.springframework.web.bind.annotation.ControllerAdvice\"/>\n")
                .append("    </context:component-scan>\n\n")
                .append("    <mvc:annotation-driven />\n")
                .append("    <mvc:default-servlet-handler />\n\n")
                .append("    <!-- 视图解析器 -->\n")
                .append("    <bean class=\"org.springframework.web.servlet.view.InternalResourceViewResolver\" \n")
                .append("            id=\"internalResourceViewResolver\">\n")
                .append("        <property name=\"prefix\" value=\"/WEB-INF/jsp/\" />\n")
                .append("        <property name=\"suffix\" value=\".jsp\" />\n")
                .append("    </bean>\n\n")
                .append("    <!-- 多文件上传 -->\n")
                .append("    <bean id=\"multipartResolver\" class=\"org.springframework.web.multipart.commons.CommonsMultipartResolver\">  \n")
                .append("        <property name=\"defaultEncoding\" value=\"utf-8\"></property>   \n")
                .append("        <property name=\"maxUploadSize\" value=\"10485760000\"></property>  \n")
                .append("        <property name=\"maxInMemorySize\" value=\"40960\"></property>  \n")
                .append("   </bean>\n\n")
                .append("    <!-- configure SimpleMappingExceptionResolver -->\n")
                .append("<!--    <bean class=\"org.springframework.web.servlet.handler.SimpleMappingExceptionResolver\">\n")
                .append("        <property name=\"exceptionMappings\">\n")
                .append("            <props>\n")
                .append("               <prop key=\"java.lang.ArithmeticException\">error</prop>\n")
                .append("            </props>\n")
                .append("        </property>\n")
                .append("    </bean>-->\n\n")
                .append("    <!--配置拦截器-->\n")
                .append("    <mvc:interceptors>\n\n")
                .append(interceptorTags)
                .append("    </mvc:interceptors>\n")
                .append("</beans>");
        return result.toString();
    }

    private String getFilePath(Config config) {
        String src = config.getSrc();
        return format("%s/springmvc-servlet.xml", src);
    }

    @Override
    public void createFile(Config config) {
        write(getFilePath(config), getContent(config));
    }

}
