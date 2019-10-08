package jalaxy.util;

import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author zhaoxuyang
 */
public class LoggerFactoryTest {

    /**
     * Test of getLogger method, of class LoggerUtil.
     */
    @Test
    public void testGetLogger() {
        Logger log = LoggerUtil.getDefaultLogger();
        log.finest("finest");
        log.finer("finer");
        log.fine("fine");
        log.info("info");
        log.config("config");
        log.warning("warning");
        log.severe("severe");
        //[星期三 七月 10 17:54:02 CST 2019][jalaxy.util.TestLog main]: 详细: fine
        //[星期三 七月 10 17:54:02 CST 2019][jalaxy.util.TestLog main]: 较详细: finer
        //[星期三 七月 10 17:54:02 CST 2019][jalaxy.util.TestLog main]: 非常详细: finest
        //[星期三 七月 10 17:54:02 CST 2019][jalaxy.util.TestLog main]: 信息: info
        //[星期三 七月 10 17:54:02 CST 2019][jalaxy.util.TestLog main]: 配置: config
        //[星期三 七月 10 17:54:02 CST 2019][jalaxy.util.TestLog main]: 警告: warning
        //[星期三 七月 10 17:54:02 CST 2019][jalaxy.util.TestLog main]: 严重: severe
    }

}
