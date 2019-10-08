/*
__        ____  __ ____    ____  _     ___   ____ 
\ \      / /  \/  |  _ \  | __ )| |   / _ \ / ___|
 \ \ /\ / /| |\/| | | | | |  _ \| |  | | | | |  _ 
  \ V  V / | |  | | |_| | | |_) | |__| |_| | |_| |
   \_/\_/  |_|  |_|____/  |____/|_____\___/ \____|
 */
/**
 * 获取作者的文章详情：
 * - 根据文章ID获取（GET）
 *
 * 获取RSS:
 * - 根据20条最新文章，返回RSS(GET)
 *
 * 获取作者的文章列表：
 * - 列出所有文章，分页
 * - 根据category列出，分页
 * - 根据tag列出，分页
 * - 根据年份月份列出，分页
 *
 * 提供作者的文章信息：
 * - 提供最近发表（3篇）
 * - 提供所有分类以及对应的数量
 * - 提供所有标签
 * - 提供年份月份以及对应的数量
 */
package net.zhaoxuyang.blog.controller;

import com.github.pagehelper.PageInfo;
import java.lang.invoke.MethodHandles;
import java.util.Date;
import javax.servlet.http.HttpSession;
import net.zhaoxuyang.blog.cache.UserCache;
import net.zhaoxuyang.blog.exception.BlogArticleNotFoundException;
import net.zhaoxuyang.blog.exception.BlogException;
import net.zhaoxuyang.blog.exception.BlogUserNotFoundException;
import net.zhaoxuyang.blog.model.Article;
import net.zhaoxuyang.blog.model.User;
import net.zhaoxuyang.blog.service.ArticleService;
import net.zhaoxuyang.blog.service.NoticeService;
import net.zhaoxuyang.blog.service.UserService;
import net.zhaoxuyang.blog.service.VisitService;
import net.zhaoxuyang.util.DatetimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author zhaoxuyang
 */
//@RequestPermission(value = {Permission.AUTHOR, Permission.ADMINISTRATOR})
@Controller
@RequestMapping(value = "/{authorUsername}/article", produces = "text/html; charset=utf-8")
public class AuthorArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @Autowired
    VisitService visitService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    UserCache userCache;

    final static String USER_HOME = "UserHome.html";
    final static String ARTICLE_NOT_FOUND = "ArticleNotFound.html";
    final static String ARTICLE_EDIT_FORM = "ArticleEdit.html";
    final static String ARTICLE_READ_FORM = "ArticleRead.html";

    final static Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * 判断是否为作者本人
     *
     * @param user 登录者
     * @param author 页面所有者
     * @return 是作者本人
     */
    private boolean isAuthor(User user, User author) {
        return user != null && author != null && user.getId().equals(author.getId());
    }

    /**
     * 判断是否为好友（或者说登录者是否已关注该页面所有者）
     *
     * @param user 登录者
     * @param author 页面所有者
     * @return 是好友（或者说已关注）
     */
