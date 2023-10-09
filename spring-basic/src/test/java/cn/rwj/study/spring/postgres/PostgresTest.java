package cn.rwj.study.spring.postgres;

import cn.rwj.study.spring.postgres.entity.User;
import cn.rwj.study.spring.postgres.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author rwj
 * @since 2023/10/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PostgresTest {

    @Resource
    private UserServiceImpl userService;

    @Test
    public void test() {
        User user = new User();
        user.setName("rwj");
        userService.save(user);
    }

}
