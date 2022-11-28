package cn.rwj.study.akka.basic;

import akka.actor.UntypedAbstractActor;

/**
 * @author rwj
 * @date 2022/11/28
 */
public class TargetActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        System.out.println("target receive:" + message);
    }
}
