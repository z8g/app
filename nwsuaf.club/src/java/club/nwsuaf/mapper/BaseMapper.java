package club.nwsuaf.mapper;

import java.util.List;

/**
 * @名称 Mabatis映射接口 - 基础Model
 * @作者 赵栩旸
 * @param <T> Model
 */
public interface BaseMapper <T>{
    public int insert(T t);
    public int update(T t);
    public int delete(T t);
    public List<T> list(T t);
}
