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
public class AdminArticleCommentReply {
    private Integer id;
    private Integer comment_id;
    private Integer reply_id;
    private Integer user_id;
    
    private String content;
    
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
     * @return the comment_id
     */
    public Integer getComment_id() {
        return comment_id;
    }

    /**
     * @param comment_id the comment_id to set
     */
    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    /**
     * @return the reply_id
     */
    public Integer getReply_id() {
        return reply_id;
    }

    /**
     * @param reply_id the reply_id to set
     */
    public void setReply_id(Integer reply_id) {
        this.reply_id = reply_id;
    }

    /**
     * @return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
