package me.zombii.qnet.api;

/**
 * This denotes the side of the network you are on.
 * <sub><ul>
 * <li>{@link Side#CLIENT} - Client side, Connects to server.</li>
 * <li>{@link Side#SERVER} - Server side, Accepts clients.</li>
 * </ul></sub>
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public enum Side {
    CLIENT,
    SERVER
}
