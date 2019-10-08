/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.zhaoxuyang.blog.model;

import java.util.Date;

/**
 *
 * @author wangjingyu
 */
public class AdminVisitArticle {
    private Integer id;
    private Integer visitor_id;
    private Integer article_id;
    private Date gmt_create;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the visitor_id
     */
    public Integer getVisitor_id() {
        return visitor_id;
    }

    /**
     * @param visitor_id the visitor_id to set
     */
    public void setVisitor_id(Integer visitor_id) {
        this.visitor_id = visitor_id;
    }

    /**
     * @return the article_id
     */
    public Integer getArticle_id() {
        return article_id;
    }

    /**
     * @param article_id the article_id to set
     */
    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    /**
     * @return the gmt_create
     */
    public Date getGmt_create() {
        return gmt_create;
    }

    /**
     * @param gmt_create the gmt_create to set
     */
    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }
    
    
}
