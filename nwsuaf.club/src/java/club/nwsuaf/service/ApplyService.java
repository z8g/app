package club.nwsuaf.service;

import club.nwsuaf.model.Apply;
import java.util.List;

/**
 * @名称 逻辑服务 - 项目申请
 * @作者 赵栩旸
 */
public interface ApplyService {

    List<Apply> listForUser(String username);

    boolean insert(Apply apply);

    boolean delete(Integer applyId, String username);
}
