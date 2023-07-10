package client;

import constants.Constants;
import util.ClientUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * user client service
 */
public class UserClientService {
  private Socket socket;
  private String userName;

  /**
   * constructor
   * set userName
   */
  public UserClientService()
  {
    System.out.println("please Type userName");
    Scanner scanner = new Scanner(System.in);
    if (scanner.hasNextLine()) {
      userName = scanner.nextLine();
    } else {
      System.out.println("Without any input, the default name(guest) is used");
      userName = "guest" + new Random().nextInt(1000);
    }
  }

  /**
   * To connect with server
   */
  public void connect() throws IOException {
      socket = new Socket(InetAddress.getLocalHost(), Constants.TCP_SERVER_PORT.getIndex());

      // connect
      ClientUtil.connect(socket,userName);
      // get connection result
      ClientUtil.receiveConnectionResult(socket);

  }

  /**
   * log out
   * @throws IOException IOException
   */
  public void logout() throws IOException {
    // to be finished
    ClientUtil.logoff(socket,userName);
  }

  /**
   *  get all online users
   */
  public void getAllClients() throws IOException {
    ClientUtil.getAllClients(socket,userName);
  }

  /**
   * send direct message
   * @param mes mes
   * @param sender sender
   * @param receiver receiver
   * @throws IOException  IOException
   */
  public void sendToSomeOne(String mes,String sender,String receiver) throws IOException {
    ClientUtil.sendToSomeOne(socket,mes,sender,receiver);
  }

  /**
   * broadcast message
   * @param mes mes
   * @param userName userName
   * @throws IOException IOException
   */
  public void sendToAll(String mes,String userName) throws IOException {
   ClientUtil.sendToAll(socket,mes,userName);
  }

  /**
   * get userName
   * @return get userName
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

  public void insultToSomeOne(String userName, String res) throws IOException {
    ClientUtil.insultToSomeOne(socket,userName,res);
  }

  /** get socket
   * @return socket
   */
  public Socket getSocket() {
    return socket;
  }

    /** set socket
     * @param socket socket
     */
  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  /**
   * override equals
   * @return to string
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserClientService that = (UserClientService) o;
    return Objects.equals(userName, that.userName);
  }

    /**
     * override hashcode
     * @return hashcode
     */
  @Override
  public int hashCode() {
    return Objects.hash(socket, userName);
  }

    /**
     * override toString
     * @return to string
     */
  @Override
  public String toString() {
    return "UserClientService{" +
            "socket=" + socket +
            ", userName='" + userName + '\'' +
            '}';
  }
}
