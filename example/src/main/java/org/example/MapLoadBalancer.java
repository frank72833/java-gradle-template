package org.example;

import java.util.HashMap;
import java.util.Map;
import org.example.exception.CapacityLoadBalancerException;
import org.example.model.Server;
import org.example.routing.RoutingStrategy;

public class MapLoadBalancer implements LoadBalancer {
    private final Map<String, Server> servers;
    private final int maxInstances;
    private final RoutingStrategy routingStrategy;

    public MapLoadBalancer(final RoutingStrategy routingStrategy) {
        this(new HashMap<>(), 10, routingStrategy);
    }

    public MapLoadBalancer(
            final Map<String, Server> servers,
            final int maxInstances,
            final RoutingStrategy routingStrategy) {
        this.servers = servers;
        this.maxInstances = maxInstances;
        this.routingStrategy = routingStrategy;
    }

    @Override
    public synchronized Server register(String ipAddress, double load) {
        if (servers.size() == maxInstances) {
            throw new CapacityLoadBalancerException(maxInstances);
        }

        final var server = new Server(ipAddress, load);
        servers.put(ipAddress, new Server(ipAddress, load));

        return server;
    }

    @Override
    public synchronized Server unregister(String ipAddress) {
        return servers.remove(ipAddress);
    }

    @Override
    public synchronized Server route() {
        return routingStrategy.route(servers);
    }
}
