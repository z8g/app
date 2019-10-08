package club.nwsuaf.service;

import club.nwsuaf.model.Project;
import java.util.List;

/**
 * @名称 逻辑服务 - 项目
 * @作者 赵栩旸
 */
public interface ProjectService {
    /**
     * 游客 - 根据具体类型查询已发布的项目
     * @param type
     * @return 
     */
    public List<Project> visitorListPublishedByType(String type);
    
    /**
     * 查询所有已发布的项目
     * @return 
     */
    public List<Project> visitorListPublished();
    
    public List<Project> visitorListPublishedByUser(String username);
    
    /**
     * 用户 - 创建项目（状态为草稿）
     * @param project
     * @return 
     */
    public boolean userInsert(Project project);
    
    /**
     * 用户 - 修改项目
     * @param project 包含项目修改的内容和用户名
     * @return 
     */
    public boolean userUpdate(Project project);
    
    /**
     * 用户 - 删除项目 
     * @param project 包含项目修改的内容和用户名
     * @return 
     */
    public boolean userDelete(Project project);
    
    
    /**
     * 用户 - 发布项目 （状态变为审核中）
     * @param project
     * @return 
     */
    public boolean userPublish(Project project);
    
    public List<Project> operatorList();
    /**
     * 操作员 - 审核成功 （状态变为已发布）
     * @param project 只需包含项目ID
     * @return 
     */
    public boolean operatorAuditSuccess(Project project);
    
    /**
     * 操作员 - 审核失败
     * @param project 只需包含项目ID
     * @return 
     */
    public boolean operatorAuditFail(Project project);
    
    /**
     * 系统管理员 - 列出所有项目
     * @return 
     */
    public List<Project> administratorList();
    
    /**
     * 系统管理员 - 删除项目
     * @param id 项目ID
     * @return 
     */
    public boolean administratorDelete(Integer id);
    
    /**
     * 系统管理员/操作员/用户 - 获取项目详情（所有状态）
     * @param id 项目 ID
     * @return 
     */
    public Project get(Integer id);
    
    /**
     * 游客 - 获取项目详情（已发布）
     * @param id
     * @return 
     */
    public Project visitorGet(Integer id);
    
    /**
     * 用户 - 列出所有项目
     * @param username
     * @return 
     */
    public List<Project> userList(String username);
    
}
