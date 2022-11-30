package cn.rwj.study.akka.basic;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;


/**
 * @author rwj
 * @date 2022/11/30
 */
public class WatchActor2 extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(this.getContext().system(), this);

    ActorRef child = null;

    /**
     * 当创建WatchActor时，会默认启动preStart方法
     *
     * @throws Exception
     */
    @Override
    public void preStart() throws Exception {
        //创建子级Actor
        child = getContext().actorOf(Props.create(WorkerActor.class), "workerActor");
        getContext().watch(child);
    }

    @Override
    public void postStop() throws Exception {
        log.info("WatchActor2 postStop");
    }

    @Override
    public void onReceive(Object message) throws Exception, Exception {
        if (message instanceof String) {
            if("stopChild".equals(message.toString())) {
                getContext().stop(child);
            }
        } else if (message instanceof Terminated) {
            Terminated terminated = (Terminated) message;
            log.info("监控到" + terminated.getActor() + "停止了...");
        } else {
            unhandled(message);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("sys");
        ActorRef watchActor = system.actorOf(Props.create(WatchActor2.class), "watchActor");
        watchActor.tell("stopChild", ActorRef.noSender());
    }
}
