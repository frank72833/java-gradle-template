package org.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExampleServiceTest {
    private final ExampleService sut = new ExampleService();

    @Test
    public void givenExampleService_whenCallingGreeting_thenReturnHelloWorld() {
        final var result = sut.getGreeting();

        assertEquals("Hello world!", result);
    }
}