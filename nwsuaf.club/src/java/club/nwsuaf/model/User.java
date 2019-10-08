package club.nwsuaf.model;

/**
 * @名称 用户类
 * @作者 赵栩旸
 */
public class User {

    private String username;//用户名（由字母或数字组成）
    private String password;//密码（由字母或数字组成）
    private String email;//电子邮箱
    private String realname;//真实姓名
    private String auth;//用户身份（用户|操作员|系统管理员）
    private String gmtCreate;//该条记录的创建时间
    private String info;//用户简介，1000个字符
    private String logo;//头像地址，255个字符
    private String birth;//出生日期 （yyyy-MM-dd）
    private String tel;//手机号码（11位数字）
    private String gender;//性别（男|女|保密）

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", email=" + email + ", realname=" + realname + ", auth=" + auth + ", gmtCreate=" + gmtCreate + ", info=" + info + ", logo=" + logo + ", birth=" + birth + ", tel=" + tel + ", gender=" + gender + '}';
    }

    public String toEmail() {
        return String.format("{账号:%s,密码:%s}", username,password);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}
