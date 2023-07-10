package client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.ServerStarter;

import java.io.*;
import java.net.Socket;

class ClientStarterTest {

    UserClientService userClientService;
    Socket serverSocket;

    static ServerStarter serverStarter = new ServerStarter();
    static ClientStarter clientStarter = new ClientStarter();
    static Thread serverThread = new Thread(() -> {
        // Start the server on the local machine
        try {
            serverStarter.main(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });
    static Thread clientThread = new Thread(new Runnable() {
        @Override
        public void run() {
            // Start the server on the local machine
            try {
                clientStarter.main(new String[]{});
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    });


    @BeforeEach
    void setUp() {
        // System.out.println("please Type userName");
        userClientService = new UserClientService();
        userClientService.setSocket(serverSocket);


    }

    @BeforeAll
    static void beforeAll() {
        // Start the server thread
        serverThread.start();
        clientThread.start();
    }

    @AfterEach
    void tearDown() {
        // Stop the server thread
        serverThread.interrupt();
        clientThread.interrupt();
    }
    @Test
    void main() throws Exception {
        // Create objects for the UserClientService and MainMenu classes
        // MainMenu menu = new MainMenu(userClientService);
        // try {
        //     menu.mainMenu();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}