package server;

import constants.Frame;
import util.ServerUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  server thread
 */
public class ServerConnectClientThread implements Runnable{

  private DataInputStream dis;
  private DataOutputStream dos;
  private String userName;

  /**
   *
   * @param dis DataInputStream
   * @param dos DataOutputStream
   * @param userName userName
   */
  public ServerConnectClientThread(DataInputStream dis,DataOutputStream dos,String userName) {
    this.dis = dis;
    this.dos = dos;
    this.userName = userName;
  }

  /**
   *   run
   *   keep running
   */
  public void run() {
    ConcurrentHashMap<String, ServerConnectClientThread> servers = ManageServerThreads.getServers();
    do {
      try {
        int identifier = dis.readInt();
        dis.readChar();
        if (identifier == Frame.QUERY_CONNECTED_USERS.getId())
        {
         ServerUtil.query(dis,dos);
        }
        else if(identifier == Frame.DIRECT_MESSAGE.getId())
        {
          ServerUtil.sendDirectMessage(dis,dos);
        }
        else if(identifier == Frame.BROADCAST_MESSAGE.getId())
        {
          ServerUtil.broadcast(dis,dos);
        }
        else if(identifier == Frame.DISCONNECT_MESSAGE.getId())
        {
          ServerUtil.disconnect(dis,dos);
          break;
        } else if (identifier == Frame.SEND_INSULT.getId()) {
          ServerUtil.sendInsultMessage(dis, dos);
        }
        } catch(Exception e){
          throw new RuntimeException(e);
        }
    } while (servers.containsKey(userName));
  }

  /**
   * get DataInputStream
   * @return get
   */
  public DataInputStream getDis() {
    return dis;
  }

  /**
   * get dos
   * @return get
   */
  public DataOutputStream getDos() {
    return dos;
  }

  /**
   * get username
   * @return get
   */
  public String getUserName() {
    return userName;
  }

  /**
   * set userName
   * @param userName set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * set dis
   * @param dis
   */
  public void setDis(DataInputStream dis) {
    this.dis = dis;
  }

    /**
     * set dos
     * @param dos
     */
  public void setDos(DataOutputStream dos) {
    this.dos = dos;
  }

    /**
     * equals
     * @param o
     * @return
     */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ServerConnectClientThread that = (ServerConnectClientThread) o;
    return Objects.equals(dis, that.dis) && Objects.equals(dos, that.dos) && Objects.equals(userName, that.userName);
  }

    /**
     * hashcode
     * @return
     */
  @Override
  public int hashCode() {
    return Objects.hash(dis, dos, userName);
  }
}
