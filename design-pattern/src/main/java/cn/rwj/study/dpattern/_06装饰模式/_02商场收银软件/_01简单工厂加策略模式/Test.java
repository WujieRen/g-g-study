package cn.rwj.study.dpattern._06装饰模式._02商场收银软件._01简单工厂加策略模式;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");
        System.out.println("《大话设计模式》代码样例");
        System.out.println();

        int discount = 0;        //商品折扣模式(1.正常收费 2.打八折 3.打七折 4.满300送100 5.先8折，再满300送100)

        double price = 0d;        //商品单价
        int num = 0;            //商品购买数量
        double totalPrices = 0d;//当前商品合计费用
        double total = 0d;        //总计所有商品费用

        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("请输入商品折扣模式（1.正常收费 2.打八折 3.打七折 4.满300送100 5.先8折，再满300送100）：");
            discount = Integer.parseInt(sc.nextLine());
            System.out.println("请输入商品单价：");
            price = Double.parseDouble(sc.nextLine());
            System.out.println("请输入商品数量：");
            num = Integer.parseInt(sc.nextLine());
            System.out.println();

            if (price > 0 && num > 0) {


                //根据用户输入，将对应的策略对象作为参数传入CashContext对象中
                CashContext cc = new CashContext(discount);

                //通过Context的getResult方法的调用，可以得到收取费用的结果
                //让具体算法与客户进行了隔离
                totalPrices = cc.getResult(price, num);

                total = total + totalPrices;

                System.out.println();
                System.out.println("单价：" + price + "元 数量：" + num + " 合计：" + totalPrices + "元");
                System.out.println();
                System.out.println("总计：" + total + "元");
                System.out.println();
            }
        }
        while (price > 0 && num > 0);

        System.out.println();
        System.out.println("**********************************************");

    }
}
