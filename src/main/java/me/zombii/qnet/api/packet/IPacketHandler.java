package me.zombii.qnet.api.packet;

import me.zombii.qnet.api.connections.IConnection;
import me.zombii.qnet.api.Side;

import java.io.IOException;

/**
 * An API class that allows for custom definitions per network-side of a given packet.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface IPacketHandler<T extends IPacket> {

    void handle(Side side, IConnection connection, IPacketProtocol protocol, T packet) throws IOException;

}
