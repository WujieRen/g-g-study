package cn.rwj.study.spring.postgres.mapper;

import cn.rwj.study.spring.postgres.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author rwj
 * @since 2023/10/9
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
