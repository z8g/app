package com.zxy97.blog.model;

/**
 * 文章类
 * @author zhaoxuyang
 */
public class Article {
    private Integer id;
    private String gmtCreate;
    private String gmtUpdate;
    private String gmtDelete;
    private String title;
    private String type;
    private Integer auth;
    private String markdown;
    private String html;
    private Integer numComment;
    private Integer numRead;
    private String authorUsername;

    public Article(){}
    
    /**
     * 写文章时的构造函数
     * @param title
     * @param type
     * @param auth
     * @param markdown
     * @param html
     * @param authorUsername 
     */
    public Article(String title, String type, Integer auth, String markdown, String html, String authorUsername) {
        this.title = title;
        this.type = type;
        this.auth = auth;
        this.markdown = markdown;
        this.html = html;
        this.authorUsername = authorUsername;
    }

    /**
     * 修改文章时的构造函数
     * @param id
     * @param title
     * @param type
     * @param auth
     * @param markdown
     * @param html
     * @param authorUsername 
     */
    public Article(Integer id, String title, String type, Integer auth, String markdown, String html, String authorUsername) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.auth = auth;
        this.markdown = markdown;
        this.html = html;
        this.authorUsername = authorUsername;
    }
    
    
    
    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", gmtDelete=" + gmtDelete + ", title=" + title + ", type=" + type + ", auth=" + auth + ", markdown=" + markdown + ", html=" + html + ", numComment=" + numComment + ", numRead=" + numRead + ", authorUsername=" + authorUsername + '}';
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Integer getNumComment() {
        return numComment;
    }

    public void setNumComment(Integer numComment) {
        this.numComment = numComment;
    }

    public Integer getNumRead() {
        return numRead;
    }

    public void setNumRead(Integer numRead) {
        this.numRead = numRead;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }
    
}
