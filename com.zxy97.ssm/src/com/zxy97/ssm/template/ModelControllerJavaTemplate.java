package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import static com.zxy97.ssm.template.Template.write;
import com.zxy97.ssm.util.StringUtil;
import static java.lang.String.format;

public class ModelControllerJavaTemplate extends Template {

    private String getContent(Config config, String className) {

        StringBuilder result = new StringBuilder();
        String upperModel = StringUtil.toModelUpper(className);
        String lowerModel = StringUtil.toModelLower(className);
        String packageRoot = config.getPackageRoot();

        result.append(format("package %s.controller;\n\n", packageRoot))
                .append(format("import %s.model.%s;\n", packageRoot, upperModel))
                .append(format("import %s.service.%sService;\n", packageRoot, upperModel))
                .append("import java.util.List;\n")
                .append("import javax.servlet.http.HttpSession;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.stereotype.Controller;\n")
                .append("import org.springframework.web.bind.annotation.RequestMapping;\n\n")
                .append("@Controller\n")
                .append(format("@RequestMapping(value = \"/%s\")\n", lowerModel))
                .append(format("public class %sController {\n\n", upperModel))
                .append("    @Autowired\n")
                .append(format("    %sService %sService;\n\n", upperModel, lowerModel))
                .append("    @RequestMapping(value = \"/insert.form\")\n")
                .append("    public String insertForm() {\n")
                .append(format("        return \"%sInsertForm\";\n", upperModel))
                .append("    }\n\n")
                .append("    @RequestMapping(value = \"/insert.action\")\n")
                .append(format("    public String insertAction(%s %s, HttpSession session) {\n", upperModel, lowerModel))
                .append(format("        boolean success = %sService.insert(%s);\n", lowerModel, lowerModel))
                .append("        if (success) {\n")
                .append("            return \"\";\n")
                .append("        } else {\n")
                .append("            return \"\";\n")
                .append("        }\n")
                .append("    }\n\n")
                .append("    @RequestMapping(value = \"/update.form\")\n")
                .append("    public String updateForm() {\n")
                .append(format("        return \"%sUpdateForm\";\n", upperModel))
                .append("    }\n\n")
                .append("    @RequestMapping(value = \"/update.action\")\n")
                .append(format("    public String updateAction(%s %s, HttpSession session) {\n", upperModel, lowerModel))
                .append(format("        boolean success = %sService.update(%s);\n\n", lowerModel, lowerModel))
                .append("        if (success) {\n")
                .append("            return \"\";\n")
                .append("        } else {\n")
                .append("            return \"\";\n")
                .append("        }\n")
                .append("    }\n\n")
                .append("    @RequestMapping(value = \"/delete.action\")\n")
                .append(format("    public String deleteAction(%s %s, HttpSession session) {\n", upperModel, lowerModel))
                .append(format("        boolean success = %sService.delete(%s);\n\n", lowerModel, lowerModel))
                .append("        if (success) {\n")
                .append("            return \"\";\n")
                .append("        } else {\n")
                .append("            return \"\";\n")
                .append("        }\n")
                .append("    }\n\n")
                .append("    @RequestMapping(value = \"/list.action\")\n")
                .append(format("    public String listAction(%s %s, HttpSession session) {\n", upperModel, lowerModel))
                .append(format("        List<%s> %sList = %sService.list(%s);\n", upperModel, lowerModel, lowerModel, lowerModel))
                .append(format("        session.setAttribute(\"%sList\", %sList);\n", lowerModel, lowerModel))
                .append("        return \"\";\n")
                .append("    }\n")
                .append("}\n");
        return result.toString();
    }

    private String getFilePath(Config config, String className) {
        String src = config.getSrc();
        String packagePath = StringUtil.toPackagePath(config.getPackageRoot());
        String upperModel = StringUtil.toModelUpper(className);
        return format("%s/%s/controller/%sController.java", src, packagePath, upperModel);
    }

    @Override
    public void createFile(Config config) {
        config.getClassNameList().stream().forEach((className) -> {
            write(getFilePath(config, className), getContent(config, className));
        });
    
    }

}
