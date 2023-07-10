package client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.DataInputStream;

import static org.junit.jupiter.api.Assertions.*;

class ClientConnectThreadTest {

    ClientConnectThread clientConnectThread;
    ClientStarter clientStarter;
    DataInputStream dataInputStream;

    @BeforeEach
    void setUp() {
        clientStarter = new ClientStarter();
        clientConnectThread = new ClientConnectThread(dataInputStream);
        // dataInputStream = new DataInputStream(System.in);
        dataInputStream = new DataInputStream(System.in);

    }

    @Test
    void getDis() {
        clientConnectThread.setDis(dataInputStream);
        assertEquals(dataInputStream, clientConnectThread.getDis());
    }

    @Test
    void setDis() {
        clientConnectThread.setDis(dataInputStream);
        assertEquals(dataInputStream, clientConnectThread.getDis());
    }

    @Test
    void testEquals() {
        ClientConnectThread clientConnectThread1 = new ClientConnectThread(dataInputStream);
        clientConnectThread1.setDis(dataInputStream);
        assertEquals(clientConnectThread, clientConnectThread);
        assertNotEquals(clientConnectThread, null);
        assertNotEquals(clientConnectThread, new Object());
        clientConnectThread1.setDis(null);
        // assertEquals(clientConnectThread, clientConnectThread1);
    }

    @Test
    void testHashCode() {
        assertEquals(clientConnectThread.hashCode(), clientConnectThread.hashCode());
    }

    @Test
    void testToString() {
        String string = "ClientConnectThread{" +
                "dis=" + dataInputStream +
                '}';
        assertEquals(clientConnectThread.toString(), clientConnectThread.toString());
    }
}