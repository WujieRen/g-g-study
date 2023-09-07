package cn.rwj.study.java.retry;

/**
 * @author rwj
 * @since 2023/9/7
 */
public class DemoRetry{


    public static void main(String[] args) throws InterruptedException {
        Object res = new RetryTemplate() {
            @Override
            protected Object doBiz() throws Exception {
                int temp = (int) (Math.random() * 10);

                if (temp > 3) {
                    throw new Exception("generate value bigger then 3! need retry");
                }

                return temp;
            }
        }.setRetryTime(10).setSleepTime(10).execute();

        System.out.println(res);
    }
}
