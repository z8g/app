package club.nwsuaf.envi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 命令行-工具类
 *
 * @author zhaoxuyang
 */
@Component
public class CommandUtil {

    Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * 执行一条命令（如果终端输出的信息过多，会阻塞Java程序）
     *
     * @param command 命令
     * @return 终端打印的字符串
     * @throws IOException
     * @throws InterruptedException
     */
    public String exec(String command) throws IOException, InterruptedException {
        String result = null;
        LOG.info("[exec command] {}", command);
        String[] commands = command.split(" ");
        List<String> commandList = new ArrayList<>();

        //删除多余的空格
        for (String s : commands) {
            if (s != null && s.length() > 0) {
                commandList.add(s);
            }
        }

        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        try (InputStream inputStream = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr)) {

            int waitFor = process.waitFor();
            int exitValue = process.exitValue();
            if (exitValue != 0) {
                //失败时返回等待码和退出码
                String template = "[exec command] waitFor={}, exitValue={}";
                LOG.error(template, waitFor, exitValue);
                return result;
            } else {
                //成功时返回输出流的信息
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                    System.out.println(line);
                }
                result = output.toString();
            }
        }
        process.destroy();
        return result;
    }
}
