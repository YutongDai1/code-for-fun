package server;

import util.ServerUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Objects;

/**
 * Server
 */
public class Server {
  private ServerSocket serverSocket;

  /**
   * To start
   * @param port port
   * @throws IOException
   */
  public void start(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    System.out.println("Listening port on "+ port);
    ServerUtil.connect(serverSocket);
    }

  /**
   * get serverSocket
   * @return serverSocket
   */
  public ServerSocket getServerSocket() {
    return serverSocket;
  }

    /**
     * set serverSocket
     * @param serverSocket serverSocket
     */
  public void setServerSocket(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
  }

  /**
   * override equals
   * @param o o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Server server = (Server) o;
    return Objects.equals(serverSocket, server.serverSocket);
  }

    /**
     * override hashcode
     * @return hashcode
     */
  @Override
  public int hashCode() {
    return Objects.hash(serverSocket);
  }
}
