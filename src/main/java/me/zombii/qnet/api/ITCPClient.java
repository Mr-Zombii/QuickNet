package me.zombii.qnet.api;

import me.zombii.qnet.api.connections.IConnection;
import me.zombii.qnet.api.packet.IPacket;
import me.zombii.qnet.api.packet.IPacketProtocol;
import me.zombii.qnet.impl.javanet.tcp.JavaNetTCPClient;
import me.zombii.qnet.impl.netty.tcp.NettyTCPClient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.function.Consumer;

/**
 * The core API class for the TCPClient that handles client-server communication.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface ITCPClient extends IConnection {

    /**
     * Creates a default TCPClient using the Netty Backend.
     * @return Netty TCP Client.
     */
    static ITCPClient newNettyClient() {
        return new NettyTCPClient();
    }

    /**
     * Creates a default TCPClient using the Netty Backend with on connection accepted and closed hooks.
     * @param onConnectionAccepted sets a hook for when the connection succeeds.
     * @param onConnectionClosed sets a hook for when the connection is closed.
     * @return Netty TCP Client.
     */
    static ITCPClient newNettyClient(Consumer<ITCPClient> onConnectionAccepted, Consumer<ITCPClient> onConnectionClosed) {
        return new NettyTCPClient(onConnectionAccepted, onConnectionClosed);
    }

    /**
     * Creates a default TCPClient using the Java.Net Backend with on connection accepted and closed hooks.
     * @return Java.Net TCP Client.
     */
    static ITCPClient newJavaNetClient() {
        return new JavaNetTCPClient();
    }

    /**
     * Creates a default TCPClient using the Java.Net Backend with on connection accepted and closed hooks.
     * @param onConnectionAccepted sets a hook for when the connection succeeds.
     * @param onConnectionClosed sets a hook for when the connection is closed.
     * @return Java.Net TCP Client.
     */
    static ITCPClient newJavaNetClient(Consumer<ITCPClient> onConnectionAccepted, Consumer<ITCPClient> onConnectionClosed) {
        return new JavaNetTCPClient(onConnectionAccepted, onConnectionClosed);
    }

    /**
     * Attempts to connect to a server with a default protocol using an address.
     * @param protocol the protocol to start with.
     * @param address the server address in the form of "host:port".
     *
     * @throws IOException if the connection fails or the client errors horribly.
     */
    void connect(IPacketProtocol protocol, String address) throws IOException;

    /**
     * Attempts to connect to a server with a default protocol using an address.
     * @param protocol the protocol to start with.
     * @param address the server address in the form of "host:port".
     * @param timeout the time in ms until the client will stop trying to connect.
     *
     * @throws IOException if the connection fails or the client errors horribly.
     */
    void connect(IPacketProtocol protocol, String address, int timeout) throws IOException;

    /**
     * Attempts to connect to a server with a default protocol using an address.
     * @param protocol the protocol to start with.
     * @param address the server address in the form of "host:port".
     *
     * @throws IOException if the connection fails or the client errors horribly.
     */
    void connect(IPacketProtocol protocol, InetSocketAddress address) throws IOException;

    /**
     * Attempts to connect to a server with a default protocol using an address.
     * @param protocol the protocol to start with.
     * @param address the server address in the form of "host:port".
     * @param timeout the time in ms until the client will stop trying to connect.
     *
     * @throws IOException if the connection fails or the client errors horribly.
     */
    void connect(IPacketProtocol protocol, InetSocketAddress address, int timeout) throws IOException;

    /**
     * Gets the connection status if you are connected to a server or not.
     * @return the connection status.
     */
    boolean isConnected();

}
