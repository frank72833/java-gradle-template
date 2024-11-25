package org.example.exception;

public class CapacityLoadBalancerException extends RuntimeException {
    public CapacityLoadBalancerException(final int capacity) {
        super("The maximum capacity: " + capacity + "for this load balancer has been reached");
    }
}
