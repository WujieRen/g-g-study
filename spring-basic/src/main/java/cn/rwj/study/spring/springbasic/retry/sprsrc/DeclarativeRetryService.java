package cn.rwj.study.spring.springbasic.retry.sprsrc;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rwj
 * @since 2023/9/7
 */
@Service
public class DeclarativeRetryService {

    @Retryable(value = Exception.class, backoff = @Backoff(delay = 2000, multiplier = 1.5), recover = "callBackFinally")
    public String callBack(String url, Map<String, Object> res) {
        Map<String, Object> data = new HashMap<>();
        data.put("data", res);
        System.out.println("发起回调；url=" + url +" , args=" + res);

        String resp = HttpUtil.post(url, data);
        System.out.println(resp);
        return resp;
    }

    @Recover
    public String callBackFinally(Exception e, String url, Map<String, Object> res) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~ 兜底 ~~~~~~~~~~~~~~~~~~~~~");
        throw new RuntimeException(e);
    }

}
