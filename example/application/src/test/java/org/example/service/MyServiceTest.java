package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyServiceTest {

    private final MyService sut = new MyService();

    @Test
    public void givenMyService__whenCallingGreeting__thenReturnHelloWorld() {
        final var result = sut.getGreeting();

        assertEquals("Hello world!", result);
    }
}