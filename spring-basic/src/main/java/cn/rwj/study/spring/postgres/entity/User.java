package cn.rwj.study.spring.postgres.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author rwj
 * @since 2023/10/9
 */
@Data
@TableName("test_user")
public class User {

    @TableId(type = IdType.ASSIGN_ID)
    Long id;

    @TableField("name")
    String name;

}
