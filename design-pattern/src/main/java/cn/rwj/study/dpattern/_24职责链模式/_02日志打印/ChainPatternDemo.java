package cn.rwj.study.dpattern._24职责链模式._02日志打印;

/**
 * @author rwj
 * @date 2023/4/6
 */
public class ChainPatternDemo {

    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

//        loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");

//        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }

}
