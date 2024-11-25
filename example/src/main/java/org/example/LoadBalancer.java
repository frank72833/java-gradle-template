package org.example;

import org.example.model.Server;

public interface LoadBalancer {
    Server register(final String ipAddress, final double load);
    Server unregister(final String ipAddress);
    Server route();
}
