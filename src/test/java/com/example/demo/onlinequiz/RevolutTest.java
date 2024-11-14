package com.example.demo.onlinequiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RevolutTest {
    Revolut revolut;

    @BeforeEach
    public void setUp() {
        revolut = new Revolut();
        revolut.serverList.clear();
    }

    @Test
    public void successful_test_register_new_server() {
        String ip = "192.168.1.1";
        InetAddress mockInetAdd = mock(InetAddress.class);

        try (MockedStatic<InetAddress> mockedInetAddress = mockStatic(InetAddress.class)) {
            when(mockInetAdd.getHostAddress()).thenReturn(ip);
            mockedInetAddress.when(() -> InetAddress.getByName(ip)).thenReturn(mockInetAdd);

            revolut.registerServer(ip);

            assertEquals(1, revolut.serverList.size());
            assertTrue(revolut.serverList.contains(ip));
        }
    }

    @Test
    public void threshold_exception() {
        for (int i = 1; i <= 10; i++)
            revolut.serverList.add("192.168.1." + i);

        var exception = assertThrows(ServerException.class, () -> revolut.registerServer(revolut.serverList.get(revolut.serverList.size()-1)));
        assertEquals("out of domain range!", exception.getMessage());
    }

    @Test
    public void unknown_host_exception() {
        try (MockedStatic<InetAddress> mockedInetAddress = mockStatic(InetAddress.class)) {
            mockedInetAddress.when(() -> InetAddress.getByName("invalid.server"))
                    .thenThrow(new ServerException("unknown host"));

            var unknownIP = assertThrows(ServerException.class, () -> revolut.registerServer("invalid.server"));
            assertEquals("unknown host", unknownIP.getMessage());
        }
    }

    @Test
    public void ip_exist_already_exception() {
        String newIP = "192.168.1.1";
        revolut.serverList.add(newIP);
        var existIP = assertThrows(ServerException.class, () -> revolut.registerServer(newIP));
        assertEquals("duplicated IP address!", existIP.getMessage());
    }

    @Test
    public void invalid_ip() {
        var invalidLength = assertThrows(ServerException.class, () -> revolut.registerServer("192.168.1.1" + "1"));
        assertEquals("out of domain range!", invalidLength.getMessage());
    }

    @Test
    public void null_ip() {
        var nullIp = assertThrows(ServerException.class, () -> revolut.registerServer(null));
        assertEquals("null ip address!", nullIp.getMessage());
    }

    @Test
    public void find_next_server() {
        revolut.serverList.add("192.168.1.1");
        revolut.serverList.add("192.168.1.2");
        revolut.serverList.add("192.168.1.5");
        revolut.serverList.add("192.168.1.6");
        revolut.serverList.add("192.168.1.3");
        assertEquals(revolut.findNextServer(), "192.168.1.1");
        assertEquals(revolut.findNextServer(), "192.168.1.2");
        assertEquals(revolut.findNextServer(), "192.168.1.5");
        assertEquals(revolut.findNextServer(), "192.168.1.6");
        assertEquals(revolut.findNextServer(), "192.168.1.3");
    }
}