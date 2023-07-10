package server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Use concurrentHashMap to manage server threads
 */
public class ManageServerThreads {
  private static ConcurrentHashMap<String,ServerConnectClientThread> servers = new ConcurrentHashMap<>();

  /**
   * add
   * @param userName userName
   * @param serverConnectClientThread serverConnectClientThread
   */
  public static void addClientThread(String userName,ServerConnectClientThread serverConnectClientThread){
    servers.put(userName,serverConnectClientThread);
  }

  /**
   * get corresponding  ServerConnectClientThread
   * @param userName userName
   * @return ServerConnectClientThread
   */
  public static ServerConnectClientThread getServerConnectClientThread(String userName){
    return servers.get(userName);
  }

  /**
   * get all users
   * @return all users
   */
  public static List<String> getAllUsers(){
    Iterator<String > iterator = servers.keySet().iterator();
    List<String> list = new ArrayList<>();
    while (iterator.hasNext()){
      list.add(iterator.next());
    }
    return list;
  }

  /**
   * Remove thread by name
   * @param userName remove
   */
  public static void remove(String userName){
    servers.remove(userName);

  }


  /**
   * get ConcurrentHashMap
   * @return ConcurrentHashMap
   */
  public static ConcurrentHashMap<String, ServerConnectClientThread> getServers() {
    return servers;
  }

    /**
     * set ConcurrentHashMap
     * @param servers ConcurrentHashMap
     */
  public static void setServers(ConcurrentHashMap<String, ServerConnectClientThread> servers) {
    ManageServerThreads.servers = servers;
  }

    /**
     * override equals
     * @param o o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManageServerThreads that = (ManageServerThreads) o;

        return Objects.equals(servers, that.servers) ;
    }

    /**
     * override hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
      return Objects.hash(servers);
    }
  /**
   * override toString
   * @return String
   */
  @Override
  public String toString() {
    return "ManageServerThreads{" +
            "servers=" + servers +
            '}';
  }
}
