package jalaxy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author zhaoxuyang
 */
public class LoggerUtil {

    private static final String LOGGER_CONFIG_FILE = "logger.properties";
    private static final String LOGGER_NAME = "JalaxyLogger";
    private static Logger logger;

    public static Logger getDefaultLogger() {
        if (logger == null) {
            try (InputStream iStream = LoggerUtil.class.getClassLoader()
                    .getResourceAsStream(LOGGER_CONFIG_FILE)) {
                LogManager.getLogManager().readConfiguration(iStream);
            } catch (SecurityException | IOException e) {
                System.out.println("获取Logger失败！" + e);
            }
            logger = Logger.getLogger(LOGGER_NAME);
            logger.setLevel(Level.ALL);
        }
        return logger;
    }

}
