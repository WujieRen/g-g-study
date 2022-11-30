package cn.rwj.study.akka.basic;

import akka.actor.UntypedActor;

/**
 * @author rwj
 * @date 2022/11/28
 */
public class TargetActor extends UntypedActor {
    @Override
    public void onReceive(Object message) {
        System.out.println("target receive:" + message);
    }
}
