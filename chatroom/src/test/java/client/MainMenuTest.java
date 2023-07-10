package client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ServerStarter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;


class MainMenuTest {
    Socket socket;
    UserClientService userClientService;
    MainMenu mainMenu;

    static ServerStarter serverStarter = new ServerStarter();
    static Thread serverThread = new Thread(() -> {
        // Start the server on the local machine
        try {
            serverStarter.main(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    @BeforeEach
    void setUp() {
        // System.out.println("please Type userName");
        userClientService = new UserClientService();
        userClientService.setSocket(socket);
        // Start the server thread
    }

    @BeforeAll
    static void beforeAll() {
        // Start the server thread
        serverThread.start();
    }

    @AfterEach
    void tearDown() {
        // Stop the server thread
        serverThread.interrupt();
    }

    @Test
    public void testSendToAll() {
    }


    @Test
    void testMainMenu() throws Exception {
        mainMenu = new MainMenu(userClientService);
        mainMenu.setLoop(false);
        UserClientService clientService = new UserClientService();
        clientService.connect();

        // Assert that the serverSocket is not null
        // assertNotNull(clientService.getSocket());
        // Set up the input for the Scanner.
        // This simulates the user entering the "who" command.
        String input = "bob" + System.lineSeparator() + "who" + System.lineSeparator() + "?"+ System.lineSeparator() + "@all hi" + System.lineSeparator() + "!bob" + System.lineSeparator() + "logoff" + System.lineSeparator();
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        // PrintStream outputStream = new PrintStream(new ByteArrayOutputStream());
        System.setIn(inputStream);
        // System.setOut(outputStream);
        mainMenu.mainMenu();


        // Verify that the userClientService.getAllClients() method was called.
        // This verifies that the "who" command was handled correctly.
    }

    @Test
    void getUserClientService() {
        mainMenu = new MainMenu(userClientService);
        assertEquals(mainMenu.getUserClientService(), userClientService);
    }

    @Test
    void isLoop() {
        mainMenu = new MainMenu(userClientService);
        assertTrue(mainMenu.isLoop());
    }

    @Test
    void setLoop() {
        mainMenu = new MainMenu(userClientService);
        mainMenu.setLoop(false);
        assertFalse(mainMenu.isLoop());
    }

    @Test
    void testEquals() {
        mainMenu = new MainMenu(userClientService);
        MainMenu mainMenu1 = new MainMenu(userClientService);
        assertEquals(mainMenu, mainMenu);
        assertNotEquals(mainMenu, null);
        assertNotEquals(mainMenu, new Object());
        assertNotEquals(mainMenu, new MainMenu(null));
        assertNotEquals(mainMenu, new MainMenu(new UserClientService()));
        mainMenu1.setLoop(false);
        assertNotEquals(mainMenu, mainMenu1);
    }

    @Test
    void testHashCode() {
        mainMenu = new MainMenu(userClientService);
        assertEquals(mainMenu.hashCode(), mainMenu.hashCode());
    }

    @Test
    void printCommand() {
        mainMenu = new MainMenu(userClientService);
        mainMenu.printCommand();
    }

    @Test
    void testToString() {
        String s = "MainMenu{userClientService=" + userClientService + ", loop=" + true + "}";
        mainMenu = new MainMenu(userClientService);
        assertEquals(mainMenu.toString(), s);

    }
}