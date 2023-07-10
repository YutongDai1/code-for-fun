package client;

import constants.Regex;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  MainMenu
 */
public class MainMenu {

  private final UserClientService userClientService;
  private boolean loop = true;

  /**
   *
   * @param userClientService   userClientService
   */
  public MainMenu(UserClientService userClientService) {
    this.userClientService = userClientService;
  }

  /**
   *
   * @throws IOException IOException
   */
  public void  mainMenu() throws Exception {
    userClientService.connect();
    Scanner scanner = new Scanner(System.in);

    do {
      System.out.println("please enter your command (use \"?\" to get all valid commands)");
        String key = scanner.nextLine();
        if(key.equals("who"))
        {
          userClientService.getAllClients();
          continue;
        }
        else if(key.equals("logoff"))
        {
          loop = false;
          userClientService.logout();
          continue;
        }
        else if(key.equals("?"))
        {
          printCommand();
          continue;
        }

        Pattern pattern = Pattern.compile(Regex.SEND_MESSAGE_REGEX.getRegex());
        Matcher matcher = pattern.matcher(key);
        //if the first character is @
        if (matcher.find()) {
          String res = matcher.group();
          // get the message
          String mes = key.substring(res.length());
          // get the name or all
          res = res.substring(1, res.length()-1);
          res = res.toLowerCase();
          if (res.equals("all")) {
            userClientService.sendToAll(mes, userClientService.getUserName());
          } else {
            userClientService.sendToSomeOne(mes, userClientService.getUserName(), res);
          }
          continue;
        }

        pattern = Pattern.compile(Regex.SEND_INSULT_REGEX.getRegex());
        matcher = pattern.matcher(key);
        // if the first character is !
        if (matcher.find()) {
          String res = matcher.group(2);

          // String mes = key.substring(res.length());
          // get the name or all
          res = res.toLowerCase();
          userClientService.insultToSomeOne(userClientService.getUserName(), res);
          continue;
        }

        // other cases just send to all
        userClientService.sendToAll(key, userClientService.getUserName());

    }while (loop);
  }

  /**
   * print all command
   */
  protected void printCommand()
    {
      System.out.println("""
              • logoff: sends a DISCONNECT_MESSAGE to the server
              • who: sends a QUERY_CONNECTED_USERS to the server
              • @user: sends a DIRECT_MESSAGE to the specified user to the server
              • @all: sends a BROADCAST_MESSAGE to the server, to be sent to all users connected
              • !user: sends a SEND_INSULT message to the server, to be sent to the specified user""");
    }

    /**
     * get UserClientService
     * @return userClientService
     */
  public UserClientService getUserClientService() {
    return userClientService;
  }

    /**
     * get loop
     * @return loop
     */
  public boolean isLoop() {
    return loop;
  }

    /**
     * set loop
     * @param loop loop
     */
  public void setLoop(boolean loop) {
    this.loop = loop;
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
    MainMenu mainMenu = (MainMenu) o;
    return loop == mainMenu.loop && Objects.equals(userClientService, mainMenu.userClientService);
  }

    /**
     * override hashcode
     * @return hashcode
     */
  @Override
  public int hashCode() {
    return Objects.hash(userClientService, loop);
  }

    /**
     * override toString
     * @return string
     */
  @Override
  public String toString() {
    return "MainMenu{" +
            "userClientService=" + userClientService +
            ", loop=" + loop +
            '}';
  }
}
