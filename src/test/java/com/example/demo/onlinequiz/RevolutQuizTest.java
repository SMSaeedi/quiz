package com.example.demo.onlinequiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RevolutQuizTest {
    RevolutQuiz revolutQuiz;

    @BeforeEach
    public void setUp() {
        revolutQuiz = new RevolutQuiz();
        revolutQuiz.serverList.clear();
    }

    @Test
    public void successful_test_register_new_server() {
        String ip = "192.168.1.1";
        InetAddress mockInetAdd = mock(InetAddress.class);

        when(mockInetAdd.getHostAddress()).thenReturn(ip);
        mockStatic(InetAddress.class).when(() -> InetAddress.getByName(ip)).thenReturn(mockInetAdd);

        revolutQuiz.registerServer(ip);

        assertEquals(1, revolutQuiz.serverList.size());
        assertTrue(revolutQuiz.serverList.contains(ip));
    }

    @Test
    public void threshold_exception() {
        for (int i = 1; i <= 10; i++)
            revolutQuiz.serverList.add("192.168.1." + i);

        var exception = assertThrows(ServerException.class, () -> revolutQuiz.registerServer("192.168.1.10"));
        assertEquals("out of threshold", exception.getMessage());
    }

    @Test
    public void unknown_host_exception() {
        var unknownIP = assertThrows(ServerException.class, () -> revolutQuiz.registerServer("invalid.server"));
        assertEquals("unknown host", unknownIP.getMessage());
        revolutQuiz.serverList.clear();
    }

    @Test
    public void ip_exist_already_exception() {
        String newIP = "192.168.1.1";
        revolutQuiz.serverList.add(newIP);
        var existIP = assertThrows(ServerException.class, () -> revolutQuiz.registerServer(newIP));
        assertEquals("IP address already exist", existIP.getMessage());
        revolutQuiz.serverList.clear();
    }

    @Test
    public void invalid_ip_already() {
        var invalidLength = assertThrows(ServerException.class, () -> revolutQuiz.registerServer("192.168.1.1" + "-"));
        assertEquals("unknown host", invalidLength.getMessage());
        revolutQuiz.serverList.clear();
    }

    @Test
    public void find_next_server() {
        revolutQuiz.serverList.add("192.168.1.1");
        revolutQuiz.serverList.add("192.168.1.2");
        revolutQuiz.serverList.add("192.168.1.5");
        revolutQuiz.serverList.add("192.168.1.6");
        revolutQuiz.serverList.add("192.168.1.3");
        assertEquals(revolutQuiz.findNextServer(), "192.168.1.1");
        assertEquals(revolutQuiz.findNextServer(), "192.168.1.2");
        assertEquals(revolutQuiz.findNextServer(), "192.168.1.5");
        assertEquals(revolutQuiz.findNextServer(), "192.168.1.6");
        assertEquals(revolutQuiz.findNextServer(), "192.168.1.3");
    }
}