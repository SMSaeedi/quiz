package com.example.demo.autoquiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class AutoTest {
    Auto c = new Auto();

    @Test
    void test_get_key() {
        var response = c.getKey("mY-cUsTom-kEy", "My secret no one should know!");
        assertEquals(response, "mY-cUsTom-kEy");
    }

    @Test
    void test_get_secret_by_key() {
        c.map.put("my-custom-key", "My secret no one should know!");
        var response = c.getSecret("mY-cUsTom-kEy");
        assertEquals(response, "My secret no one should know!");
    }

    @Test
    void test_get_key_with_wrong_length_exception() {
        assertThrowsExactly(RuntimeException.class,
                () -> c.getKey("mY-cUsTom-kEyvvvvvvvvvvvvvvvvvvvvvvvvvvvvv", "My secret no one should know!"));
    }

}