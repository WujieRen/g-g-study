package cn.rwj.study.ibatis.my.io;

import java.io.InputStream;

/**
 * @author rwj
 * @since 2023/9/1
 */
public class Resources {

    /**
     * 根据配置文件的路径，加载配置文件成字节输入流，存到内存中
     */
    public static InputStream getResourceAsStream(String path){
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }

}
