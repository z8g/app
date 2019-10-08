package club.nwsuaf.model.constants;

/**
 * @名称 常量
 * @作者 zhaoxuyang
 */
public interface Constants {
    
    //网站用户的身份
    public static final String USER_AUTH_USER = "用户";
    public static final String USER_AUTH_OPERATOR = "操作员";
    public static final String USER_AUTH_ADMINISTRATOR = "系统管理员";
    
    //系统发布的文章的类型
    public static final String ARTICLE_TYPE_BSTZ = "比赛通知";
    public static final String ARTICLE_TYPE_PXBG = "培训报告";
    public static final String ARTICLE_TYPE_JSXM = "竞赛项目";
    public static final String ARTICLE_TYPE_ZFXM = "在孵项目";
    public static final String ARTICLE_TYPE_XXZL = "学习资料";
    public static final String ARTICLE_TYPE_GYWM = "关于我们";
    public static final String ARTICLE_TYPE_CJWT = "常见问题";
    
    //用户发布的项目的类型
    public static final String PROJECT_TYPE_XXJS = "信息技术";
    public static final String PROJECT_TYPE_JXZZ = "机械制造";
    public static final String PROJECT_TYPE_HBNY = "环保能源";
    public static final String PROJECT_TYPE_YLJK = "医疗健康";
    public static final String PROJECT_TYPE_WHCY = "文化创意";
    public static final String PROJECT_TYPE_GYCY = "公益创业";
    public static final String PROJECT_TYPE_SHFW = "生活服务";
    public static final String PROJECT_TYPE_JY = "教育";
    public static final String PROJECT_TYPE_QT = "其他";
    
    /**
     * @描述 用户发布的项目的状态：
     * 
     * @草稿 用户创建项目后变成草稿；
     * @审核中 用户发布项目后变成审核中；
     * @草稿 操作员审核未通过，项目变成草稿；
     * @已发布 操作员审核通过，项目变成已发布。
     */
    public static final String PROJECT_STATE_CG = "草稿";
    public static final String PROJECT_STATE_SHZ = "审核中";
    public static final String PROJECT_STATE_YFB = "已发布";
}
