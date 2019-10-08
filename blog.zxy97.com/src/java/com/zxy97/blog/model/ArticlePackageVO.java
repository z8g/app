package com.zxy97.blog.model;

import java.util.List;

public class ArticlePackageVO {

    private Integer typeId;
    private String type;
    private List<Article> articleList;

    @Override
    public String toString() {
        return "ArticlePackageVO{" + "typeId=" + typeId + ", type=" + type + ", articleList=" + articleList + '}';
    }
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Article> getArticleVOList() {
        return articleList;
    }

    public void setArticleVOList(List<Article> articleList) {
        this.articleList = articleList;
    }

}
