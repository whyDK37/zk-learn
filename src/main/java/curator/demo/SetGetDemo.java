package curator.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import static curator.lock.ZkConfig.connectString;

public class SetGetDemo {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .zk34CompatibilityMode(true)
                .build();
        client.start();


//        String s = client.create().forPath("/super", "1234".getBytes());
//        System.out.println(s);
        Stat stat = client.setData().forPath("/super", "new".getBytes());
        System.out.println(stat);

        client.sync().forPath("/super");
        byte[] bytes = client.getData().forPath("/super");
        System.out.println(new String(bytes));
    }
}
