package cn.rwj.study.akka.basic;

import akka.actor.*;
import akka.japi.Creator;

/**
 * @author rwj
 * @date 2022/11/27
 */
public class PropsDemoActor extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable, Throwable {

    }

    public static Props createProps() {
        //实现 akka.jaapi.Creator 接口，并将其传入 Props.create()
        return Props.create(new Creator<Actor>() {
            @Override
            public Actor create() throws Exception, Exception {
                return new PropsDemoActor();
            }
        });
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("propsDemo");
        ActorRef ref = system.actorOf(PropsDemoActor.createProps(), "propsActor");
    }
}
