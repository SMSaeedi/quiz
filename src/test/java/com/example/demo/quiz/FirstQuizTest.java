package com.example.demo.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FirstQuizTest {
    FirstQuiz firstQuiz;

    @BeforeEach
    public void setUp() {
        firstQuiz = new FirstQuiz();
        firstQuiz.serverList.clear();
    }

    @Test
    public void successful_test_register_new_server() {
        String ip = "192.168.1.1";
        InetAddress mockInetAdd = mock(InetAddress.class);

        when(mockInetAdd.getHostAddress()).thenReturn(ip);
        mockStatic(InetAddress.class).when(() -> InetAddress.getByName(ip)).thenReturn(mockInetAdd);

        firstQuiz.registerServer(ip);

        assertEquals(1, firstQuiz.serverList.size());
        assertTrue(firstQuiz.serverList.contains(ip));
    }

    @Test
    public void threshold_exception() {
        for (int i = 1; i <= 10; i++)
            firstQuiz.serverList.add("192.168.1." + i);

        var exception = assertThrows(ServerException.class, () -> firstQuiz.registerServer("192.168.1.10"));
        assertEquals("out of threshold", exception.getMessage());
    }

    @Test
    public void unknown_host_exception() {
        var unknownIP = assertThrows(ServerException.class, () -> firstQuiz.registerServer("invalid.server"));
        assertEquals("unknown host", unknownIP.getMessage());
        firstQuiz.serverList.clear();
    }

    @Test
    public void ip_exist_already_exception() {
        String newIP = "192.168.1.1";
        firstQuiz.serverList.add(newIP);
        var existIP = assertThrows(ServerException.class, () -> firstQuiz.registerServer(newIP));
        assertEquals("IP address already exist", existIP.getMessage());
        firstQuiz.serverList.clear();
    }

    @Test
    public void invalid_ip_already() {
        var invalidLength = assertThrows(ServerException.class, () -> firstQuiz.registerServer("192.168.1.1" + "-"));
        assertEquals("unknown host", invalidLength.getMessage());
        firstQuiz.serverList.clear();
    }
}