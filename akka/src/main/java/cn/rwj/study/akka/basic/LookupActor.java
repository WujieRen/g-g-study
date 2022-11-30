package cn.rwj.study.akka.basic;

import akka.actor.*;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 查找一个Actor
 * 收到 "find" 消息后，会通过 ActorContext 查找出 ActorSelection 对象；
 * 给 ActorSelection 发送 identity 时，需要制定一个 messageId，用来区分所查找的不同 Actor ）可能查找多个 Actor）
 * 消息发送后，当前Actor会自动收到一个 ActorIdentity，通过该对象的 getActorRef() 得到一个 ActorRef（即 ActorSelection 查找带过来的 Actor）；
 * 但是ActorSelection 不保证 Actor 一定存在，所以通常会对返回的 ActorRef 做非空判断
 *
 * @author rwj
 * @date 2022/11/28
 */
public class LookupActor extends UntypedActor {

    private ActorRef target;

    {
        target = getContext().actorOf(Props.create(TargetActor.class), "targetActor");
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof String && Objects.equals("find", message)) {
            ActorSelection as = getContext().actorSelection("targetActor");
            as.tell(new Identify("A001"), getSelf());
        } else if (message instanceof ActorIdentity) {
            ActorIdentity ai = (ActorIdentity) message;
            if ("A001".equals(ai.correlationId().toString())) {
                ActorRef ref = ai.getRef();
                System.out.println("ActorIdentity:" + ai.correlationId() + ref);
                ref.tell("hello target", getSelf());
            }
        } else {
            unhandled(message);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        ActorRef lookupActor = system.actorOf(Props.create(LookupActor.class), "lookupActor");
        lookupActor.tell("find", ActorRef.noSender());

        /**
         * 除了以上的额方式，还可以通过 ActorSelection 的 resolveOne 得到 ActorRef，该方法会返回一个 scala.concurrent.Future 对象（和 ask() 返回的对象相同）
         * 当 Actor 不存在或在指定时间内没找到 Actor，抛出 akka.actor.ActorNotFound 异常
         */
        ActorSelection actorSelection = system.actorSelection("/user/lookupActor/targetActor");
        Timeout timeout = new Timeout(Duration.create(2, TimeUnit.SECONDS));
        Future<ActorRef> future = actorSelection.resolveOne(timeout);
        future.onSuccess(new OnSuccess<>() {
            @Override
            public void onSuccess(ActorRef ref) throws Throwable, Throwable {
                System.out.println("查找到 Actor: " + ref);
            }
        }, system.dispatcher());

        future.onFailure(new OnFailure() {
            @Override
            public void onFailure(Throwable failure) throws Throwable, Throwable {
                if (failure instanceof ActorNotFound) {
                    System.out.println("找不到 Actor：" + failure.getMessage());
                }
            }
        }, system.dispatcher());
    }
}
