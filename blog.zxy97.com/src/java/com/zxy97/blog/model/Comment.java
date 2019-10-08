package com.zxy97.blog.model;

/**
 * 评论类
 * @author zhaoxuyang
 */
public class Comment {
    private Integer id;
    private String gmtCreate;
    private String gmtUpdate;
    private String gmtDelete;
    private String content;
    private Integer articleId;
    private String authorUsername;
    
    public Comment(){}
    public Comment(Integer id, String gmtCreate, String gmtUpdate, String gmtDelete, String content, Integer articleId, String authorUsername) {
        this.id = id;
        this.gmtCreate = gmtCreate;
        this.gmtUpdate = gmtUpdate;
        this.gmtDelete = gmtDelete;
        this.content = content;
        this.articleId = articleId;
        this.authorUsername = authorUsername;
    }

    @Override
    public String toString() {
        return "comment{" + "id=" + id + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", gmtDelete=" + gmtDelete + ", content=" + content + ", articleId=" + articleId + ", authorUsername=" + authorUsername + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(String gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getGmtDelete() {
        return gmtDelete;
    }

    public void setGmtDelete(String gmtDelete) {
        this.gmtDelete = gmtDelete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }
    
}
