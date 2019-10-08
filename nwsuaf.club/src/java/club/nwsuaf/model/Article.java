package club.nwsuaf.model;

/**
 * @描述 文章类
 * @作者 赵栩旸
 */
public class Article {
    private Integer id;//文章ID，主键自动递增
    private String title;//文章标题
    private String type;//具体类型（比赛通知/培训报告/竞赛项目/在孵项目/学习资料/关于我们/FAQ）
    private String content;//文章的内容（富文本形式）
    private String gmtCreate;//该条记录的创建时间（yyyy-MM-dd HH:mm:ss）
    private String gmtUpdate;//该条记录的删除时间（yyyy-MM-dd HH:mm:ss）
    private String username;//作者的用户名

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", title=" + title + ", type=" + type + ", content=" + content + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", username=" + username + '}';
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
