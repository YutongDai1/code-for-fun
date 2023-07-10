package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketAddress;

import static constants.Constants.TCP_SERVER_PORT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ServerTest {

    Server server;
    ServerSocket serverSocket;

    @BeforeEach
    void setUp() throws IOException {
        server = new Server();
        serverSocket = new ServerSocket();
        SocketAddress socketAddress = serverSocket.getLocalSocketAddress();
        serverSocket.bind(socketAddress);
        server.setServerSocket(serverSocket);
    }

    @Test
    void start() {
        Thread thread = new Thread(() -> {
            try {
                server.start(TCP_SERVER_PORT.getIndex());
            } catch (IOException e) {
                System.out.println("Server is already running");
            }
        });
        thread.start();
        assertNotEquals(serverSocket, null);
    }

    @Test
    void getServerSocket() {
        assertEquals(serverSocket, server.getServerSocket());
    }

    @Test
    void setServerSocket() {
        server.setServerSocket(serverSocket);
        assertEquals(serverSocket, server.getServerSocket());
    }

    @Test
    void testEquals() {
        Server server1 = new Server();
        server1.setServerSocket(serverSocket);
        assertEquals(server1, server);
        assertEquals(server, server);
        assertNotEquals(server, null);
        assertNotEquals(server, new Object());


    }

    @Test
    void testHashCode() {
        Server server1 = new Server();
        server1.setServerSocket(serverSocket);
        assertEquals(server.hashCode(), server.hashCode());
    }
}