package org.example.routing;

import java.util.ArrayList;
import java.util.Map;
import java.util.random.RandomGenerator;
import org.example.model.Server;

public class WeightedRoutingStrategy implements RoutingStrategy {
    private final Map<String, Integer> weightedMap;
    private final RandomGenerator randomGenerator;

    public WeightedRoutingStrategy(final Map<String, Integer> weightedMap) {
        this(weightedMap, RandomGenerator.getDefault());
    }

    public WeightedRoutingStrategy(final Map<String, Integer> weightedMap, RandomGenerator randomGenerator) {
        this.weightedMap = weightedMap;
        this.randomGenerator = randomGenerator;
    }

    @Override
    public Server route(final Map<String, Server> servers) {
        final var serverList = new ArrayList<Server>();
        servers.forEach((key, value) -> {
            final var weight = weightedMap.getOrDefault(key, 1);
            for (int i = 0; i < weight; i++) {
                serverList.add(value);
            }
        });

        final var randomIndex = randomGenerator.nextInt(serverList.size());
        return serverList.get(randomIndex);
    }
}
