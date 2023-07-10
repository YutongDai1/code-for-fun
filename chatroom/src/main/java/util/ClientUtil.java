package util;

import client.ClientConnectThread;
import constants.Frame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *  ClientUtil
 */
public interface ClientUtil {

  char SPACE_CHAR = ' ';

  /**
   *  To connect
   * @param socket socket
   * @param userName userName
   * @throws IOException IOException
   */
  static void connect(Socket socket,String userName) throws IOException {
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    dos.writeInt(Frame.CONNECT_MESSAGE.getId());
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(userName.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(userName);
    dos.writeChar(SPACE_CHAR);
  }

  /**
   * getAllClients, query
   * @param socket  socket
   * @param userName userName
   * @throws IOException IOException
   */
  static void getAllClients(Socket socket,String userName) throws IOException {
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    dos.writeInt(Frame.QUERY_CONNECTED_USERS.getId());
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(userName.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(userName);
    dos.writeChar(SPACE_CHAR);
    dos.flush();
  }

  /**
   * send direct message
   * @param socket socket
   * @param mes mes
   * @param sender senderName
   * @param receiver receiverName
   * @throws IOException IOException
   */
  static void sendToSomeOne(Socket socket,String mes,String sender,String receiver) throws IOException {
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    //send mes to one
    dos.writeInt(Frame.DIRECT_MESSAGE.getId());
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(sender.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(sender);
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(receiver.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(receiver);
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(mes.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(mes);
    dos.writeChar(SPACE_CHAR);
  }

  /**
   * send insult message
   * @param socket socket
   * @param sender senderName
   * @param receiver receiverName
   * @throws IOException IOException
   */
  static void insultToSomeOne(Socket socket,String sender,String receiver) throws IOException {
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    //send mes to one
    dos.writeInt(Frame.SEND_INSULT.getId());
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(sender.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(sender);
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(receiver.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(receiver);
    dos.writeChar(SPACE_CHAR);
  }

  /**
   * broadcast
   * @param socket socket
   * @param mes mes
   * @param userName userName
   * @throws IOException IOException
   */
  static void sendToAll(Socket socket,String mes,String userName) throws IOException {
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    //send mes to one
    dos.writeInt(Frame.BROADCAST_MESSAGE.getId());
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(userName.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(userName);
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(mes.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(mes);
    dos.writeChar(SPACE_CHAR);
    //dos.flush();
  }

  /**
   *  receiveConnectionResult
   * @param socket socket
   * @throws IOException IOException
   */
  static void receiveConnectionResult(Socket socket) throws IOException {
    DataInputStream dis = new DataInputStream(socket.getInputStream());
    // get length of the message and skip the blank char, then read the message and return string
    // skip the blank char
    dis.readInt();
    dis.readChar();
    boolean success = dis.readBoolean();
    dis.readChar();
    int len = dis.readInt();
    dis.readChar();
    byte[] b = new byte[len];
    dis.readFully(b);
    dis.readChar();
    // if connect successfully then create a thread
    System.out.println(new String(b, StandardCharsets.UTF_8));
    if(success)
    {
      ClientConnectThread clientConnectThread = new ClientConnectThread(dis);
      Thread thread = new Thread(clientConnectThread);
      thread.start();
      cleanup(dis);
    }
    else
    {
      socket.close();
    }

  }

  /**
   * Run
   * @param dis dis
   * @param booleans booleans
   */
  static void Run(DataInputStream dis,boolean[] booleans)
  {
    try {
      //System.out.println("waiting message to come");
      int identifier = dis.readInt();
      dis.readChar();
      if (identifier== Frame.QUERY_USERS_RESPONSE.getId())
      {
        System.out.println("query");
        int number = dis.readInt();
        dis.readChar();
        System.out.println("\n========online clients======");

          for (int i = 0; i < number; i++) {
            int length = dis.readInt();
            dis.readChar();
            byte[] clientNameByteList = new byte[length];
            dis.readFully(clientNameByteList);
            dis.readChar();
            System.out.println("client ： " + new String(clientNameByteList, StandardCharsets.UTF_8));
          }

        cleanup(dis);
      }
      else if(identifier == Frame.MESSAGE_RECEIVE.getId())
      {
        String output = dis.readUTF();
        dis.readChar();
        String info = dis.readUTF();
        System.out.print(info + ": ");
        System.out.println(output);
        cleanup(dis);
      }
      else if(identifier == Frame.MESSAGE_SEND_RESPONSE.getId())
      {
        String output = dis.readUTF();
        System.out.println(output);
        cleanup(dis);
      }
      else if(identifier == Frame.FAILED_MESSAGE.getId())
      {
        int len = dis.readInt();
        dis.readChar();
        byte[] b = new byte[len];
        dis.readFully(b);
        dis.readChar();
        String output = new String(b,StandardCharsets.UTF_8);
        System.out.println(output);
        cleanup(dis);
      }
      else if(identifier == Frame.DISCONNECT_RESPONSE.getId())
      {
        boolean success = dis.readBoolean();
        dis.readChar();
        int len = dis.readInt();
        dis.readChar();
        byte[] b = new byte[len];
        dis.readFully(b);
        dis.readChar();
        System.out.println(new String(b,StandardCharsets.UTF_8));
        if(success)
        {
          booleans[0] = false;
          dis.close();
        }
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * logoff
   * @param socket socket
   * @param name name
   * @throws IOException IOException
   */
  static void logoff(Socket socket, String name) throws IOException {
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    dos.writeInt(Frame.DISCONNECT_MESSAGE.getId());
    dos.writeChar(SPACE_CHAR);
    dos.writeInt(name.getBytes().length);
    dos.writeChar(SPACE_CHAR);
    dos.writeBytes(name);
    dos.writeChar(SPACE_CHAR);
  }

  /**
   * cleanup
   * @param dis  dis
   * @throws IOException IOException
   */
  static void cleanup(DataInputStream dis) throws IOException {
    int size = dis.available();
    dis.readFully(new byte[size]);
  }


}
