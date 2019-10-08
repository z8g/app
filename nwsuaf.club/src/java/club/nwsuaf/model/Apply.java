
package club.nwsuaf.model;

/**
 *
 * @author Administrator
 */
public class Apply {
    private Integer id;
    private String realname;
    private String concat;
    private String content;
    private Integer projectId;//项目ID
    private String username;
    private String gmtCreate;

    @Override
    public String toString() {
        return "Apply{" + "id=" + id + ", realname=" + realname + ", concat=" + concat + ", content=" + content + ", projectId=" + projectId + ", username=" + username + ", gmtCreate=" + gmtCreate + '}';
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
