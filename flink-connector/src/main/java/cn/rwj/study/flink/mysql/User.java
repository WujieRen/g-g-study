package cn.rwj.study.flink.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author rwj
 * @since 2023/6/27
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public int id;

    public String name;

    public String address;

    public int sex;

}
