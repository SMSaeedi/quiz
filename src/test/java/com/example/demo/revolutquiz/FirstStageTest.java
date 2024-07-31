package com.example.demo.revolutquiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FirstStageTest {
    private FirstStage firstStage;

    @BeforeEach
    void setUp() {
        firstStage = new FirstStage();
        firstStage.serverList.add("192.168.1.1");
        firstStage.serverList.add("192.168.1.2");
        firstStage.serverList.add("192.168.1.5");
        firstStage.serverList.add("192.168.1.6");
        firstStage.serverList.add("192.168.1.3");
    }

    @Test
    public void find_next_server() {
        assertEquals(firstStage.findNextServer(), "192.168.1.1");
        assertEquals(firstStage.findNextServer(), "192.168.1.2");
        assertEquals(firstStage.findNextServer(), "192.168.1.5");
        assertEquals(firstStage.findNextServer(), "192.168.1.6");
        assertEquals(firstStage.findNextServer(), "192.168.1.3");
    }

    @Test
    public void add_new_server() {
        assertEquals(firstStage.addServer("192.168.1.4"), "Server added 192.168.1.4");
        assertEquals(6, firstStage.serverList.size());
        assertEquals(firstStage.addServer("192.168.1.3"), "Server already exists");
    }

    @Test
    public void remove_server() {
        assertEquals(firstStage.removeServer("192.168.1.3"), "Server removed 192.168.1.3");
        assertEquals(4, firstStage.serverList.size());
        assertEquals(firstStage.removeServer("192.168.1.14"), "Server does not exist already");
    }

    @Test
    public void not_equals_server_ip() {
        assertNotEquals(firstStage.findNextServer(), "192.168.11.1");
    }

    @Test
    public void server_list_empty() {
        firstStage.serverList.clear();
        assertEquals(firstStage.findNextServer(), "Server list is empty");
    }
}