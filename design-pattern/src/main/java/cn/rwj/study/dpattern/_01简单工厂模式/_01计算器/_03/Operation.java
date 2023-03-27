package cn.rwj.study.dpattern._01简单工厂模式._01计算器._03;

public class Operation {
    public static double getResult(double numberA, double numberB, String operate) {
        double result = 0d;
        switch (operate) {
            case "+":
                result = numberA + numberB;
                break;
            case "-":
                result = numberA - numberB;
                break;
            case "*":
                result = numberA * numberB;
                break;
            case "/":
                result = numberA / numberB;
                break;
            case "pow":
                result = Math.pow(numberA, numberB);
                break;
        }
        return result;
    }
}