//    private boolean isFriend(User user, User author) {
//        LOG.info("private boolean isFriend");
//        LOG.info("user=" + user);
//        LOG.info("author=" + author);
//        boolean isFriend = false;
//        return user != null && author != null && isFriend;
//    }
    private boolean isFriend(User user, User author) {
        if (user == null || author == null) {
            return false;
        }
        return !isAuthor(user, author) && noticeService.isFriend(user.getId(), author.getId());
    }

    /**
     * 获取搜索的文章权限
     *
     * @param user 登录者
     * @param author 页面所有者
     * @return
     */
    private Integer getAuth(User user, User author) {
        //判断是否是作者本人
        LOG.info("private Integer getAuth");
        LOG.info("user=" + user);
        LOG.info("author=" + author);
        if (isAuthor(user, author)) {
            //是作者本人，可以查询所有文章，并直接转发到编辑页面；
            return 2;
        } else if (isFriend(user, author)) {
            //是好友，可以查询公开可见和好友可见的文章
            return 1;
        } else {
            //是访客，只能查询公开可见的文章
            return 0;
        }
    }

    /**
     * 文章详情页面(GET)
     *
     * @param model SpringMVC Model
     * @param session Web会话
     * @throws net.zhaoxuyang.blog.exception.BlogUserNotFoundException
     * @throws net.zhaoxuyang.blog.exception.BlogArticleNotFoundException
     * @url http://localhost:8080/zhaoxuyang/article/details/666
     *
     * @param authorUsername 作者的用户名
     * @param articleId 文章的ID
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping("/details/{articleId}")
    public String details(
            @PathVariable String authorUsername,
            @PathVariable Integer articleId,
            Model model,
            HttpSession session
    ) throws BlogException {
        User user = (User) session.getAttribute("user");//登录的用户
        User author = userService.getByUsername(authorUsername);//作者
        if (author == null) {
            LOG.error("作者不存在！");
            throw new BlogUserNotFoundException(String.format("ID为%s的作者不存在！", authorUsername));
            //return ARTICLE_NOT_FOUND;
        }
        model.addAllAttributes(userCache.getUserHomeMap(authorUsername));//默认模型

        Integer auth = getAuth(user, author);//查询文章的权限

        Article article = articleService.get(articleId, auth);//根据权限获取文章

        //判断文章是否都存在
        if (article == null) {
            LOG.error("文章不存在！");
            //return ARTICLE_NOT_FOUND;
             throw new BlogArticleNotFoundException(String.format("ID为%d的文章不存在！", articleId));
        }

        LOG.info(article.toString());
        //不是作者本人，判断文章权限

        model.addAttribute("article", article);

        if (auth == 2) {
            return "redirect:/user/article/" + article.getId();//作者本人，编辑页面
        } else {
            visitService.visitArticle(session, article);//增加访客记录
            int countArticle = visitService.countArticle(article.getId());
            model.addAttribute("countArticle", countArticle);
            return ARTICLE_READ_FORM;//不是作者本人，阅读页面
        }
    }

    /**
     * 列出一个作者的文章(GET)
     *
     * @param model SpringMVC Model
     * @param pageCurr 第几页
     * @param pageSize 每页几篇文章
     * @param session Web会话
     * @url http://localhost:8080/zhaoxuyang/article/details/666
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping("/list/{pageCurr}")
    public String list(
            @PathVariable String authorUsername,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            Model model,
            HttpSession session
    ) {

        if (pageCurr == null) {
            pageCurr = 1;
        }

        model.addAllAttributes(userCache.getUserHomeMap(authorUsername));//默认模型

        User user = (User) session.getAttribute("user");//登录的用户
        User author = userService.getByUsername(authorUsername);//作者
        System.out.println("author" + author);
        if (author == null) {
            LOG.error("作者不存在！");
            return ARTICLE_NOT_FOUND;
        }

        model.addAttribute("isNotice", isFriend(user, author));//判断是否关注
        model.addAttribute("isAuthor", isAuthor(user, author));//判断是否作者

        Integer auth = getAuth(user, author);//查询文章的权限

        //查询
        PageInfo pageInfo = articleService.list(authorUsername, auth, pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        LOG.info(pageInfo.toString());

        String pageUri = authorUsername + "/article/list";
        model.addAttribute("pageUri", pageUri);

        //添加查询提示信息
        String selectTitle = String.format("%s | 文章列表（共%d条） | 第 %d/%d 页",
                authorUsername, pageInfo.getTotal(), pageCurr, pageInfo.getPages());
        model.addAttribute("selectTitle", selectTitle);
        if (!isAuthor(user, author)) {
            visitService.visitUserHome(session, author);//增加访客记录
        }

        return USER_HOME;
    }

    @RequestMapping(value = {"list", ""})
    public String list1(
            @PathVariable String authorUsername,
            HttpSession session
    ) {

        return String.format("redirect:/%s/article/list/1", authorUsername);
    }

    /**
     * 根据用户分类列出一个作者的文章(GET)
     *
     * @param model SpringMVC Model
     * @param category 用户分类
     * @param pageCurr 第几页
     * @param pageSize 每页几篇文章（默认为5）
     * @param session Web会话
     * @url http://localhost:8080/zhaoxuyang/article/category/Java技术/2
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping(value = "/category/{category}/{pageCurr}")
    public String listBycategory(
            @PathVariable String authorUsername,
            @PathVariable String category,
            HttpSession session,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            Model model
    ) {
        LOG.info("public String listBycategory");
        LOG.info("authorUsername=" + authorUsername);
        LOG.info("category=" + category);
        category = category.trim();

        if (pageCurr == null) {
            pageCurr = 1;
        }

        model.addAllAttributes(userCache.getUserHomeMap(authorUsername));//默认模型

        User user = (User) session.getAttribute("user");//登录的用户
        User author = userService.getByUsername(authorUsername);//作者
        if (author == null) {
            LOG.error("作者不存在！");
            return ARTICLE_NOT_FOUND;
        }

        Integer auth = getAuth(user, author);//查询文章的权限

        //查询
        PageInfo pageInfo = articleService.listByCategory(author.getId(), category, auth, pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        LOG.info(pageInfo.toString());

        String pageUri = authorUsername + "/article/category/" + category;
        model.addAttribute("pageUri", pageUri);

        //添加查询提示信息
        String selectTitle = String.format("%s | 文章分类：【%s】（共%d条） | 第 %d/%d 页",
                authorUsername, category, pageInfo.getTotal(), pageCurr, pageInfo.getPages());
        model.addAttribute("selectTitle", selectTitle);

        return USER_HOME;
    }

    /**
     * 根据用户分类列出一个作者的文章的第1页(GET)
     *
     * @param session Web会话
     * @param category 用户分类
     * @url http://localhost:8080/zhaoxuyang/article/category/Java技术
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping(value = "/category/{category}")
    public String listByCategory1(
            @PathVariable String authorUsername,
            @PathVariable String category,
            HttpSession session
    ) {
        LOG.info("public String listByCategory1");
        LOG.info("authorUsername=" + authorUsername);
        LOG.info("category=" + category);
        category = category.trim();
        return String.format("redirect:/%s/article/category/%s/1",
                authorUsername, java.net.URLEncoder.encode(category));
    }

    /**
     * 根据标签列出一个作者的文章(GET)
     *
     * @param model SpringMVC Model
     * @param tag 标签
     * @param pageCurr 第几页
     * @param pageSize 每页几篇文章（默认为5）
     * @param session Web会话
     * @url http://localhost:8080/zhaoxuyang/article/tag/Linux/2
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @RequestMapping(value = "/tag/{tag}/{pageCurr}", produces = "text/html; charset=utf-8")
    public String listByTag(
            @PathVariable String authorUsername,
            @PathVariable String tag,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            HttpSession session,
            Model model
    ) {
        tag = tag.trim();
        if (pageCurr == null) {
            pageCurr = 1;
        }

        model.addAllAttributes(userCache.getUserHomeMap(authorUsername));//默认模型

        User user = (User) session.getAttribute("user");//登录的用户
        User author = userService.getByUsername(authorUsername);//作者
        if (author == null) {
            LOG.error("作者不存在！");
            return ARTICLE_NOT_FOUND;
        }

        Integer auth = getAuth(user, author);//查询文章的权限

        //查询
        PageInfo pageInfo = articleService.listLikeTag(author.getId(),
                tag, auth, pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        LOG.info(pageInfo.toString());

        String pageUri = authorUsername + "/article/tag/" + tag;
        model.addAttribute("pageUri", pageUri);

        //添加查询提示信息
        String selectTitle = String.format("%s | 文章标签：【%s】（共%d条） | 第 %d/%d 页",
                authorUsername, tag, pageInfo.getTotal(), pageCurr, pageInfo.getPages());
        model.addAttribute("selectTitle", selectTitle);

        return USER_HOME;
    }

    /**
     * 根据标签列出一个作者的文章的第1页(GET)
     *
     * @param session Web会话
     * @param tag 标签
     *
     * @url http://localhost:8080/zhaoxuyang/article/tag/Linux
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @RequestMapping(value = "/tag/{tag}", produces = "text/html; charset=utf-8")
    public String listByTag1(
            @PathVariable String authorUsername,
            @PathVariable String tag,
            HttpSession session
    ) {
        tag = tag.trim();
        return String.format("redirect:/%s/article/tag/%s/1",
                authorUsername, java.net.URLEncoder.encode(tag));
    }
    
     @RequestMapping(value = "/search/{keyword}/{pageCurr}", produces = "text/html; charset=utf-8")
    public String listByTitleOrContent(
            @PathVariable String authorUsername,
            @PathVariable String keyword,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            HttpSession session,
            Model model
    ) {
        keyword = keyword.trim();
        if (pageCurr == null) {
            pageCurr = 1;
        }

        model.addAllAttributes(userCache.getUserHomeMap(authorUsername));//默认模型

        User user = (User) session.getAttribute("user");//登录的用户
        User author = userService.getByUsername(authorUsername);//作者
        if (author == null) {
            LOG.error("作者不存在！");
            return ARTICLE_NOT_FOUND;
        }

        Integer auth = getAuth(user, author);//查询文章的权限

        //查询
        PageInfo pageInfo = articleService.listLikeTitleOrContent(keyword,
                 auth, pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        LOG.info(pageInfo.toString());

        String pageUri = authorUsername + "/article/search/" + keyword;
        model.addAttribute("pageUri", pageUri);

        //添加查询提示信息
        String selectTitle = String.format("%s | 关键词：【%s】（共%d条） | 第 %d/%d 页",
                authorUsername, keyword, pageInfo.getTotal(), pageCurr, pageInfo.getPages());
        model.addAttribute("selectTitle", selectTitle);

        return USER_HOME;
    }
    @RequestMapping(value = "/search/{keyword}", produces = "text/html; charset=utf-8")
    public String listByTitleOrContent1(
            @PathVariable String authorUsername,
            @PathVariable String keyword,
            HttpSession session
    ) {
        keyword = keyword.trim();
        return String.format("redirect:/%s/article/search/%s/1",
                authorUsername, java.net.URLEncoder.encode(keyword));
    }

    /**
     * 根据年份月份列出一个作者的文章(GET)
     *
     * @param model SpringMVC Model
     * @param yearMonth 年份月份（2019-01）
     * @param pageCurr 第几页
     * @param pageSize 每页几篇文章（默认为5）
     * @param session Web会话
     * @url http://localhost:8080/zhaoxuyang/article/yearmonth/2019-01/2
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @RequestMapping("/yearmonth/{yearMonth}/{pageCurr}")
    public String listByYearMonth(
            @PathVariable String authorUsername,
            @PathVariable String yearMonth,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            HttpSession session,
            Model model
    ) {
        LOG.info("public String listByYearMonth");
        LOG.info("authorUsername=" + authorUsername);
        LOG.info("yearMonth=" + yearMonth);
        LOG.info("pageCurr=" + pageCurr);
        LOG.info("pageSize=" + pageSize);

        yearMonth = yearMonth.trim();

        if (pageCurr == null) {
            pageCurr = 1;
        }

        model.addAllAttributes(userCache.getUserHomeMap(authorUsername));//默认模型

        User user = (User) session.getAttribute("user");//登录的用户
        User author = userService.getByUsername(authorUsername);//作者
        if (author == null) {
            LOG.error("作者不存在！");
            return ARTICLE_NOT_FOUND;
        }

        Integer auth = getAuth(user, author);//查询文章的权限

        Date date = DatetimeUtil.formatString(yearMonth);

        //查询
        PageInfo pageInfo = articleService.listByCreateYearMonth(author.getId(),
                date, auth, pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        LOG.info(pageInfo.toString());

        String pageUri = authorUsername + "/article/yearmonth/" + yearMonth;
        model.addAttribute("pageUri", pageUri);

        //添加查询提示信息
        String selectTitle = String.format("%s | 文章归档：【%s】（共%d条） | 第 %d/%d 页",
                authorUsername, yearMonth, pageInfo.getTotal(), pageCurr, pageInfo.getPages());
        model.addAttribute("selectTitle", selectTitle);

        return USER_HOME;
    }

    /**
     * 根据年份月份列出一个作者的文章的第1页(GET)
     *
     * @param session Web会话
     * @param yearMonth 年份月份
     * @url http://localhost:8080/zhaoxuyang/article/yearmonth/2019-01
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping("/yearmonth/{yearMonth}")
    public String listByYearMonth1(
            @PathVariable String authorUsername,
            @PathVariable String yearMonth,
            HttpSession session
    ) {
        LOG.info("public String listByYearMonth1");
        LOG.info("authorUsername=" + authorUsername);
        LOG.info("yearMonth=" + yearMonth);
        yearMonth = yearMonth.trim();
        return String.format("redirect:/%s/article/yearmonth/%s/1",
                authorUsername, yearMonth);
    }

    /**
     * 根据系统分类列出一个作者的文章(GET)
     *
     * @param model SpringMVC Model
     * @param type 系统分类
     * @param pageCurr 第几页
     * @param pageSize 每页几篇文章（默认为5）
     * @param session Web会话
     * @url http://localhost:8080/wangyuxuan/article/type/Java/2
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping(value = "/type/{type}/{pageCurr}")
    public String listBytype(
            @PathVariable String authorUsername,
            @PathVariable String type,
            HttpSession session,
            @PathVariable Integer pageCurr,
            @RequestParam(defaultValue = "5") Integer pageSize,
            Model model
    ) {
        LOG.info("public String listBytype");
        LOG.info("authorUsername=" + authorUsername);
        LOG.info("type=" + type);
        type = type.trim();

        if (pageCurr == null) {
            pageCurr = 1;
        }

        User author = userService.getByUsername(authorUsername);//作者
        if (author == null) {
            LOG.error("作者不存在！");
            return ARTICLE_NOT_FOUND;
        }

        //查询
        PageInfo pageInfo = articleService.listByType(type, pageCurr, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        LOG.info(pageInfo.toString());

        String pageUri = authorUsername + "/article/type/" + type;
        model.addAttribute("pageUri", pageUri);

        return USER_HOME;
    }

    /**
     * 根据系统分类列出一个作者的文章的第1页(GET)
     *
     * @param session Web会话
     * @param type 系统分类
     * @url http://localhost:8080/wangyuxuan/article/type/Java
     *
     * @param authorUsername 作者的用户名
     * @return 视图名称（template下的xxx.html）
     */
    @GetMapping(value = "/type/{type}")
    public String listByType1(
            @PathVariable String authorUsername,
            @PathVariable String type,
            HttpSession session
    ) {
        LOG.info("public String listByType1");
        LOG.info("authorUsername=" + authorUsername);
        LOG.info("type=" + type);
        type = type.trim();
        return String.format("redirect:/%s/article/type/%s/1", authorUsername, type);
    }

    @GetMapping(value = "notice/add")
    public String addNotice(HttpSession session, @PathVariable String authorUsername) {
        User loginUser = (User) session.getAttribute("user");
        User author = userService.getByUsername(authorUsername);
        LOG.info(loginUser.toString());
        LOG.info(author.toString());
        noticeService.insert(loginUser.getId(), author.getId());
        return String.format("redirect:/%s/article/list", authorUsername);
    }

    @GetMapping(value = "notice/remove")
    public String removeNotice(HttpSession session, @PathVariable String authorUsername) {
        User loginUser = (User) session.getAttribute("user");
        User author = userService.getByUsername(authorUsername);
        noticeService.delete(loginUser.getId(), author.getId());
        return String.format("redirect:/%s/article/list", authorUsername);
    }
}
