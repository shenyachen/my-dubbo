package com.buge.dubboapi.SlidingWindow;



import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: yachen.shen
 * @Date 2020/3/30 11:45
 */
public class WindowLimiter {
    private LoadingCache<Long, AtomicLong> counter =
            CacheBuilder.newBuilder()
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build(new CacheLoader<Long, AtomicLong>() {
                        @Override
                        public AtomicLong load(Long seconds) throws Exception {
                            return new AtomicLong(0);
                        }
                    });
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    //限流阈值
    private long limit = 15;

    /**
     * 滑动时间窗口
     * 每隔1s累加前5s内每1s的请求数量，判断是否超出限流阈值
     */
    public void slideWindow() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                long time = System.currentTimeMillis() / 1000;
                //每秒发送随机数量的请求
                int reqs = (int) (Math.random() * 5) + 1;
                counter.get(time).addAndGet(reqs);
                long nums = 0;
                // time windows 5 s
                for (int i = 0; i < 5; i++) {
                    nums += counter.get(time - i).get();
                }
                System.out.println("time=" + time + ",nums=" + nums);
                if (nums > limit) {
                    System.out.println("限流了,nums=" + nums);
                }
            } catch (Exception e) {
                System.out.println("slideWindow error"+e.getMessage());
            } finally {
            }
        }, 5000, 1000, TimeUnit.MILLISECONDS);
    }

}
