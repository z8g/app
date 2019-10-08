package edu.monash.erc.pippin.model;

import java.math.BigDecimal;

public class Protein {

    @Override
    public String toString() {
        return "Protein{" + "id=" + id + ", pre=" + pre + ", post=" + post + ", clazz=" + clazz + '}';
    }
    
    private String id;
    private BigDecimal pre;
    private BigDecimal post;
    private String clazz;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPre() {
        return pre;
    }

    public void setPre(BigDecimal pre) {
        this.pre = pre;
    }

    public BigDecimal getPost() {
        return post;
    }

    public void setPost(BigDecimal post) {
        this.post = post;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    
    
    
}
