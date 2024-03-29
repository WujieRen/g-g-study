package cn.rwj.study.dpattern._20迭代器模式._02名字;

/**
 * @author rwj
 * @date 2023/4/9
 */
public class IteratorPatternDemo {

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        Iterator iter = namesRepository.getIterator();
        while (iter.hasNext()) {
            String name = (String) iter.next();
            System.out.println("Name : " + name);
        }
    }

}
