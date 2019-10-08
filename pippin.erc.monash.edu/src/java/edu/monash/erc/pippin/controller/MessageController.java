
package edu.monash.erc.pippin.controller;

import edu.monash.erc.pippin.service.EmailService;
import edu.monash.erc.pippin.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    
    @Autowired 
    EmailService emailService;
    
    
    @RequestMapping("/submit")
    @ResponseBody
    public String submitAction(String name,String email,String message){
        boolean success = emailService.processSubmit(email, name, message);
        if(success) {
            return "We have received your mail.";
        }
        return "We did not receive your mail.";
    }
}
