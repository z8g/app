/*
 * Job服务接口，定义了提供给控制器的相关服务逻辑
 */
package cn.edu.nwsuaf.deep4mcpred.service;

import javax.servlet.http.HttpSession;

/**
 * @date 2019-05-22 01:09
 * @author zhaoxuyang
 */
public interface JobService {

    /**
     * 获取jobId
     *
     * @param session 用户打开浏览器会产生一个会话
     * @return 根据会话ID生成即可
     */
    public String getJobId(HttpSession session);

    public boolean isLock(HttpSession session);

    public void lock(HttpSession session);

    public void unlock(HttpSession session);

    /**
     * 运行python程序
     *
     * @param jobId
     * @param categories "A,C,D"
     * @param inputType "file" | "fixed"
     * @return
     */
    public boolean run(String jobId, String categories, String inputType);

}
