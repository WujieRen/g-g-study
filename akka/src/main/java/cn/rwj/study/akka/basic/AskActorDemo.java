package cn.rwj.study.akka.basic;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * 发送 - 接收消息
 *  如果仅仅是给 actor 发送消息，使用 actor.tell(message, sender) 即可
 *  如果发送消息后，要从 actor 得到一返回结果，使用 Patterns.ask(actorRef, message, timeout)
 * 消息转发
 *  除了给 actor发送消息外，还可以通过 forward 对消息进行转发，比较典型的场景是实现 小西路由 和 负载 等功能，见 {@link cn.rwj.study.akka.basic.ForwardActor ForwardActor}
 * @author rwj
 * @date 2022/11/27
 */
public class AskActorDemo extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) {
        System.out.println("发送者是：" + getSender());
        getSender().tell("Hello," + message, getSelf());    //把当前Actor设置为Sender，注意这里不是this
    }

    //从执行结果可以看到ask是异步执行，且对同一个 actor 发送的消息，会被该 actor 顺序处理
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();
        ActorRef askActor = system.actorOf(Props.create(AskActorDemo.class), "askActor");
        Timeout timeout = new Timeout(Duration.create(2, "seconds"));
        Future<Object> future = Patterns.ask(askActor, "${message}", timeout);
        System.out.println("ask...");
        future.onSuccess(new OnSuccess<>() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("收到消息" + result);
            }
        }, system.dispatcher());

        //消息转发
        ActorRef forwardActor = system.actorOf(Props.create(ForwardActor.class), "forwardActor");
        Future<Object> ask = Patterns.ask(forwardActor, "消息转发~~~", timeout);
        System.out.println("~~~~~~转发~~~~~~~~");
        ask.onSuccess(new OnSuccess<Object>() {
            @Override
            public void onSuccess(Object result) throws Throwable, Throwable {
                System.out.println("转发回信~~~" + result);
            }
        }, system.dispatcher());

    }
}
