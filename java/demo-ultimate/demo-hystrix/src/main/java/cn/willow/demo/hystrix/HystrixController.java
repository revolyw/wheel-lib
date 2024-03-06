package cn.willow.demo.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author willow
 * @date 2024/3/6
 */
@RestController
public class HystrixController {
    /**
     *
     * @return
     */
    @HystrixCommand(groupKey = "hystrix", //一组 Hystrix 命令的集合， 用来统计、报告，默认取类名，可不配置。
            commandKey = "test", //用来标识一个 Hystrix 命令，默认会取被注解的方法名
            fallbackMethod = "fallback", //方法执行时熔断、错误、超时时会执行的降级方法，需要保持此方法与 Hystrix 方法的签名和返回值一致。
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"), //方法执行超时时间。
                    // 默认值是 1000，即 1秒，此值根据业务场景配置。
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1000"),//此配置项指定了窗口的大小，
                    // 单位是 ms，默认值是 1000，即一个滑动窗口默认统计的是 1s 内的请求数据。
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),//启用熔断器功能窗口时间内的最小请求数。
                    // 试想如果没有这么一个限制，我们配置了 50% 的请求失败会打开熔断器，窗口时间内只有 3 条请求，恰巧两条都失败了，
                    // 那么熔断器就被打开了，5s 内的请求都被快速失败。此配置项的值需要根据接口的 QPS 进行计算，
                    // 值太小会有误打开熔断器的可能，值太大超出了时间窗口内的总请求数，则熔断永远也不会被触发。
                    // 建议设置为 QPS * 窗口秒数 * 60%。 例如：QPS=5，窗口时间=1s，那么此值为 3。窗口时间参考：circuitBreaker.sleepWindowInMilliseconds
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "1"),//统计窗口内触发熔断的失败率
                    //在通过滑动窗口获取到当前时间段内 Hystrix 方法执行的失败率后，就需要根据此配置来判断是否要将熔断器打开了。
                    // 此配置项默认值是 50，即窗口时间内超过 50% 的请求失败后会打开熔断器将后续请求快速失败。
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),//熔断后请求重试时间间隔。单位 ms
                    //熔断器打开后，所有的请求都会快速失败，但何时服务恢复正常就是下一个要面对的问题。熔断器打开时，
                    // Hystrix 会在经过一段时间后就放行一条请求，如果这条请求执行成功了，说明此时服务很可能已经恢复了正常，那么会将熔断器关闭，
                    // 如果此请求执行失败，则认为服务依然不可用，熔断器继续保持打开状态。
                    // 此配置项指定了熔断器打开后经过多长时间允许一次请求尝试执行，默认值是 5000。
            },
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "1"), //核心线程池的大小，默认值是 10。
                    // 一般根据 QPS * 99% cost + redundancy count 计算得出。
                @HystrixProperty(name = "maximumSize", value = "1"), //线程池中线程的最大数量，默认值是 10。
                    // 此配置项单独配置时并不会生效，需要启用 allowMaximumSizeToDivergeFromCoreSize 项。
                @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize", value = "true"),// 是否允许线程池扩展到最大线程池数量，默认为 false;
                @HystrixProperty(name = "maxQueueSize", value = "1000"),//作业队列的最大值，默认值为 -1。
                    // 设置为默认值时，队列会使用 SynchronousQueue，此时其 size 为 0，Hystrix 不会向队列内存放作业。
                    // 如果此值设置为一个正的 int 型，队列会使用一个固定 size 的 LinkedBlockingQueue，此时在核心线程池内的线程都在忙碌时，
                    // 会将作业暂时存放在此队列内，但超出此队列的请求依然会被拒绝。
                @HystrixProperty(name = "queueSizeRejectionThreshold", value = "100"),//由于 maxQueueSize 值在线程池被创建后就固定了大小，
                    // 如果需要动态修改队列长度的话可以设置此值，即使队列未满，队列内作业达到此值时同样会拒绝请求。此值默认是 5
                    // 所以有时候只设置了 maxQueueSize 也不会起作用。
                @HystrixProperty(name = "keepAliveTimeMinutes", value = "5"), //由上面的 maximumSize，
                    // 线程池内核心线程数目都在忙碌，再有新请求到达时，线程池容量可以被扩充为到最大数量，
                    // 等到线程池空闲后，多于核心数量的线程还会被回收，此值指定了线程被回收前的存活时间，默认为 2，即两分钟。
            }
    )
    @RequestMapping("/test")
    public String test() throws InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String format = simpleDateFormat.format(now);
//        Thread.sleep(900);
        return format;
    }

    private String fallback(Throwable executionException){
        throw new RuntimeException("fallback：「" + executionException.getMessage() + "」");
//        return "fallback：「" + executionException.getMessage()+"」";
    }
}
