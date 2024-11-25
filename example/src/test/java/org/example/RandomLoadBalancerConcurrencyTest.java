package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.random.RandomGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomLoadBalancerConcurrencyTest {

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
    public void givenConcurrentThreads_whenCallingRegister_returnExpectedSize() throws InterruptedException, ExecutionException {
        final var sut = new RandomLoadBalancer();

        final var totalNumberOfTasks = 10_000;
        final var executor = Executors.newFixedThreadPool(10);
        final var futures = new ArrayList<Future<?>>();
        for (int i = 0; i < totalNumberOfTasks; i++) {
            final var iterator = i;
            futures.add(executor.submit(() -> {
                sut.register(randomIpAddress(), iterator);
            }));
        }

        executor.shutdown();

        for (var future: futures) {
            future.get();
        }

        final var finalServers = sut.getServers();

        assertThat(finalServers).hasSize(10_000);
    }

    @Test
    public void givenConcurrentThreads_whenCallingRegisterAndUnregister_returnExpectedSize() throws InterruptedException, ExecutionException {
        final var sut = new RandomLoadBalancer();

        final var totalNumberOfTasks = 10_000;
        final var executor = Executors.newFixedThreadPool(10);
        final var futures = new ArrayList<Future<?>>();
        for (int i = 0; i < totalNumberOfTasks; i++) {
            final var iterator = i;
            futures.add(executor.submit(() -> {
                sut.register(ipAddress(iterator), iterator);
            }));
        }

        for (var future: futures) {
            future.get();
        }

        for (int i = 0; i < totalNumberOfTasks - 1; i++) {
            final var iterator = i;
            futures.add(executor.submit(() -> {
                sut.unregister(ipAddress(iterator));
            }));
        }

        executor.shutdown();

        for (var future: futures) {
            future.get();
        }

        final var finalServers = sut.getServers();

        assertThat(finalServers).hasSize(1);

        final var result = sut.route();
        assertThat(result.getIpAddress()).isEqualTo(ipAddress(9_999));
    }

    private String ipAddress(final int number) {
        return number + "." + number + "." + number + "." + number + ".";
    }

    private String randomIpAddress() {
        final var r = new Random();
        return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
    }
}