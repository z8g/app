///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.zhaoxuyang.util;
//
//import java.io.File;
//import java.io.IOException;
//import org.beetl.core.Configuration;
//import org.beetl.core.GroupTemplate;
//import org.beetl.core.Template;
//import org.beetl.core.resource.ClasspathResourceLoader;
//import org.beetl.core.resource.FileResourceLoader;
//import org.beetl.core.resource.StringTemplateResourceLoader;
//
///**
// * @see http://ibeetl.com/guide/#beetl
// * @author zhaoxuyang
// */
//public class BeetlTest {
//
//    public static void main(String[] args) throws IOException {
//
//    }
//
//    public String method1(String path) throws IOException {
//        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
//        Configuration cfg = Configuration.defaultConfiguration();
//        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
//        Template t = gt.getTemplate("hello,${name}");
//        t.binding("name", "beetl");
//        String str = t.render();
//        System.out.println(str);
//        return str;
//    }
//
//    /**
//     * @描述 更通常情况下，模板资源是以文件形式管理的，
//     * 集中放在某一个文件目录下（如webapp的模板根目录就可能是WEB-INF/template里），
//     * 因此，可以使用FileResourceLoader来加载模板实例，如下代码：
//     *
//     * @param path
//     * @return
//     * @throws IOException
//     */
//    public String method2(String path) throws IOException {
//        String root = System.getProperty("user.dir") + File.separator + "template";
//        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
//        Configuration cfg = Configuration.defaultConfiguration();
//        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
//        Template t = gt.getTemplate(path);
//        String str = t.render();
//        System.out.println(str);
//        return str;
//    }
//
//    
//    /**
//     * 
//     * @param classPath org/beetl/sample/s01/
//     * @param path 1.txt
//     * @return
//     * @throws IOException 
//     */
//    public String method3(String classPath, String path) throws IOException {
//        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(classPath);
//        Configuration cfg = Configuration.defaultConfiguration();
//        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
//        Template t = gt.getTemplate(path);
//        String str = t.render();
//        System.out.println(str);
//        return str;
//    }
//
//}
