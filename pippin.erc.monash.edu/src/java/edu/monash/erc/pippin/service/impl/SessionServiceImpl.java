package edu.monash.erc.pippin.service.impl;

import edu.monash.erc.pippin.service.SessionService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public String getJobId(HttpSession session) {
        String result;
        String sessionId = session.getId();
        result = sessionId.substring(0,9);
        return result;
    }
    
}
