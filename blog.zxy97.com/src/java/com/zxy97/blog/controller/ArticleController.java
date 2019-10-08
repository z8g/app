package com.zxy97.blog.controller;

import com.zxy97.blog.model.Article;
import com.zxy97.blog.model.Author;
import com.zxy97.blog.service.ArticleService;
import com.zxy97.blog.service.AuthorService;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {

    @RequestMapping(value = "{authorUsername}/article/details/{articleId}")
    public ModelAndView details(@PathVariable String authorUsername, @PathVariable Integer articleId, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Author author = AuthorService.getAuthor(authorUsername);
        Author user = (Author) session.getAttribute("user");
        boolean isLogin = (user != null);//是否登录
        boolean isSelf = false;//被访问主页的作者是否为已登录用户
        if (author == null) {
            mv.setViewName("redirect:/author");
            return mv;//找不到被访问主页的作者则直接返回
        }
        if (isLogin) {//不加判断，如果user为null会引起异常
            if (user.getUsername().equals(author.getUsername())) {
                isSelf = true;
            }
        }
        session.setAttribute("isSelf", isSelf);
        Article article = ArticleService.getArticleById(articleId);
        session.setAttribute("article", article);
        if (isSelf) {
            mv.setViewName("ArticleEditForm");
        } else {
            mv.setViewName("ArticleRead");
        }
        return mv;
    }

    @RequestMapping(value = "{authorUsername}/article/new")
    public ModelAndView insert(@PathVariable String authorUsername, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Author author = AuthorService.getAuthor(authorUsername);
        Author user = (Author) session.getAttribute("user");
        boolean isLogin = (user != null);//是否登录
        boolean isSelf = false;//被访问主页的作者是否为已登录用户
        if (author == null) {
            mv.setViewName("redirect:/LoginForm");
            return mv;//找不到被访问主页的作者则直接返回
        }
        if (isLogin) {//不加判断，如果user为null会引起异常
            if (user.getUsername().equals(author.getUsername())) {
                isSelf = true;
            }
        }
        session.setAttribute("isSelf", isSelf);
        Article article = new Article();
        article.setAuth(0);
        article.setTitle("新标题");
        article.setType("未分类");
        article.setMarkdown("");
        session.setAttribute("article", article);

        if (isSelf) {
            mv.setViewName("ArticleEditForm");
        } else {
            mv.setViewName("LoginForm");
        }
        return mv;
    }

    private Integer toInteger(String param) {
        try {
            return Integer.valueOf(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @RequestMapping(value = "{authorUsername}/article/publisher")
    public ModelAndView publisher(@PathVariable String authorUsername, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            mv.setViewName("redirect:/" + authorUsername);
            return mv;//找不到被访问主页的作者则直接返回
        }
        Integer article_id = toInteger(request.getParameter("article_id"));
        Integer article_auth = toInteger(request.getParameter("article_auth"));
        String article_title = request.getParameter("article_title");
        String article_markdown = request.getParameter("article_markdown");
        String article_html = request.getParameter("article_html");
        String article_type = request.getParameter("article_type");
        String article_new_type = request.getParameter("article_new_type");

        Author author = AuthorService.getAuthor(authorUsername);
        if (author == null) {
            mv.setViewName("redirect:/LoginForm");
            return mv;//找不到被访问主页的作者则直接返回
        }

        Article article = new Article();
        article_auth = article_auth == null ? 0 : article_auth;
        article.setAuth(article_auth);
        String articleType;

        article_new_type = article_new_type == null ? "" : article_new_type;
        article_type = article_type == null ? "" : article_type;

        if (!"".equals(article_new_type)) {
            articleType = article_new_type;
        } else if (!"".equals(article_type)) {
            articleType = article_type;
        } else {
            articleType = "未分类";
        }
        article.setId(article_id);
        article.setTitle(article_title);
        article.setType(articleType);
        article.setMarkdown(article_markdown);
        article.setHtml(article_html);
        article.setAuthorUsername(authorUsername);

        if (article.getId() == null || article.getId() == 0) {
            ArticleService.articleInsert(article);
        } else {
            ArticleService.articleUpdate(article);
        }
        mv.addObject("article_id", article_id);
        mv.setViewName("redirect:/" + authorUsername);
        return mv;
    }

    @RequestMapping(value = "{authorUsername}/article/remover/{articleId}")
    public ModelAndView remover(@PathVariable String authorUsername, @PathVariable Integer articleId, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Author author = AuthorService.getAuthor(authorUsername);
        Author user = (Author) session.getAttribute("user");
        boolean isLogin = (user != null);//是否登录
        boolean isSelf = false;//被访问主页的作者是否为已登录用户
        if (author == null) {
            mv.setViewName("redirect:/LoginForm");
            return mv;//找不到被访问主页的作者则直接返回
        }
        if (isLogin) {//不加判断，如果user为null会引起异常
            if (user.getUsername().equals(author.getUsername())) {
                isSelf = true;
            }
        }
        session.setAttribute("isSelf", isSelf);
        if (isSelf) {
            Article article = new Article();
            article.setId(articleId);
            ArticleService.articleRecycle(article);
            article = null;
            session.setAttribute("article", article);
            mv.setViewName("redirect:/" + authorUsername);
        } else {
            mv.setViewName("LoginForm");
        }
        return mv;
    }

}
