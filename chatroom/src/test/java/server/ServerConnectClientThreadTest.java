package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ServerConnectClientThreadTest {
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    ServerConnectClientThread serverConnectClientThread;
    String userName;


    @BeforeEach
    void setUp() {
        // this.server = new ChatRoomServer(port);
        // this.server.startServer();
        // this.socket = new Socket("127.0.0.1", port);
        // this.dataInputStream = new DataInputStream(socket.getInputStream());
        // this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        // this.dataOutputStream.write(new ConnectMsg(name).toByteArray());
        // this.dataInputStream.readInt();
        // this.dataInputStream.read();
        // int len = this.dataInputStream.readInt();
        // this.dataInputStream.read();
        // byte[] bytes = new byte[len];
        // this.dataInputStream.read(bytes, 0, len);

        dataInputStream = new DataInputStream(System.in);
        dataOutputStream = new DataOutputStream(System.out);
        userName = "test";
        serverConnectClientThread = new ServerConnectClientThread(dataInputStream, dataOutputStream, userName);
    }

    @Test
    void run() throws IOException {
        // Create a byte array containing the data that the DataInputStream
        // should read
        byte[] data = new byte[] {
                // This array contains the bytes that represent the data you
                // want to read. For example, if you want to read an int value
                // followed by a char value, you would need to include the bytes
                // that represent those values in the correct order.

                // The first int is 22
                0, 0, 0, 22,
                // The second char is ' '
                32,
                // The third int is 33


        };
        // Create a ByteArrayInputStream to read data from the byte array
        ByteArrayInputStream bais = new ByteArrayInputStream(data);

        // Create a DataInputStream to read data from the ByteArrayInputStream
        DataInputStream dis = new DataInputStream(bais);

        // Create an instance of the anonymous inner class
        Runnable runnable = new Runnable() {
            public void run() {
                // The body of the run() method goes here

            }
        };

        // Call the run() method on the instance, passing the DataInputStream
        // as an argument
        runnable.run();

        // Add any additional test logic here, such as assertions to verify
        // the behavior of the run() method
    }

    @Test
    void getDis() {
        assertEquals(serverConnectClientThread.getDis(), dataInputStream);
    }

    @Test
    void getDos() {
        assertEquals(serverConnectClientThread.getDos(), dataOutputStream);
    }

    @Test
    void getUserName() {
        assertEquals(serverConnectClientThread.getUserName(), userName);
    }

    @Test
    void setUserName() {
        serverConnectClientThread.setUserName("test2");
        assertNotEquals(serverConnectClientThread.getUserName(), userName);
    }

    @Test
    void setDis() {
        DataInputStream dataInputStream2 = new DataInputStream(System.in);
        serverConnectClientThread.setDis(dataInputStream2);
        assertNotEquals(serverConnectClientThread.getDis(), dataInputStream);
    }

    @Test
    void setDos() {
        DataOutputStream dataOutputStream2 = new DataOutputStream(System.out);
        serverConnectClientThread.setDos(dataOutputStream2);
        assertNotEquals(serverConnectClientThread.getDos(), dataOutputStream);
    }

    @Test
    void testEquals() {
        DataInputStream dataInputStream2 = new DataInputStream(new BufferedInputStream(System.in));
        DataOutputStream dataOutputStream2 = new DataOutputStream(new FilterOutputStream(System.out));
        ServerConnectClientThread serverConnectClientThread2 = new ServerConnectClientThread(dataInputStream2, dataOutputStream2, userName);
        assertNotEquals(serverConnectClientThread, serverConnectClientThread2);
        assertEquals(serverConnectClientThread, serverConnectClientThread);
        assertNotEquals(serverConnectClientThread, null);
        assertNotEquals(serverConnectClientThread, new Object());
        assertNotEquals(serverConnectClientThread, new ServerConnectClientThread(dataInputStream, dataOutputStream, "test2"));
        assertNotEquals(serverConnectClientThread, new ServerConnectClientThread(dataInputStream, dataOutputStream2, userName));
        assertNotEquals(serverConnectClientThread, new ServerConnectClientThread(dataInputStream2, dataOutputStream, userName));
    }

    @Test
    void testHashCode() {
        assertEquals(serverConnectClientThread.hashCode(), serverConnectClientThread.hashCode());
    }
}