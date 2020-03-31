package zk;

import java.io.IOException;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Demo {

  public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
    ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
      @Override
      public void process(WatchedEvent event) {

      }
    });

    zk.sync("/super", (rc, path, ctx) -> {

    }, null);
    byte[] data = zk.getData("/super", false, null);
    System.out.println(new String(data));
  }
}
