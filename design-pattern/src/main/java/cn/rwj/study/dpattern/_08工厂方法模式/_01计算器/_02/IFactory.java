package cn.rwj.study.dpattern._08工厂方法模式._01计算器._02;

public interface IFactory {

    public Operation createOperation(String operType);

}
