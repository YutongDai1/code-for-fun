package client;

/**
 * To start client
 */
public class ClientStarter {
  public static void main(String[] args) throws Exception {
    UserClientService userClientService = new UserClientService();
    MainMenu menu = new MainMenu(userClientService);
    menu.mainMenu();
  }
}
