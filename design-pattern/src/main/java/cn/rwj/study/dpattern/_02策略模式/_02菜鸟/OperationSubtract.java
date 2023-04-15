package cn.rwj.study.dpattern._02策略模式._02菜鸟;

/**
 * @author rwj
 * @date 2023/4/15
 */
public class OperationSubtract implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
