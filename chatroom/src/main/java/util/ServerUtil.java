package util;

import assignment4.Grammar;
import assignment4.JsonReader;
import constants.Constants;
import constants.Frame;
import server.ManageServerThreads;
import server.ServerConnectClientThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static util.ClientUtil.SPACE_CHAR;

/**
 * ServerUtil
 */
public interface ServerUtil {

  /**
   * connect
   * @param serverSocket serverSocket
   * @throws IOException IOException
   */
  static void connect(ServerSocket serverSocket) throws IOException {
    do {
      Socket socket = serverSocket.accept();

      DataInputStream dis = new DataInputStream(socket.getInputStream());
      DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
      int identifier = dis.readInt();
      dis.readChar();
      if (identifier == Frame.CONNECT_MESSAGE.getId()) {
        dos.writeInt(Frame.CONNECT_RESPONSE.getId());
        dos.writeChar(SPACE_CHAR);
        int length = dis.readInt();
        dis.readChar();
        byte[] bytes = new byte[length];
        dis.readFully(bytes);
        dis.readChar();
        String userName = new String(bytes, StandardCharsets.UTF_8);
        if (ManageServerThreads.getServers().size() >= Constants.MAX_CLIENT_SIZE.getIndex()) {
          dos.writeBoolean(false);
          dos.writeChar(SPACE_CHAR);
          String failedMes = "Maximum capacity exceeded, fail";
          dos.writeInt(failedMes.getBytes().length);
          dos.writeChar(SPACE_CHAR);
          dos.writeBytes(failedMes);
          dos.writeChar(SPACE_CHAR);
          cleanup(dis);
          continue;
        } else if (ManageServerThreads.getServers().containsKey(userName)) {
          dos.writeBoolean(false);
          dos.writeChar(SPACE_CHAR);
          String failedMes = "Username already exists, fail";
          dos.writeInt(failedMes.getBytes().length);
          dos.writeChar(SPACE_CHAR);
          dos.writeBytes(failedMes);
          dos.writeChar(SPACE_CHAR);
          cleanup(dis);
          continue;
        }
        dos.writeBoolean(true);
        dos.writeChar(SPACE_CHAR);
        String output =
            "There are " + ManageServerThreads.getServers().size() + " other connected clients";
        dos.writeInt(output.getBytes().length);
        dos.writeChar(SPACE_CHAR);
        dos.writeBytes(output);
        dos.writeChar(SPACE_CHAR);


        ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(dis,
            dos, userName);
        Thread thread = new Thread(serverConnectClientThread);
        thread.start();
        ManageServerThreads.addClientThread(userName, serverConnectClientThread);
        cleanup(dis);
      }

    } while (true);
  }

  /**
   * query
   * @param dis dis
   * @param dos dos
   * @throws IOException IOException
   */
  static List<String> query(DataInputStream dis, DataOutputStream dos) throws IOException {
      int len = dis.readInt();
      dis.readChar();
      byte[] b = new byte[len];
      dis.readFully(b);
      dis.readChar();

      dos.writeInt(Frame.QUERY_USERS_RESPONSE.getId());
      dos.writeChar(SPACE_CHAR);
      List<String> onlineUser = ManageServerThreads.getAllUsers();
      dos.writeInt(onlineUser.size());
      dos.writeChar(SPACE_CHAR);
      for (String user : onlineUser) {
        dos.writeInt(user.getBytes().length);
        dos.writeChar(SPACE_CHAR);
        dos.writeBytes(user);
        dos.writeChar(SPACE_CHAR);
      }
      return onlineUser;

  }

  /**
   * broadcast
   * @param dis dis
   * @param dos dos
   * @throws IOException IOException
   */
    static void broadcast (DataInputStream dis, DataOutputStream dos) throws IOException {

      int senderSize = dis.readInt();
      dis.readChar();
      byte[] b = new byte[senderSize];
      dis.readFully(b);
      dis.readChar();
      String sender = new String(b, StandardCharsets.UTF_8);
      // sender doesn't exist
      if (!ManageServerThreads.getServers().containsKey(sender)) {
        dos.writeInt(Frame.FAILED_MESSAGE.getId());
        dos.writeChar(SPACE_CHAR);
        String mes = "cannot find sender";
        dos.writeInt(mes.getBytes().length);
        dos.writeChar(SPACE_CHAR);
        dos.writeBytes(mes);
        dos.writeChar(SPACE_CHAR);
        return;
      }
      int mesSize = dis.readInt();
      dis.readChar();
      byte[] b2 = new byte[mesSize];
      dis.readFully(b2);
      dis.readChar();
      String mes = new String(b2, StandardCharsets.UTF_8);

      String resRes = "\"" + mes + "\"" + " sent to all connected clients";
      dos.writeInt(Frame.MESSAGE_SEND_RESPONSE.getId());
      dos.writeChar(SPACE_CHAR);
      dos.writeUTF(resRes);
      dos.flush();
      ConcurrentHashMap<String, ServerConnectClientThread> hm = ManageServerThreads.getServers();

      for (Entry<String, ServerConnectClientThread> o : hm.entrySet()) {
        if (o.getKey().equals(sender)) {
          continue;
        }
        ServerConnectClientThread serverConnectClientThread = o.getValue();
        DataOutputStream dos1 = serverConnectClientThread.getDos();
        dos1.writeInt(Frame.MESSAGE_RECEIVE.getId());
        dos1.writeChar(SPACE_CHAR);
        dos1.writeUTF(mes);
        dos1.writeChar(SPACE_CHAR);
        String info = "User " + sender + " " + "sent to all";
        dos1.writeUTF(info);
      }
      cleanup(dis);
    }

