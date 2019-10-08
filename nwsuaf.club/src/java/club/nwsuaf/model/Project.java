package club.nwsuaf.model;

/**
 * @描述 项目类
 * @作者 赵栩旸
 */
public class Project {
    private Integer id;//项目ID，主键自动递增
    private String name;//项目名称
    private String type;//项目的具体类型（信息技术/机械制造/环保能源/医疗健康/文化创意/公益创业/生活服务/教育/其他）
    private String content;//项目的内容（少于等于1000字）
    private String logo;//项目LOGO的图片地址
    private String state;//项目的状态（已发表/草稿/审核中）
    private String required;//队员要求
    private String gmtCreate;//该条记录的创建时间（yyyy-MM-dd HH:mm:ss）
    private String gmtUpdate;//该条记录的删除时间（yyyy-MM-dd HH:mm:ss）
    private String gmtEnd;//项目申请截止日期（yyyy-MM-dd）
    private String username;//作者的用户名

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", type=" + type + ", content=" + content + ", logo=" + logo + ", state=" + state + ", required=" + required + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", gmtEnd=" + gmtEnd + ", username=" + username + '}';
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
    
}
