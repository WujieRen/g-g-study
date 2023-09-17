package cn.rwj.study.java.jvm;

import cn.hutool.core.date.DateUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * jvm参数：
 * -Xmx120M：指定JVM堆的最大内存为120MB，即JVM运行时可以使用的堆内存上限。
 * -Xms120M：指定堆初始化内存为120MB，表示JVM启东时分配给堆的内存大小。
 * -XX:+UseAdaptiveSizePolicy：启动自适应内存调整策略。该选项允许JVM根据当前应用程序的需求自动调整内存大小。
 * -XX:SurvivorRatio=8：设置 Eden 空间与 Survivor 空间的比率为 8:1
 * -XX:NewRatio=2：设置新生代与老年代的比率为2:1
 *
 * vm配置示例如下：
 * -Xmx240M -Xms120M -XX:+UseAdaptiveSizePolicy -XX:SurvivorRatio=8 -XX:NewRatio=2
 *
 * @author rwj
 * @since 2023/9/17
 */
public class VisualVmDemo {
    public static void main(String[] args) throws IOException {
        List<byte[]> list = new ArrayList<>();
        System.in.read();
        for (int i = 0; ; ++i) {
            System.out.println("+++ " + DateUtil.now());
            final int _4m = 1024 * 1024 * 4;
            byte[] arr = new byte[_4m];
            list.add(arr);
            System.in.read();
        }

    }
}
