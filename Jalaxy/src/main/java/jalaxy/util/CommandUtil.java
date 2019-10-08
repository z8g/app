package jalaxy.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 命令行-工具类
 *
 * @author zhaoxuyang
 */
public class CommandUtil {

    static final Logger LOG = LoggerUtil.getDefaultLogger();

    /**
     * shel 执行Shell脚本
     *
     * @param shellFilePath Shell脚本的绝对路径
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean runShellFile(String shellFilePath)
            throws IOException, InterruptedException {
        String command = String.format("sh %s", shellFilePath);
        int returnValue = nonBlockingRun(command);
        return (returnValue == 0);
    }

    /**
     * shel 执行Shell脚本
     *
     * @param shellScript Shell脚本内容
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static boolean runShellScript(String shellScript)
            throws IOException, InterruptedException {
        String uuid = UUID.randomUUID().toString();
        LOG.log(Level.INFO, "UUID:{0}", uuid);

        File shellFile = new File("/tmp/shell", uuid);
        shellFile.getParentFile().mkdirs();
        LOG.log(Level.INFO, "创建脚本：{0}", shellFile.getAbsolutePath());

        if (!shellFile.createNewFile()) {
            return false;
        }

        try (OutputStream fos = new FileOutputStream(shellFile)) {
            fos.write(shellScript.getBytes());
        }

        boolean returnValue = runShellFile(shellFile.getAbsolutePath());

        if (!returnValue) {
            shellFile.delete();
        }

        return returnValue;
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

        String cmd = "ls -l";
        System.out.println(nonBlockingRun(cmd));
        System.out.println(nonBlockingRun("pwd"));

        System.out.println(runShellFile("/home/zhaoxuyang/Desktop/1.sh"));

        String shellScript = "ls -l > /home/zhaoxuyang/Desktop/2.txt";
        System.out.println(runShellScript(shellScript));

    }
}
