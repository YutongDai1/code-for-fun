package server;

import constants.Constants;

import java.io.IOException;

/**
 * To start server
 */
public class ServerStarter {
  public static void main(String[] args) throws IOException {
    Server server = new Server();
    server.start(Constants.TCP_SERVER_PORT.getIndex());
  }
}
