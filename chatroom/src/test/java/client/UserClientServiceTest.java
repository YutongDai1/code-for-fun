package client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ServerStarter;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class UserClientServiceTest {

    UserClientService userClientService;
    Socket socket;

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
    void connect() throws IOException {
        UserClientService clientService = new UserClientService();
        clientService.connect();

        // Assert that the serverSocket is not null
        assertNotNull(clientService.getSocket());
        // test username
        InputStream inputStream = new ByteArrayInputStream("TestUser\n".getBytes());
        PrintStream outputStream = new PrintStream(new ByteArrayOutputStream());
        System.setIn(inputStream);
        System.setOut(outputStream);

        // Call the UserClientService constructor
        UserClientService userClientService = new UserClientService();

        // Verify that the userName field was set correctly
        assertEquals("TestUser", userClientService.getUserName());
    }

    @Test
    public void testLogout() throws IOException {
        UserClientService clientService = new UserClientService();
        clientService.connect();

        try {
            clientService.logout();
        } catch (IOException e) {
            fail("IOException thrown");
        }

        // Assert that the serverSocket has been closed
        assertTrue(!clientService.getSocket().isClosed());
    }

    @Test
    public void testGetAllClients() throws IOException {
        UserClientService clientService = new UserClientService();
        clientService.connect();

        try {
            clientService.getAllClients();
        } catch (IOException e) {
            fail("IOException thrown");
        }
    }

    @Test
    public void testSendToSomeOne() throws IOException {
        UserClientService clientService = new UserClientService();
        clientService.connect();

        try {
            clientService.sendToSomeOne("Hello!", "sender", "receiver");
        } catch (IOException e) {
            fail("IOException thrown");
        }
    }

    @Test
    public void testSendToAll() throws IOException {
        UserClientService clientService = new UserClientService();
        clientService.connect();

        try {
            clientService.sendToAll("Hello!", "sender");
        } catch (IOException e) {
            fail("IOException thrown");
        }
        serverThread.interrupt();
    }

    @Test
    public void testGetUserName() {
        String userName = "userName";
        userClientService.setUserName(userName);
        assertEquals(userName, userClientService.getUserName());
    }

    @Test
    void insultToSomeOne() throws IOException {
        UserClientService clientService = new UserClientService();
        clientService.connect();

        try {
            clientService.insultToSomeOne("receiver", "sender");
        } catch (IOException e) {
            fail("IOException thrown");
        }
    }

    @Test
    void getSocket() {
        Socket socket = new Socket();
        userClientService.setSocket(socket);
        assertEquals(socket, userClientService.getSocket());
    }

    @Test
    void setSocket() {
        Socket socket = new Socket();
        userClientService.setSocket(socket);
        assertEquals(socket, userClientService.getSocket());
    }

    @Test
    void testEquals() throws IOException {
        UserClientService userClientService1 = new UserClientService();
        assertNotEquals(userClientService, new UserClientService());
        assertEquals(userClientService, userClientService);
        assertNotEquals(userClientService, null);
        assertNotEquals(userClientService, new Object());
        // userClientService1.setSocket(new Socket("localhost", 1000));
        userClientService1.setUserName("userName");
        assertNotEquals(userClientService, userClientService1);
    }

    @Test
    void testHashCode() {
        UserClientService userClientService1 = new UserClientService();
        assertEquals(userClientService1.hashCode(), userClientService1.hashCode());
    }

    @Test
    void testToString() {
        UserClientService userClientService1 = new UserClientService();
        assertEquals(userClientService1.toString(), userClientService1.toString());
    }
}