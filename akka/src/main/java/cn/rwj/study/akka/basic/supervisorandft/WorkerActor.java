package cn.rwj.study.akka.basic.supervisorandft;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.Option;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author rwj
 * @date 2022/12/1
 */
public class WorkerActor extends UntypedActor {

    //状态数据
    private int stateCount = 1;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        System.out.println("WorkerActor.preStart");
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        System.out.println("WorkerActor.postStop");
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("WorkerActor.preRestart begin --> " + this.stateCount);
        super.preRestart(reason, message);
        System.out.println("WorkerActor.preRestart end --> " + this.stateCount);
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        System.out.println("WorkerActor.postRestart start -->" + this.stateCount);
        super.postRestart(reason);
        System.out.println("WorkerActor.postRestart end -->" + this.stateCount);
    }

    @Override
    public void onReceive(Object message) throws Exception, Exception {
        //模拟计算任务
        this.stateCount++;
        if(message instanceof  Exception) {
            throw (Exception) message;
        } else if("getValue".equals(message)) {
            getSender().tell(stateCount, getSelf());
        } else {
            unhandled(message);
        }
    }


}
