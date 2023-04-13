package cn.rwj.study.dpattern._18备忘录模式._02菜鸟;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CareTaker  看门人
 *
 * @author rwj
 * @date 2023/4/13
 */
public class CareTaker {

    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }

}
