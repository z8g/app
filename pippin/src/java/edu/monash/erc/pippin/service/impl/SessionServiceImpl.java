package edu.monash.erc.pippin.service.impl;

import edu.monash.erc.pippin.service.SessionService;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    static final ConcurrentHashMap<String, String> LOCK_MAP = new ConcurrentHashMap<>();

    @Override
    public String getJobId(HttpSession session) {
        String sessionId = session.getId();
        String result = sessionId.substring(0, 9);
        return result;
    }

    @Override
    public boolean isLock(HttpSession session) {
        String id = getJobId(session);
        String lockFlag = LOCK_MAP.get(id);
        System.out.println("isLock:" + lockFlag);
        return lockFlag != null;
    }

    @Override
    public void lock(HttpSession session) {
        String id = getJobId(session);
        LOCK_MAP.put(id, id);
        System.out.println("lock:" + id);

    }

    @Override
    public void unlock(HttpSession session) {
        String id = getJobId(session);
        LOCK_MAP.remove(id, id);
        System.out.println("unlock:" + id);
    }

}
