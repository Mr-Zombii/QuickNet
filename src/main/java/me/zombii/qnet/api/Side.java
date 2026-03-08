package me.zombii.qnet.api;

/**
 * This denotes the side of the network you are on.
 * <ul>
 * <li><sub>{@link Side#CLIENT} - Client side, Connects to server.</sub></li>
 * <li><sub>{@link Side#SERVER} - Server side, Accepts clients.</sub></li>
 * </ul>
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public enum Side {
    CLIENT,
    SERVER
}
