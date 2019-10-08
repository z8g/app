/*
 * 用户操作反馈
 * 以list形式存储在session中
 */
package club.nwsuaf.model;

import club.nwsuaf.util.DatetimeUtil;

/**
 *
 * @author Administrator
 */
public class Notification {

    public static final String TYPE_PRIMARY = "noti-primary";//天蓝色，系统色
    public static final String TYPE_SUCCESS = "noti-success";//绿色
    public static final String TYPE_INFO = "noti-info";//浅蓝色
    public static final String TYPE_WARNING = "noti-warning";//橙色
    public static final String TYPE_DANGER = "noti-danger";//红色
    public static final String TYPE_DARK = "noti-dark";//黑色
    public static final String TYPE_PINK = "noti-pink";//粉色

    private String type; //结果的类型，用于填充CSS的class
    private String content;//通知的内容
    private String gmtCreate;//时间

    public Notification() {
    }

    public Notification(String content, String type) {
        this.type = type;
        this.content = content;
        this.gmtCreate = DatetimeUtil.formatNow();
    }

    public Notification(String content) {
        this.type = TYPE_PRIMARY;
        this.gmtCreate = DatetimeUtil.formatNow();
        this.content = content;
    }

    @Override
    public String toString() {
        return "Notification{" + "type=" + type + ", content=" + content + ", gmtCreate=" + gmtCreate + '}';
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
