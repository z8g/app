package club.nwsuaf.service.impl;

import club.nwsuaf.mapper.ApplyMapper;
import club.nwsuaf.model.Apply;
import club.nwsuaf.service.ApplyService;
import club.nwsuaf.util.DatetimeUtil;
import club.nwsuaf.util.MyBatisUtil;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

/**
 * @名称 逻辑服务实现类 - 项目申请
 * @作者 赵栩旸
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Override
    public List<Apply> listForUser(String username) {
        List<Apply> result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ApplyMapper applyMapper = sqlSession.getMapper(ApplyMapper.class);
            Apply search = new Apply();
            search.setUsername(username);
            result = applyMapper.list(search);
            sqlSession.commit();
        }
        return result;
    }

    @Override
    public boolean insert(Apply apply) {
        boolean result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ApplyMapper applyMapper = sqlSession.getMapper(ApplyMapper.class);
            apply.setGmtCreate(DatetimeUtil.formatNow());
            int n = applyMapper.insert(apply);
            sqlSession.commit();
            result = n > 0;
        }
        return result;
    }

    @Override
    public boolean delete(Integer applyId, String username) {
        boolean result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ApplyMapper applyMapper = sqlSession.getMapper(ApplyMapper.class);
            Apply search = new Apply();
            search.setId(applyId);
            search.setUsername(username);
            int n = applyMapper.delete(search);
            sqlSession.commit();
            result = n > 0;
        }
        return result;
    }

}
