package cn.rwj.study.akka.basic;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * ActorSystem 和 ActorContext 可以通过接收一个 Props 实例来创建 Actor，而 Props 实例本身有两种创建方式
 *  1. 指定 Actor 的 Class 如： Props.create(ActorDemo.class)
 *  2. 指定一个工厂，实现 akka.japi.Creator 接口，重写其 creat()
 *  对于简单场景，使用1即可；如果需要统一配置或创建Actor的场景，则需要使用2.
 *  一般来讲，会直接把 Creator 工厂定义在 Actor 内部，作为静态 API 存在，见 {@link cn.rwj.study.akka.basic.PropsDemoActor PropsDemoActor}.
 *
 *
 * @author rwj
 * @date 2022/11/27
 */
public class ActorDemo extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(this.getContext().system(), this);

    @Override
    public void preStart() throws Exception {
        //创建子Actor
//        getContext().actorOf(Props.create(ChildActor.class, "childActor"));
        super.preStart();
    }

    @Override
    public void onReceive(Object message) {
        if(message instanceof String) {
            log.info(message.toString());
        } else {
            unhandled(message); //匹配不到相应类型消息，推荐使用 unhandled 进行处理
        }
    }

    public static void main(String[] args) {
        ActorSystem sys = ActorSystem.create("sys");
        ActorRef demo = sys.actorOf(Props.create(ActorDemo.class), "actorDemo");
        demo.tell("Hello Akka", ActorRef.noSender());
    }
}
