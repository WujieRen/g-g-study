package cn.rwj.study.spring.postgres.service;

import cn.rwj.study.spring.postgres.entity.User;
import cn.rwj.study.spring.postgres.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author rwj
 * @since 2023/10/9
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IService<User> {


}
