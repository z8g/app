package cn.edu.nwsuaf.deep4mcpred.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 命令行-工具类
 *
 * @author zhaoxuyang
 */
public class CommandUtil {

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * 执行命令，返回屏幕上打印的信息
     *
     * @param command 命令
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static String blockingRun(String command) throws IOException, InterruptedException {
        String result;//要返回的结果

        System.out.printf("[command]%s\n", command);
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
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        int waitFor = process.waitFor();
        int exitValue = process.exitValue();
        if (exitValue != 0) {
            //失败时返回等待码和退出码
            result = String.format("[ERROR]process.waitFor=%d, process.exitValue=%d\n",
                    waitFor, exitValue);
            return "false";
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

        return result;
    }

    /**
     * shel 执行Shell脚本
     *
     * @param shellFilePath Shell脚本的绝对路径
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static String sh(String shellFilePath) throws IOException, InterruptedException {
        String command = String.format("sh -c %s", shellFilePath);
        String result = blockingRun(command);
        return result;
    }

    public static int nonBlockingRun(String command) throws IOException {
        int queryExitValue;
        Process process = Runtime.getRuntime().exec(command);
        final InputStream infoInputStream = process.getInputStream();
        new Thread(() -> {
            BufferedReader infoReader = new BufferedReader(
                    new InputStreamReader(infoInputStream));
            try {
                String infoLine;
                while ((infoLine = infoReader.readLine()) != null) {
                    System.out.println(infoLine);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }).start();

        InputStream errorInputStream = process.getErrorStream();
        BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(errorInputStream));
        StringBuilder errorBuffer = new StringBuilder();
        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
            errorBuffer.append(errorLine);
        }
        while (errorReader.readLine() != null) {
        };
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        queryExitValue = process.exitValue();
        System.out.println("exitValue:" + process.exitValue());

        return queryExitValue;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //CommandUtil.run("java -version");
        //CommandUtil.run(" ls -l ");
        //CommandUtil.sh("/home/zhaoxuyang/Desktop/test.sh");

        String cmd = "python /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/Deep4mcPred.py /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/test_data.txt /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/2022.txt A,C,D fixed /home/zhaoxuyang/code/2019/20190521/test_webserver-0519";
        System.out.println(nonBlockingRun(cmd));

    }
}
