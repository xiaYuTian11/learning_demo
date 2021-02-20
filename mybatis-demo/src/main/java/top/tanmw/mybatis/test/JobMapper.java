package top.tanmw.mybatis.test;

import org.apache.ibatis.annotations.Param;
import top.tanmw.mybatis.entity.Jobs;

/**
 * @author TMW
 * @date 2021/2/19 17:46
 */
public interface JobMapper {

    public Jobs selectUser(@Param("id") String id);
}
