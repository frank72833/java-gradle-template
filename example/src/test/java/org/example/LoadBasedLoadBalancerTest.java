package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoadBasedLoadBalancerTest {

    @Test
    public void givenServers_whenCallingRoute_returnLessLoadedService() {
        final var sut = new LoadBasedLoadBalancer();

        sut.register("1.1.1.1", 0.43);
        sut.register("1.1.1.2", 0.10);
        sut.register("1.1.1.3", 0.22);
        sut.register("1.1.1.4", 0.05);
        sut.register("1.1.1.1", 0.11);

        final var result = sut.route();

        assertThat(result.getIpAddress())
                .isEqualTo("1.1.1.4");
    }
}