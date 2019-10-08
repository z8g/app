/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */ 
package net.zhaoxuyang.blog.model;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文章类
 *
 * @author zhaoxuyang
 */
public class Article {
final static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private Integer id;//文章ID
    private String title;//文章标题
    private String category;//文章类别
    private String markdown;// 文章源码
    private String summary;//自动生成的摘要/概要
    private String tags;//文章标签集
    private Integer auth;//文章权限
    private Date gmtCreate;//创建时间
    private Date gmtUpdate;//修改时间
    private Integer userId;//修改时间
    private String type;//文章类型

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", title=" + title + ", category=" + category + ", markdown=" + markdown + ", summary=" + summary + ", tags=" + tags + ", auth=" + auth + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", userId=" + userId + ", type=" + type + '}';
    }

    public String getType() {
         return type;  
    }
    

    public void setType(String type) {
        this.type = type;
    }
    
    final static int LENGTH_SUMMARY = 100;
    final static String TAG_SUMMARY_START = "<!--summary";
    final static String TAG_END = "-->";

    /**
     * 生成摘要 1. 获取标记内的字符 ２.　没有标记则去掉一些字符，取前面ｎ个
     *
     * @return
     */
    public String createSummary() {
        LOG.info(" public String createSummary");
        String result;//返回结果
        boolean workFlag = false;//是否有标记
        StringBuilder summaryBuffer = new StringBuilder();

        try (Scanner input = new Scanner(markdown)) {
            while (input.hasNext()) {
                String line = input.nextLine().trim();

                //遇到<!--summary标记,进入读取状态
                if (line.startsWith(TAG_SUMMARY_START)) {
                    workFlag = true;
                    continue;
                }

                //遇到-->标记且处于读取状态
                if (line.startsWith(TAG_END) && workFlag) {
                    break;
                }

                //具备<!--summary标记，每行读取
                if (workFlag) {
                    summaryBuffer.append(line).append("<br />");
                }
            }
        }

        /**
         * 如果读取过，则返回summaryBuffer 如果没有读取过，则替换正文一些特殊字符来生成概要
         */
        if (workFlag) {
            result = summaryBuffer.toString();
        } else {
            String str = markdown.replaceAll("<", "")
                    .replaceAll(">", "")
                    .replaceAll("#", "")
                    .replaceAll("`", "");

            /**
             * 小于等于100（LENGTH_SUMMARY）则返回全部 否则返回前100个字符
             */
            if (str.length() <= LENGTH_SUMMARY) {
                result = str;
            } else {
                result = str.substring(0, LENGTH_SUMMARY);
            }
        }
        summary = result;
        return result;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category.trim();
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        LOG.info(" public void setTags");
        LOG.info(" tags=" +  tags);
        String[] strs = tags.split("\\|");
        java.util.Set<String> set = new java.util.HashSet<>();
        for (String str : strs) {
            String trim = str.trim();
            if (trim.length() > 0) {
                set.add(trim);
            }
        }
        StringBuilder buffer = new StringBuilder();
        set.forEach((str) -> {
            buffer.append(str).append("|");
        });
        String temp = buffer.toString();
        this.tags = temp.substring(0, temp.length() - "|".length());
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

}
