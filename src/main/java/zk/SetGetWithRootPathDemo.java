package zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;

public class SetGetWithRootPathDemo {

  public static void main(String[] args) throws Exception {
    ZooKeeper zk = new ZooKeeper("127.0.0.1:2181/a", 1000, event -> {

    });

    byte[] data = zk.getData("/super", false, null);
    System.out.println(new String(data));
  }
}
