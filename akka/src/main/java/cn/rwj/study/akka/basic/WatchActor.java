package cn.rwj.study.akka.basic;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * 随意停Actor也会导致不可预知的后果。为了提高可用性和维护性，Actor停止时都会遵循一套比较可靠的流程：
 *      1.当停止 Actor 是，正在处理的消息会在完全停止之前处理完毕，后续消息将不再进行处理，邮箱（用来保存 Actor 的消息）将被挂起；
 *      2.给所有子级 Actor 发送终止指令，当子级都停掉后，再听自己。停止完毕后会调用 postStop 方法，在这可以清理或释放资源。
 *      3.向生命周期监控者）DeathWatch）发送 Terminated 消息，以便监控者做相应的处理。
 * @author rwj
 * @date 2022/11/29
 */
public class WatchActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(this.getContext().system(), this);

    ActorRef child = null;

    /**
     * 当创建WatchActor时，会默认启动preStart方法
     * @throws Exception
     */
    @Override
    public void preStart() throws Exception {
        //创建子级Actor
        child = getContext().actorOf(Props.create(WorkerActor.class), "workerActor");
    }

    @Override
    public void postStop() throws Exception {
        log.info("WatchActor postStop");
    }

    @Override
    public void onReceive(Object message) throws Exception, Exception {

    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef ref = system.actorOf(Props.create(WatchActor.class), "workerActor");
        //关闭时会先关闭子级 Actor，再关闭当前 Actor
        system.stop(ref);
    }
}
