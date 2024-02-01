package cn.rwj.study.spring.springbasic.web;

import cn.hutool.core.util.NumberUtil;
import cn.rwj.study.spring.springbasic.BeanPostProcessorDemo.People;
import cn.rwj.study.spring.springbasic.retry.sprsrc.DeclarativeRetryService;
import cn.rwj.study.spring.utils.NetworkUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author rwj
 * @since 2023/9/4
 */
@RestController
@RequestMapping("test")
public class ForTestController {

    @Resource
    private People people;

    @GetMapping("get")
    public String getForTest() {
        return "get ---> " + people.toString();
    }

    @PostMapping("post")
    public String postForTest(HttpServletRequest request) throws IOException {
        People p = new People();

        String contentType = request.getContentType();

        /*if(contentType.startsWith(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
            String name = request.getParameter("name");
            if(StringUtils.isNotBlank(name)) {
                p.setName(name);
                return "post  ---> " + p;
            }
        }*/
        /*if(contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            String name = getRequestPostStr(request);
            if(StringUtils.isNotBlank(name)) {
                p.setName(name);
                return "post  ---> " + p;
            }
        }*/


        // 这种方式取值，兼容 application/x-www-form-urlencoded 和 application/json
        String name = NetworkUtil.getRequestPostStr(request);
        if (StringUtils.isNotBlank(name)) {
            p.setName(name);
            return "post  ---> " + p;
        }
        return "post  ---> " + p;
    }


    /*@RetryDot(count = 3, asyn = true)
    @GetMapping("retry/{num}")
    public void testRetry(@PathVariable Integer num) {
        if (num > 3) {
            throw new RuntimeException("報錯了---开始重试");
        } else {
            System.out.println("测试重试");
        }
    }*/

    @Resource
    private DeclarativeRetryService RetryService;

    @GetMapping("retry/{num}")
    public void testRetyr(@PathVariable Integer num) throws Throwable {
        HashMap<String, Object> map = new HashMap<>();
        map.put("kk", num);
        RetryService.callBack("http://localhost:18866/test/post", map);
    }

    @GetMapping("udf")
    public String udfGet(@RequestParam String name, @RequestParam String sex) {
        String res = String.format("人名：%s -- 性别：%s", name, sex);
        System.out.println(res);
        return res;
    }

    @PostMapping("udf")
    public String udfPost(@RequestBody User user) {
        boolean equals = Objects.equals(user.getSex(), "1");
        if(equals) {
            user.setSex("男");
        } else {
            user.setSex("女");
        }
        System.out.println(user);
        return user.toString();
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private String name;
    private String sex;
}
