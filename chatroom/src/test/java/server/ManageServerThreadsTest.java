package server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class ManageServerThreadsTest {
    ManageServerThreads manageServerThreads;
    ConcurrentHashMap<String, ServerConnectClientThread> serverThreads;

    @BeforeEach
    void setUp() {
        manageServerThreads = new ManageServerThreads();
        serverThreads = new ConcurrentHashMap<>();
    }

    @Test
    void setServers() {
        ManageServerThreads.setServers(serverThreads);
        assertEquals(serverThreads, ManageServerThreads.getServers());
    }

    @Test
    void testEquals() {
        ManageServerThreads manageServerThreads1 = new ManageServerThreads();
        ManageServerThreads.setServers(serverThreads);
        assertEquals(manageServerThreads1, manageServerThreads);
        assertEquals(manageServerThreads, manageServerThreads);
        assertNotEquals(manageServerThreads, null);
        assertNotEquals(manageServerThreads, new Object());
        ManageServerThreads.setServers(null);
        // assertEquals(manageServerThreads, manageServerThreads1);
    }

    @Test
    void testHashCode() {
        assertEquals(manageServerThreads.hashCode(), manageServerThreads.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(manageServerThreads.toString(), manageServerThreads.toString());
    }
}