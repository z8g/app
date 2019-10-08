package club.nwsuaf.service.impl;

import club.nwsuaf.mapper.ProjectMapper;
import club.nwsuaf.model.Project;
import club.nwsuaf.model.constants.Constants;
import club.nwsuaf.service.ProjectService;
import club.nwsuaf.util.DatetimeUtil;
import club.nwsuaf.util.MyBatisUtil;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @名称 逻辑服务实现类 - 项目
 * @作者 赵栩旸
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Override
    public List<Project> visitorListPublishedByType(String type) {
        List<Project> result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
            Project project = new Project();
            project.setType(type);
            result = projectMapper.list(project);
            sqlSession.commit();
        }

        return result;
    }

    @Override
    public boolean userInsert(Project project) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);

            String nowString = DatetimeUtil.formatNow();
            project.setGmtCreate(nowString);//设置创建时间
            project.setGmtUpdate(nowString);//设置修改时间
            project.setState(Constants.PROJECT_STATE_CG);//设置类型为草稿

            int n = projectMapper.insert(project);
            sqlSession.commit();
            result = n > 0;
        }

        return result;
    }

    @Override
    public boolean userUpdate(Project project) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);

            project.setGmtUpdate(DatetimeUtil.formatNow());//设置修改时间
project.setState(Constants.PROJECT_STATE_CG);
            int n = projectMapper.update(project);
            sqlSession.commit();
            result = n > 0;
        }

        return result;
    }

    @Override
    public boolean userDelete(Project project) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);

            int n = projectMapper.delete(project);
            sqlSession.commit();
            result = n > 0;
        }

        return result;
    }

    @Override
    public boolean userPublish(Project project) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
            project.setState(Constants.PROJECT_STATE_SHZ);//修改项目状态为审核中
            int n = projectMapper.update(project);
            sqlSession.commit();
            result = n > 0;
        }

        return result;
    }

    @Override
    public boolean operatorAuditSuccess(Project project) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);

            project.setState(Constants.PROJECT_STATE_YFB);

            int n = projectMapper.update(project);
            sqlSession.commit();
            result = n > 0;
        }

        return result;
    }

    @Override
    public boolean operatorAuditFail(Project project) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);

            project.setState(Constants.PROJECT_STATE_CG);

            int n = projectMapper.update(project);
            sqlSession.commit();
            result = n > 0;
        }

        return result;
    }

    @Override
    public List<Project> administratorList() {
        List<Project> result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
            result = projectMapper.list(null);
            sqlSession.commit();
        }

        return result;
    }

    @Override
    public boolean administratorDelete(Integer id) {
        boolean result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);

            Project project = new Project();
            project.setId(id);

            int n = projectMapper.delete(project);
            sqlSession.commit();
            result = n > 0;
        }

        return result;
    }

    @Override
    public Project get(Integer id) {
        Project result = null;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);

            Project project = new Project();
            project.setId(id);
            List<Project> list = projectMapper.list(project);

            if (list != null) {
                result = list.get(0);
            }
            sqlSession.commit();
        }

        return result;
    }

    @Override
    public Project visitorGet(Integer id) {
         Project result = null;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);

            Project project = new Project();
            project.setId(id);
            project.setState(Constants.PROJECT_STATE_YFB);//只能查看已发布的

            List<Project> list = projectMapper.list(project);

            if (list != null) {
                result = list.get(0);
            }
            sqlSession.commit();
        }

        return result;
    }
    

    @Override
    public List<Project> userList(String username) {
        List<Project> result;
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
            Project search = new Project();
            search.setUsername(username);
            result = projectMapper.list(search);
            sqlSession.commit();
        }
        return result;
    }

    @Override
    public List<Project> operatorList() {
         List<Project> result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
            Project project = new Project();
            project.setState(Constants.PROJECT_STATE_SHZ);
            result = projectMapper.list(project);
            sqlSession.commit();
        }

        return result;
    }

    @Override
    public List<Project> visitorListPublished() {
        List<Project> result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
            Project project = new Project();
            project.setState(Constants.PROJECT_STATE_YFB);
            result = projectMapper.list(project);
            sqlSession.commit();
        }

        return result;
    }

    @Override
    public List<Project> visitorListPublishedByUser(String username) {
        List<Project> result;

        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            ProjectMapper projectMapper = sqlSession.getMapper(ProjectMapper.class);
            Project project = new Project();
            project.setState(Constants.PROJECT_STATE_YFB);
            project.setUsername(username);
            result = projectMapper.list(project);
            sqlSession.commit();
        }

        return result;
    }

}
