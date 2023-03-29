package cn.rwj.study.dpattern._13建造者模式._02汉堡店;

/**
 *  将一个复杂的构建与其表示相分离，使得同样的构建过程可以创建不同的表示。
 * @author rwj
 * @date 2023/3/29
 */
public class Main {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegMeal.showItems();
        System.out.println("Total Cost: " + nonVegMeal.getCost());

    }

}
