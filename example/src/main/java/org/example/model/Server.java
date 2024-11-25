package org.example.model;

import java.util.Objects;

public class Server {
    private final String ipAddress;
    private double load;

    public Server(final String ipAddress, final double load) {
        this.ipAddress = ipAddress;
        this.load = load;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public double getLoad() {
        return load;
    }

    public void updateLoad(final double load) {
        this.load = load;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return Double.compare(load, server.load) == 0 && Objects.equals(ipAddress, server.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, load);
    }
}
