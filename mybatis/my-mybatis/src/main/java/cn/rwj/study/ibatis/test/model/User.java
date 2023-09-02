package cn.rwj.study.ibatis.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author rwj
 * @since 2023/9/1
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    private String name;

}
