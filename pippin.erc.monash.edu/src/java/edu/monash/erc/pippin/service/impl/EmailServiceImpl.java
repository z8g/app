package edu.monash.erc.pippin.service.impl;

import edu.monash.erc.pippin.service.EmailService;
import static edu.monash.erc.pippin.util.EmailUtil.send;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String FORM = "xxx@mail.com";
    private static final String FORM_KEYWORD = "xxxxxx";

    @Override
    public boolean processSubmit(String email, String name, String message) {
        String subject = "【Pippin Web Server】";
        String footer = "Thanks!";
        String text = String.format("Re:%s\n\n%s\n\n%s",name,message,footer);
        return send(FORM, FORM_KEYWORD, email, subject, text);
        
    }

}
