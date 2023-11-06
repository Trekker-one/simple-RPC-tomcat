package yuzhe.com.loadbalance;

import yuzhe.com.common.URL;

import java.util.List;
import java.util.Random;

public class LoadBalancer {

    // 简单模拟随机
    public static URL random(List<URL> urls) {
        Random random = new Random();
        int i = random.nextInt(urls.size());
        return urls.get(i);
    }
}
