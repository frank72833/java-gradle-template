package org.example.routing;

import java.util.Map;
import org.example.model.Server;

public interface RoutingStrategy {
    Server route(final Map<String, Server> servers);
}
