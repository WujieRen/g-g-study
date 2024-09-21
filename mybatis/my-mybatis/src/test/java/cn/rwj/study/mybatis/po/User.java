package cn.rwj.study.mybatis.po;

import java.util.Date;
import lombok.Data;

/**
 * @author rwj
 * @since 2024/9/21
 */
@Data
public class User {

    private Long id;
    private String userId;          // 用户ID
    private String userHead;        // 头像
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间

}
