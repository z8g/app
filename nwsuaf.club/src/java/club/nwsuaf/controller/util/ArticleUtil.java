package club.nwsuaf.controller.util;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class ArticleUtil {

    public static void projectFormInit(HttpSession session) {
        String[] categories = {
            "在孵项目", "竞赛项目"
        };
        String[] types = {
            "信息技术", "机械制造", "环保能源", "医疗健康", "文化创意", "公益创业", "生活服务", "教育", "其他"
        };
        session.setAttribute("categories", categories);
        session.setAttribute("types", types);
    }

    public static void articleFormInit(HttpSession session) {
        String[] types = {
            "比赛通知", "培训报告", "竞赛项目", "在孵项目", "学习资料","关于我们","常见问题","导师信息"
        };
        session.setAttribute("types", types);
    }

}
