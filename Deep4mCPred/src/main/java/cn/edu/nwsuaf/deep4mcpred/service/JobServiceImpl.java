package cn.edu.nwsuaf.deep4mcpred.service;

import cn.edu.nwsuaf.deep4mcpred.tools.CommandUtil;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @date 2019-05-22 01:09
 * @author zhaoxuyang
 */
@Service
public class JobServiceImpl implements JobService {

    static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final ConcurrentHashMap<String, String> LOCK_MAP = new ConcurrentHashMap<>();

    @Override
    public String getJobId(HttpSession session) {
        return session.getId().substring(0, 8);
    }

    @Override
    public boolean isLock(HttpSession session) {
        String id = getJobId(session);
        String lockFlag = LOCK_MAP.get(id);
        LOG.info("isLock:" + lockFlag);
        return lockFlag != null;
    }

    @Override
    public void lock(HttpSession session) {
        String id = getJobId(session);
        LOCK_MAP.put(id, id);
        LOG.info("lock:" + id);

    }

    @Override
    public void unlock(HttpSession session) {
        String id = getJobId(session);
        LOCK_MAP.remove(id, id);
        LOG.info("unlock:" + id);
    }

    @Value("${python.exec}")
    private String pythonExec;

    @Value("${python.folder}")
    private String pythonFolder;

    @Value("${python.folder.run}")
    private String pythonFolderRun;

    @Autowired
    FileComponent fileComponent;

    @Override
    public boolean run(String jobId, String categories, String inputType) {
        boolean result = false;
        //String cmd = "python /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/Deep4mcPred.py 
//        /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/test_data.txt 
//        /home/zhaoxuyang/code/2019/20190521/test_webserver-0519/2022.txt
//        A,C,D fixed 
//        /home/zhaoxuyang/code/2019/20190521/test_webserver-0519";

        String inputFilePath = fileComponent.getSaveFile(jobId).getAbsolutePath();
        String outputFilePath = fileComponent.getOutputFile(jobId).getAbsolutePath();
        String command = String.format("%s %s %s %s %s %s %s",
                pythonExec, pythonFolderRun, inputFilePath, outputFilePath,
                categories, inputType, pythonFolder);
        LOG.info("command:" + command);
        int exitValue;
        try {
            long start = System.currentTimeMillis();
            exitValue = CommandUtil.nonBlockingRun(command);
            long end = System.currentTimeMillis();
            LOG.info(String.format("exitValue:%d; TimeMillis:%d.", exitValue, (end - start)));

            result = (exitValue == 0);
        } catch (IOException ex) {
            LOG.info(ex.toString());
            return result;
        }
        return result;
    }

}
