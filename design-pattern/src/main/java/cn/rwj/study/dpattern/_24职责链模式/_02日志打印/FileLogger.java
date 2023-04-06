package cn.rwj.study.dpattern._24职责链模式._02日志打印;

/**
 * @author rwj
 * @date 2023/4/6
 */
public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }

}
