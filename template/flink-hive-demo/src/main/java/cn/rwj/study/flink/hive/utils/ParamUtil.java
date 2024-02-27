package cn.rwj.study.flink.hive.utils;

import cn.hutool.json.JSONUtil;
import cn.rwj.study.flink.hive.model.Params;
import org.apache.flink.api.java.utils.ParameterTool;

/**
 * @author rwj
 * @since 2024/2/26
 */
public class ParamUtil {

    public static Params parseParams(String[] args) {
        ParameterTool parameter = ParameterTool.fromArgs(args);
        if (!parameter.has("param")) {
            throw new IllegalArgumentException("运行参数不能为空");
        }
        try {
            return JSONUtil.toBean(parameter.get("param"), Params.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("解析运行参数失败", e);
        }
    }

}
