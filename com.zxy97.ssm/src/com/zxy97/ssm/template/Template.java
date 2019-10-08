/**
 * 对于每一个样板文件，都有创建文件的方法 个人样板文件在创建文件时，需要使用Model（字符串）去替换其中对应的内容，写出新文件
 */
package com.zxy97.ssm.template;

import com.zxy97.ssm.config.Config;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class Template {

    public abstract void createFile(Config config);

    public static void write(String filePath, String content) {
        System.out.format("> 开始创建文件：%s\n", filePath);
        Path targetPath = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(targetPath,
                StandardCharsets.UTF_8,StandardOpenOption.CREATE,StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            writer.write(content);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
