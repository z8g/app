package club.nwsuaf.controller;

import club.nwsuaf.controller.util.ArticleUtil;
import club.nwsuaf.controller.util.NotificationUtil;
import club.nwsuaf.model.Article;
import club.nwsuaf.model.Notification;
import club.nwsuaf.model.User;
import club.nwsuaf.service.ArticleService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/operator/article")
public class OperatorArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/create")
    public String createForm(HttpSession session) {
        ArticleUtil.articleFormInit(session);
        NotificationUtil.add(session, String.format("操作员开始创建文章"));
        return "OperatorArticleCreate";
    }

    /**
     * 文章创建事件
     *
     * @param article
     * @param session
     * @return
     */
    @RequestMapping(value = "/create.action")
    public String createAction(Article article, HttpSession session) {
        User user = (User) session.getAttribute("user");
        article.setUsername(user.getUsername());
        boolean success = articleService.operatorInsert(article);
        if (success) {
            return String.format("redirect:/operator/article/list", article.getId());
        } else {
            NotificationUtil.add(session, String.format("文章创建失败！"), Notification.TYPE_DARK);
            return "OperatorArticleCreate";
        }
    }

    /**
     * 操作员 - 文章列表
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/list")
    public String articleList(HttpSession session) {
        List<Article> articleList = articleService.list();
        session.setAttribute("articleList", articleList);
        return "OperatorArticleList";
    }

    /**
     * 文章修改页面
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/update")
    public String updateForm(Integer id, HttpSession session) {
        Article article = articleService.get(id);
        ArticleUtil.articleFormInit(session);
        session.setAttribute("updateArticle", article);
        NotificationUtil.add(session, String.format("开始修改文章[%d]", id));
        return "OperatorArticleUpdate";

    }

    /**
     * 文章修改事件
     *
     * @param newArticle
     * @param session
     * @return
     */
    @RequestMapping(value = "/update.action")
    public String updateAction(Article newArticle, HttpSession session) {
        Integer id = newArticle.getId();//ID是一样的
        Article article = articleService.get(id);
        article.setContent(newArticle.getContent());
        article.setTitle(newArticle.getTitle());
        article.setType(newArticle.getType());
        boolean success = articleService.operatorUpdate(article);
        if (success) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的文章信息修改成功！", id));
            return "redirect:/operator/article/list";
        } else {
            NotificationUtil.add(session, String.format("编号为 [%s] 的文章信息修改失败！", id), Notification.TYPE_DANGER);
            return "redirect:/operator/article/update?id=" + article.getId();
        }
    }

    /**
     * 操作员-文章删除事件
     *
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/delete.action")
    public String deleteAction(Integer id, HttpSession session) {
        Article article = articleService.get(id);//文章
        boolean success = articleService.operatorDelete(article);
        if (success) {
            NotificationUtil.add(session, String.format("编号为 [%s] 的文章信息删除成功！", id));
        } else {
            NotificationUtil.add(session, String.format("编号为 [%s] 的文章信息删除失败！", id), Notification.TYPE_DANGER);
        }
        return "redirect:/operator/article/list";
    }

}
