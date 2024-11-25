package org.example.routing;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.example.model.Server;

public class RoundRobinRoutingStrategy implements RoutingStrategy {
    private final AtomicInteger counter;

    public RoundRobinRoutingStrategy() {
        this.counter = new AtomicInteger(0);
    }

    @Override
    public Server route(final Map<String, Server> servers) {
        final var serverList = servers.values().stream().toList();
        final var roundRobin = counter.getAndIncrement() % serverList.size();
        return serverList.get(roundRobin);
    }
}
