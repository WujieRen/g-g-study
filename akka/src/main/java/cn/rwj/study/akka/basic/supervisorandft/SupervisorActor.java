package cn.rwj.study.akka.basic.supervisorandft;

import akka.actor.*;
import akka.japi.Function;
import cn.rwj.study.akka.basic.supervisorandft.WorkerActor;
import scala.actors.threadpool.TimeUnit;
import scala.concurrent.duration.Duration;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author rwj
 * @date 2022/12/1
 */
public class SupervisorActor extends UntypedActor {

    /**
     * 定义监督策略 OneForOneStrategy
     * 该对象需要三个参数，分别是 maxNrOfRetries、withinTimeRange、decider（一个Function对象，它通过apply方法返回监督指令 Directive ）
     */
    private SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create(1, TimeUnit.MINUTES.name()), new Function<Throwable, SupervisorStrategy.Directive>() {
        @Override
        public SupervisorStrategy.Directive apply(Throwable t) throws Exception, Exception {
            if(t instanceof IOException) {
                System.out.println("=======IOException=======");
                return SupervisorStrategy.resume(); //恢复运行
            } else if(t instanceof IndexOutOfBoundsException) {
                System.out.println("=======IndexOutOfBoundsException=======");
                return SupervisorStrategy.restart(); //重启
            } else if(t instanceof SQLException) {
                System.out.println("=======SQLException=======");
                return SupervisorStrategy.stop();   //停止
            } else {
                System.out.println("=======escalate=======");
                return SupervisorStrategy.escalate();   //向上抛出，由上级Actor处理
            }
        }
    });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("********* " + WorkerActor.class.getName() + " *************");
        ActorRef workerActor = getContext().actorOf(Props.create(WorkerActor.class), WorkerActor.class.getName());
        getContext().watch(workerActor);
    }

    @Override
    public void onReceive(Object message) throws Exception, Exception {
        if(message instanceof Terminated) {
            Terminated ter = (Terminated) message;
            System.out.println(ter.getActor() + "~~~已经停止了");
        } else {
            System.out.println("stateCount=" + message);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();

        System.out.println("------------------------------");

        System.out.println(WorkerActor.class.getName());
        System.out.println(WorkerActor.class.getSimpleName());
        System.out.println(WorkerActor.class.getTypeName());

        ActorRef workerActor = system.actorOf(Props.create(WorkerActor.class), WorkerActor.class.getName());
        workerActor.tell(new IOException(), ActorRef.noSender());
        workerActor.tell(new SQLException("SQL异常"), ActorRef.noSender());
        workerActor.tell(new IndexOutOfBoundsException(), ActorRef.noSender());
        workerActor.tell("getValue", ActorRef.noSender());

    }


}
