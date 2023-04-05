package cn.rwj.study.dpattern._07代理模式._02快捷方式;

/**
 * @author rwj
 * @date 2023/4/5
 */
public class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }

}
