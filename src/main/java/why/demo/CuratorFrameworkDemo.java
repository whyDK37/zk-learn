package why.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorFrameworkDemo {

    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .zk34CompatibilityMode(true)
                .namespace("wd")
                .build();


        System.out.println("CuratorFramework:" + client.toString());
        System.out.println("getNamespace:" + client.getNamespace());
        System.out.println("isZk34CompatibilityMode:" + client.isZk34CompatibilityMode());
        System.out.println("getSchemaSet:" + client.getSchemaSet());
    }
}
