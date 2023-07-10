package client;

import util.ClientUtil;

import java.io.DataInputStream;
import java.util.Objects;

/**
 * client connect thread
 */
public class ClientConnectThread implements  Runnable{
  private DataInputStream dis;

  /**
   * DataInputStream
   * @param dis dis
   */
  public ClientConnectThread(DataInputStream dis) {
    this.dis = dis;
  }

  /**
   * run
   */
  @Override
  public void run() {
  boolean[] booleans = new boolean[1];
  booleans[0] = true;
    while (booleans[0])
    {
      ClientUtil.Run(dis,booleans);
    }
  }

  /**
   * get dis
   * @return dis
   */
  public DataInputStream getDis() {
    return dis;
  }

    /**
     * set dis
     * @param dis dis
     */
  public void setDis(DataInputStream dis) {
    this.dis = dis;
  }

    /**
     * equals
     * @param o o
     * @return
     */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClientConnectThread that = (ClientConnectThread) o;
    return Objects.equals(dis, that.dis);
  }

    /**
     * hashcode
     * @return hashcode
     */
  @Override
  public int hashCode() {
    return Objects.hash(dis);
  }

    /**
     * to string
     * @return
     */
  @Override
  public String toString() {
    return "ClientConnectThread{" +
            "dis=" + dis +
            '}';
  }
}
