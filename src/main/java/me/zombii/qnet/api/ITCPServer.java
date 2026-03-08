package me.zombii.qnet.api;

import it.unimi.dsi.fastutil.objects.ObjectList;
import me.zombii.qnet.api.connections.IConnection;
import me.zombii.qnet.api.packet.IPacket;
import me.zombii.qnet.api.packet.IPacketProtocol;
import me.zombii.qnet.impl.javanet.tcp.JavaNetTCPServer;
import me.zombii.qnet.impl.netty.tcp.NettyTCPServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.function.Consumer;

/**
 * The core API class for the TCPServer that handles communication to multiple clients.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface ITCPServer {

    /**
     * Creates a default TCPServer using the Netty Backend.
     * @return Netty TCP Server.
     */
    static ITCPServer newNettyServer() {
        return new NettyTCPServer();
    }

    /**
     * Creates a default TCPServer using the Netty Backend with on connection accepted and closed hooks.
     * @return Netty TCP Server.
     */
    static ITCPServer newNettyServer(Consumer<IConnection> onConnectionAccepted, Consumer<IConnection> onConnectionClosed) {
        return new NettyTCPServer(onConnectionAccepted, onConnectionClosed);
    }

    /**
     * Creates a default TCPServer using the Java.Net Backend.
     * @return Java.Net TCP Server.
     */
    static ITCPServer newJavaNetServer() throws IOException {
        return new JavaNetTCPServer();
    }

    /**
     * Creates a default TCPServer using the Java.Net Backend with on connection accepted and closed hooks.
     * @return Java.Net TCP Server.
     */
    static ITCPServer newJavaNetServer(Consumer<IConnection> onConnectionAccepted, Consumer<IConnection> onConnectionClosed) throws IOException {
        return new JavaNetTCPServer(onConnectionAccepted, onConnectionClosed);
    }

    /**
     * Attempts to bind a server to the given address with a default protocol used for each client.
     * @param protocol the protocol to start with.
     * @param address the server address in the form of "host:port".
     */
    void bind(IPacketProtocol protocol, String address);

    /**
     * Attempts to bind a server to the given address with a default protocol used for each client.
     * @param protocol the protocol to start with.
     * @param address the server address in the form of "host:port".
     */
    void bind(IPacketProtocol protocol, InetSocketAddress address);

    /**
     * Stops the server.
     */
    void stop();

    /**
     * Disconnects a specified connection from the server.
     * @param connection the connection to disconnect.
     */
    void disconnect(IConnection connection);

    /**
     * Sends a packet using a specified protocol to all connected clients.
     * @param protocol the protocol to send the packet with.
     * @param packet the packet to broadcast.
     */
    void broadcastPacket(IPacketProtocol protocol, IPacket packet) throws IOException;

    /**
     * Sends a packet using a specified protocol to all connected clients except one.
     * @param connection the connection to exclude.
     * @param protocol the protocol to send the packet with.
     * @param packet the packet to broadcast.
     */
    void broadcastPacketToAllExcept(IConnection connection, IPacketProtocol protocol, IPacket packet) throws IOException;

    /**
     * Gets all the connected clients.
     * @return all connected clients.
     */
    ObjectList<IConnection> getAllConnections();

    /**
     * Gets the connection accepted callback.
     * @return the connection accepted callback.
     */
    Consumer<IConnection> getOnConnectionAccepted();

    /**
     * Gets the connection closed callback.
     * @return the connection closed callback.
     */
    Consumer<IConnection> getOnConnectionClosed();

    /**
     * Checks if the server is running.
     * @return the state of the server.
     */
    boolean isRunning();
}
