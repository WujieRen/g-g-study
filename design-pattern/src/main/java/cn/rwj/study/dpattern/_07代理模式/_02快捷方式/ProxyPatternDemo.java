package cn.rwj.study.dpattern._07代理模式._02快捷方式;

/**
 * @author rwj
 * @date 2023/4/5
 */
public class ProxyPatternDemo {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }

}
