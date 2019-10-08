/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.zhaoxuyang.blog.mapper;
import net.zhaoxuyang.blog.model.AdminArticleComment;
import net.zhaoxuyang.blog.model.AdminArticleCommentReply;
import net.zhaoxuyang.blog.model.AdminVisitArticle;

import org.springframework.stereotype.Repository;

/**
 *
 * @author wangjingyu
 */
@Repository
public interface AdminMapper {
    public int deleteArticleComment(AdminArticleComment adminArticleComment);
    public int deleteArticleCommentReply(AdminArticleCommentReply adminArticleCommentReply);
    public int deleteVisitArticle(AdminVisitArticle adminVisitArticle);
    public int selectArticleComment(AdminArticleComment adminArticleComment);
}
