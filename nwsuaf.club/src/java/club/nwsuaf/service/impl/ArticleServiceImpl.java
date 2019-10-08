package club.nwsuaf.service.impl;

import club.nwsuaf.mapper.ArticleMapper;
import club.nwsuaf.model.Article;
import club.nwsuaf.service.ArticleService;
import club.nwsuaf.util.DatetimeUtil;
import club.nwsuaf.util.MyBatisUtil;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

/**
 * @名称 逻辑服务实现类 - 文章
 * @作者 赵栩旸
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Override
    public List<Article> list() {
        List<Article> result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);
            result = articleMapper.list(null);
            sqlSession.commit();
        }
        return result;
    }

    @Override
    public List<Article> listByType(String type) {
        List<Article> result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);

            Article article = new Article();
            article.setType(type);

            result = articleMapper.list(article);
            sqlSession.commit();
        }
        return result;
    }

    @Override
    public boolean administratorDelete(Integer id) {
        boolean result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);

            Article article = new Article();
            article.setId(id);

            int n = articleMapper.delete(article);
            sqlSession.commit();
            result = n > 0;

        }
        return result;
    }

    @Override
    public Article get(Integer id) {
        Article result = null;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);

            Article article = new Article();
            article.setId(id);

            List<Article> list = articleMapper.list(article);
            sqlSession.commit();

            if (list != null) {
                result = list.get(0);
            }
        }
        return result;
    }

    @Override
    public boolean operatorInsert(Article article) {
        boolean result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);

            String nowString = DatetimeUtil.formatNow();
            article.setGmtCreate(nowString);
            article.setGmtUpdate(nowString);

            int n = articleMapper.insert(article);
            sqlSession.commit();
            result = n > 0;

        }
        return result;
    }

    @Override
    public boolean operatorUpdate(Article article) {
        boolean result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);

            String nowString = DatetimeUtil.formatNow();
            article.setGmtUpdate(nowString);

            int n = articleMapper.update(article);
            sqlSession.commit();
            result = n > 0;

        }
        return result;
    }

    @Override
    public boolean operatorDelete(Article article) {
        boolean result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ArticleMapper articleMapper = sqlSession.getMapper(ArticleMapper.class);

            int n = articleMapper.delete(article);
            sqlSession.commit();
            result = n > 0;

        }
        return result;
    }

}
