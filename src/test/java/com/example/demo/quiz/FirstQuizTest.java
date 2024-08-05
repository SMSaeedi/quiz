package com.example.demo.quiz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FirstQuizTest {
    FirstQuiz firstQuiz;

    @Before
    public void setUp() {
        firstQuiz = new FirstQuiz();
        firstQuiz.serverList.add("192.168.1.1");
        firstQuiz.serverList.add("192.168.1.2");
        firstQuiz.serverList.add("192.168.1.3");
        firstQuiz.serverList.add("192.168.1.4");
        firstQuiz.serverList.add("192.168.1.5");
        firstQuiz.serverList.add("192.168.1.6");
        firstQuiz.serverList.add("192.168.1.7");
        firstQuiz.serverList.add("192.168.1.8");
        firstQuiz.serverList.add("192.168.1.9");
        firstQuiz.serverList.add("192.168.1.10");
    }

    @Test
    public void register_new_server_sunny_test() {
        assertTrue(firstQuiz.serverList.remove("192.168.1.10"));
        assertTrue(firstQuiz.validateServer("192.168.1.10"));
    }

    @Test
    public void register_new_server_gray_test() {
        assertFalse(firstQuiz.validateServer("192.168.1.10"));
        assertFalse(firstQuiz.validateServer("192.168.1.111"));
    }

    @Test
    public void threshold_check() {
        assertThrows(ServerException.class, () -> firstQuiz.registerServer());
    }

}