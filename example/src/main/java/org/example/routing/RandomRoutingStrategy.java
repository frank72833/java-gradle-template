package org.example.routing;

import java.util.Map;
import java.util.random.RandomGenerator;
import org.example.model.Server;

public class RandomRoutingStrategy implements RoutingStrategy {
    final RandomGenerator randomGenerator;

    public RandomRoutingStrategy() {
        this.randomGenerator = RandomGenerator.getDefault();
    }

    public RandomRoutingStrategy(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Server route(Map<String, Server> servers) {
        final var randomIndex = randomGenerator.nextInt(servers.size());
        return servers.values().stream().toList().get(randomIndex);
    }
}
