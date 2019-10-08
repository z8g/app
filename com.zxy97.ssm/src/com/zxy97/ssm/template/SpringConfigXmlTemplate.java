package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import static java.lang.String.format;

public class SpringConfigXmlTemplate extends Template {

    private String getContent() {

        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<beans xmlns=\"http://www.springframework.org/schema/beans\"\n")
                .append("    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n")
                .append("    xsi:schemaLocation=\"http://www.springframework.org/schema/beans  \n")
                .append("        http://www.springframework.org/schema/beans/spring-beans.xsd \n")
                .append("        http://www.springframework.org/schema/util \n")
                .append("        http://www.springframework.org/schema/util/spring-util.xsd\n")
                .append("        http://www.springframework.org/schema/context\n")
                .append("        http://www.springframework.org/schema/context/spring-context.xsd\"\n")
                .append("        xmlns:util=\"http://www.springframework.org/schema/util\"\n")
                .append("        xmlns:p=\"http://www.springframework.org/schema/p\"\n")
                .append("        xmlns:context=\"http://www.springframework.org/schema/context\">\n")
                .append("    <context:component-scan base-package=\"test.SpringMVC.integrate\">\n")
                .append("        <context:exclude-filter type=\"annotation\" \n")
                .append("            expression=\"org.springframework.stereotype.Controller\"/>\n")
                .append("        <context:exclude-filter type=\"annotation\" \n")
                .append("            expression=\"org.springframework.web.bind.annotation.ControllerAdvice\"/>\n")
                .append("    </context:component-scan>\n")
                .append("</beans>");
        return result.toString();
    }

    private String getFilePath(Config config) {
        String src = config.getSrc();
        return format("%s/applicationContext.xml", src);
    }

    @Override
    public void createFile(Config config) {
        write(getFilePath(config), getContent());
    }

}
