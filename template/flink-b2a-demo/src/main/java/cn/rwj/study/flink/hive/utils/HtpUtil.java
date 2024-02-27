package cn.rwj.study.flink.hive.utils;

import cn.hutool.json.JSONUtil;
import cn.rwj.study.flink.hive.model.ApiInfo;
import cn.rwj.study.flink.hive.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hdfs.web.JsonUtil;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rwj
 * @since 2024/2/26
 */
@Slf4j
public class HtpUtil {

    private static final Map<String, List<String>> USER_HOUSE_MAP = new HashMap<>();

    /**
     * 这里模拟数据库中的数据
     *  user_id     house_address
     *   1001       北京市-1001-1
     *   1001       成都市-1001-2
     *   1002       南京市-1002-1
     *   1002       太原市-1002-2
     *   1003       武汉市-1003-1
     *   1003       杭州市1003-2
     */
    static {
        USER_HOUSE_MAP.put("1001", Arrays.asList("北京市-1001-1", "成都市-1001-2"));
        USER_HOUSE_MAP.put("1002", Arrays.asList("南京市-1002-1", "太原市-1002-2"));
        USER_HOUSE_MAP.put("1003", Arrays.asList("武汉市-1003-1"));
    }

    public static String callApi(ApiInfo apiInfo, String requestParam) throws IOException {
        // HttpUtil.post(url, requestParam)
        log.info("调用 http 接口...，url为：{}", apiInfo.getUrl());
        log.info("查询数据库中...");
        log.error("API调用，参数信息 ---> " + requestParam);
        User user = JSONUtil.toBean(requestParam, User.class);
        List<String> qryRes = USER_HOUSE_MAP.get(user.getId());
        if (null == qryRes) return null;

        List<User> users = qryRes
                .stream()
                .map(house -> {
                    User tmpUser = new User();
                    tmpUser.setId(user.getId());
                    tmpUser.setHouse(house);
                    return tmpUser;
                })
                .collect(Collectors.toList());
        log.info("查询结果：{}", users);

        return JsonUtil.toJsonString(users);
    }

}