  /**
   * sendDirectMessage
   * @param dis dis
   * @param dos dos
   * @throws IOException IOException
   */
    static void sendDirectMessage (DataInputStream dis, DataOutputStream dos) throws IOException {
      int senderSize = dis.readInt();
      dis.readChar();
      byte[] b = new byte[senderSize];
      dis.readFully(b);
      dis.readChar();
      String sender = new String(b, StandardCharsets.UTF_8);

      int receiverSize = dis.readInt();
      dis.readChar();
      byte[] b2 = new byte[receiverSize];
      dis.readFully(b2);
      dis.readChar();
      String receiver = new String(b2, StandardCharsets.UTF_8);

      int mesSize = dis.readInt();
      dis.readChar();
      byte[] b3 = new byte[mesSize];
      dis.readFully(b3);
      dis.readChar();

      ServerConnectClientThread serverConnectClientThread = ManageServerThreads.getServerConnectClientThread(
          receiver);
      if (serverConnectClientThread == null) {
        dos.writeInt(Frame.FAILED_MESSAGE.getId());
        dos.writeChar(SPACE_CHAR);
        String mes1 = "cannot find receiver, please check the username";
        dos.writeInt(mes1.getBytes().length);
        dos.writeChar(SPACE_CHAR);
        dos.writeBytes(mes1);
        dos.writeChar(SPACE_CHAR);
        return;
      } else {
        String mes = new String(b3, StandardCharsets.UTF_8);

        String retRes = "\"" + mes + "\"" + " is sent to user " + receiver;
        dos.writeInt(Frame.MESSAGE_SEND_RESPONSE.getId());
        dos.writeChar(SPACE_CHAR);
        dos.writeUTF(retRes);
        dos.flush();

        DataOutputStream dos1 = serverConnectClientThread.getDos();
        dos1.writeInt(Frame.MESSAGE_RECEIVE.getId());
        dos1.writeChar(SPACE_CHAR);
        dos1.writeUTF(mes);
        dos1.writeChar(SPACE_CHAR);
        String info = "User " + sender + " " + "sent to you";
        dos1.writeUTF(info);
      }
      cleanup(dis);
    }

  /**
   * disconnect
   * @param dis dis
   * @param dos dos
   * @throws IOException IOException
   */
    static void disconnect(DataInputStream dis ,DataOutputStream dos) throws IOException {

      int len = dis.readInt();
      dis.readChar();
      byte[] b = new byte[len];
      dis.readFully(b);
      dis.readChar();
      String name = new String(b,StandardCharsets.UTF_8);

      ManageServerThreads.remove(name);
      dos.writeInt(Frame.DISCONNECT_RESPONSE.getId());
      dos.writeChar(SPACE_CHAR);
      dos.writeBoolean(true);
      dos.writeChar(SPACE_CHAR);
      String mes = "You are no longer connected.";
      dos.writeInt(mes.getBytes().length);
      dos.writeChar(SPACE_CHAR);
      dos.writeBytes(mes);
      dos.writeChar(SPACE_CHAR);
      dos.close();
      dis.close();
    }

  /**
   * cleanup
   * @param dis dis
   * @throws IOException IOException
   */
    static void cleanup(DataInputStream dis) throws IOException {
      int size = dis.available();
      dis.readFully(new byte[size]);
    }

  static void sendInsultMessage(DataInputStream dis, DataOutputStream dos) throws Exception {
    int senderSize = dis.readInt();
    dis.readChar();
    byte[] b = new byte[senderSize];
    dis.readFully(b);
    dis.readChar();
    String sender = new String(b, StandardCharsets.UTF_8);

    int receiverSize = dis.readInt();
    dis.readChar();
    byte[] b2 = new byte[receiverSize];
    dis.readFully(b2);
    dis.readChar();
    String receiver = new String(b2, StandardCharsets.UTF_8);

    ServerConnectClientThread serverConnectClientThread = ManageServerThreads.getServerConnectClientThread(
            receiver);
    if (serverConnectClientThread == null) {
      dos.writeInt(Frame.FAILED_MESSAGE.getId());
      dos.writeChar(SPACE_CHAR);
      String mes1 = "cannot find receiver, please check the username";
      dos.writeInt(mes1.getBytes().length);
      dos.writeChar(SPACE_CHAR);
      dos.writeBytes(mes1);
      dos.writeChar(SPACE_CHAR);
      return;
    } else {
      // get insult message from insult_grammar.json
      Grammar grammar = new Grammar();
      JsonReader jsonReader = new JsonReader();
      URL resFile = ServerUtil.class.getClassLoader().getResource("insult_grammar.json");
      File file = Paths.get(Objects.requireNonNull(resFile).toURI()).toFile();
      String absolutePath = file.getAbsolutePath();
      String mes = grammar.textGenerator("start", jsonReader.jsonProcess(absolutePath));


      String retRes = "\"" + mes + "\"" + " is sent to user " + receiver + ".\n please don't send insult message again, thank you!";
      dos.writeInt(Frame.MESSAGE_SEND_RESPONSE.getId());
      dos.writeChar(SPACE_CHAR);
      dos.writeUTF(retRes);
      dos.flush();

      DataOutputStream dos1 = serverConnectClientThread.getDos();
      dos1.writeInt(Frame.MESSAGE_RECEIVE.getId());
      dos1.writeChar(SPACE_CHAR);
      dos1.writeUTF(mes);
      dos1.writeChar(SPACE_CHAR);
      String info = "User " + sender + " " + "sent insult message to you";
      dos1.writeUTF(info);
    }
    cleanup(dis);

  }
}