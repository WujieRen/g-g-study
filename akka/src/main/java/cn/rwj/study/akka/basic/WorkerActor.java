package cn.rwj.study.akka.basic;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 当不再需要某个Actor时，最好采用恰当的手段使其停止。停止Actor的三种方式：
 *      1.调用 ActorSystem 或者 getContext().stop()
 *      2.给 Actor 发送一个 PoisonPill（毒丸）消息
 *      3.给 Actor发送一个Kill消息，此时会抛出ActorKilledException异常，并上报到父级 supervisor 处理
 *          这种方式会多出一个Error日志：[ERROR] [11/29/2022 22:30:31.500] [sys-akka.actor.default-dispatcher-2] [akka://sys/user/workerActor] Kill (akka.actor.ActorKilledException)
 *          原因：再通过 kill 方式杀死 Actor 时，会抛出 akka.actor.ActorKilledException，
 *              该异常会被父级 Supervisor 处理，而默认处理方式就是停止该 Actor
 *
 * @author rwj
 * @date 2022/11/29
 */
public class WorkerActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(this.getContext().system(), this);

    @Override
    public void onReceive(Object message) {
        log.info("收到消息：" + message);
    }

    /**
     * 调用ActorSystem 或者 getContext().stop() 时，会调用该方法
     */
    @Override
    public void postStop() {
        log.info("Worker postStop");
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef ref = system.actorOf(Props.create(WorkerActor.class), "workerActor");
//        system.stop(ref);
//        ref.tell(PoisonPill.getInstance(), ActorRef.noSender());
        ref.tell(Kill.getInstance(), ActorRef.noSender());  // 这种方式会有 ERROR信息： [ERROR] [11/29/2022 22:30:31.500] [sys-akka.actor.default-dispatcher-2] [akka://sys/user/workerActor] Kill (akka.actor.ActorKilledException)
    }
}
