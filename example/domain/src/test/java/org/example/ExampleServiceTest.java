package org.example;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleServiceTest {
    private final ExampleService sut = new ExampleService();

    @Test
    public void givenExampleService_whenCallingGreeting_thenReturnHelloWorld() {
        final var result = sut.getGreeting();

        assertThat(result).isEqualTo("Hello world!");
    }
}