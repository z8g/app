/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.monash.erc.pippin.service;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public interface SessionService {
    
    public String getJobId(HttpSession session);
}
