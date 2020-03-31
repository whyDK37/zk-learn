package curator.demo;

import static curator.lock.ZkConfig.connectString;

import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class InterProcessReadWriteLockDemo {

  public static void main(String[] args) throws Exception {
    CuratorFramework client = CuratorFrameworkFactory.builder()
        .connectString(connectString)
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .zk34CompatibilityMode(true)
        .build();
    client.start();

    InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(client,
        "/lock/rw");

    interProcessReadWriteLock.readLock().acquire();
    interProcessReadWriteLock.readLock().acquire();

    interProcessReadWriteLock.writeLock().acquire();

    TimeUnit.HOURS.sleep(1L);
  }
}
