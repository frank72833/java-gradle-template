package org.example;

import java.util.ArrayList;
import java.util.random.RandomGenerator;
import org.example.model.Server;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLoadBalancerTest {

    private static class TestRandomGenerator implements RandomGenerator {

        @Override
        public int nextInt(int origin, int bound) {
            return 1;
        }


        @Override
        public long nextLong() {
            return 0;
        }
    }

    @Test
    public void givenMultipleServers_whenCallingRouter_returnRandomRoute() {
        final var servers = new ArrayList<Server>();

        final var sut = new RandomLoadBalancer(servers, new TestRandomGenerator());

        sut.register("1.1.1.1", 0.01);
        sut.register("1.1.1.2", 0.10);
        sut.register("1.1.1.3", 0.22);
        sut.register("1.1.1.4", 0.05);
        sut.register("1.1.1.1", 0.11);

        final var result = sut.route();

        assertThat(result.getIpAddress())
                .isEqualTo("1.1.1.2");
    }
}