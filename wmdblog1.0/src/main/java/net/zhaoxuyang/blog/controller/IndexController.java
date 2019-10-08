/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
package net.zhaoxuyang.blog.controller;

import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpSession;
import static net.zhaoxuyang.blog.controller.AuthorArticleController.LOG;
import net.zhaoxuyang.blog.service.ArticleService;
import net.zhaoxuyang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author wyx
 */
@Controller
@RequestMapping(value="/index", produces = "text/html; charset=utf-8")
public class IndexController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    final static String INDEX_HTML = "Index.html";

    final static String ARTICLE_NOT_FOUND = "ArticleNotFound.html";
    final static String[] INDEX_TYPES = {"程序人生", "Python", "Java", "C", "C++", "C#", "前端", "架构", "区块链", "数据库", "游戏开发", "移动开发", "运维", "人工智能", "云计算", "大数据", "研发管理", "物联网", "计算机基础", "其他"};

    @GetMapping("/pages/{pageCurr}")
    public String list(
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            Model model,
            HttpSession session) {

        if (pageCurr == null) {
            pageCurr = 1;
        }

        String pageUri = "index/pages";
        model.addAttribute("pageUri", pageUri);

        PageInfo pageInfo = (PageInfo) articleService.listAll(pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);

        model.addAttribute("indextypes", INDEX_TYPES);
        return INDEX_HTML;
    }

    @RequestMapping(value = {"pages ", ""})
    public String list() {
        return String.format("redirect:/index/pages/1");
    }

    /**
     * 根据系统分类列出一个作者的文章(GET)
     *
     * @param model SpringMVC Model
     * @param type 系统分类
     * @param pageCurr 第几页
     * @param pageSize 每页几篇文章（默认为5）
     * @param session Web会话
     * @url http://localhost:8080/html/index/type/Java/2
     *
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping(value = "/type/{type}/{pageCurr}")
    public String listBytype(
            @PathVariable String type,
            HttpSession session,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            Model model
    ) {
        LOG.info("public String listBytype");
        LOG.info("type=" + type);
        type = type.trim();

        if (pageCurr == null) {
            pageCurr = 1;
        }

        //查询
        PageInfo pageInfo = articleService.listByType(type, pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        LOG.info(pageInfo.toString());

        model.addAttribute("indextypes", INDEX_TYPES);

        String pageUri = "index/type/" + type;
        model.addAttribute("pageUri", pageUri);

        return INDEX_HTML;
    }

    /**
     * 根据系统分类列出一个作者的文章的第1页(GET)
     *
     * @param session Web会话
     * @param type 系统分类
     * @url http://localhost:8080/html/index/type/Java
     *
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping(value = "/type/{type}")
    public String listByType1(
            @PathVariable String type,
            HttpSession session
    ) {
        LOG.info("public String listByType1");
        LOG.info("type=" + type);
        return String.format("redirect:/index/type/%s/1",
                java.net.URLEncoder.encode(type.trim()));
    }

    @GetMapping(value = "/search/{keyword}/{pageCurr}")
    public String listByTitleOrContent(
            @PathVariable String keyword,
            HttpSession session,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            Model model
    ) {
        keyword = keyword.trim();

        if (pageCurr == null) {
            pageCurr = 1;
        }

        //查询
        PageInfo pageInfo = articleService.listLikeTitleOrContent(keyword, 0, pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("indextypes", INDEX_TYPES);
        String pageUri = "index/search/" + keyword;
        model.addAttribute("pageUri", pageUri);

        return INDEX_HTML;
    }

    @GetMapping(value = "/search/{keyword}")
    public String listByTitleOrContent1(
            @PathVariable String keyword,
            HttpSession session
    ) {
        return String.format("redirect:/index/search/%s/1",
                java.net.URLEncoder.encode(keyword.trim()));
    }

}
