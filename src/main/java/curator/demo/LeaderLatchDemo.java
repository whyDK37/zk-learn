package curator.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class LeaderLatchDemo {

  public static void main(String[] args) throws Exception {
    CuratorFramework client = CuratorFrameworkFactory.builder()
        .connectString("127.0.0.1:2181")
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .zk34CompatibilityMode(true)
        .namespace("wd")
        .build();
    client.start();

    LeaderLatch leaderLatch = new LeaderLatch(client, "/latch");
    leaderLatch.addListener(new LeaderLatchListener() {
      @Override
      public void isLeader() {
        System.out.println("is leader");
      }

      @Override
      public void notLeader() {
        System.out.println("not leader");
      }
    });

    leaderLatch.start();

    leaderLatch.await();
    System.out.println(leaderLatch.hasLeadership());

    leaderLatch.close();
  }
}
