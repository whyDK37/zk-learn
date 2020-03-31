package curator.demo;

import static curator.lock.ZkConfig.connectString;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class SetGetWIthRootPathDemo {

  public static void main(String[] args) throws Exception {
    CuratorFramework client = CuratorFrameworkFactory.builder()
        .connectString(connectString + "/a")
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .zk34CompatibilityMode(true)
        .build();
    client.start();

    String s = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
        .forPath("/super", "1234".getBytes());
    System.out.println(s);

  }
}
