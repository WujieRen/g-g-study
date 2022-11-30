package cn.rwj.study.akka.basic;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author rwj
 * @date 2022/11/27
 */
public class ForwardActor extends UntypedActor {

    private ActorRef targetActor = getContext().actorOf(Props.create(AskActorDemo.class), "targetActor");

    @Override
    public void onReceive(Object message) {
        targetActor.forward(message, getContext());
    }
}
