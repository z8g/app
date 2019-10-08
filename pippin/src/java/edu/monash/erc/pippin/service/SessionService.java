package edu.monash.erc.pippin.service;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public interface SessionService {
    
    public String getJobId(HttpSession session);
    
    public boolean isLock(HttpSession session);

    public void lock(HttpSession session);

    public void unlock(HttpSession session);
}
