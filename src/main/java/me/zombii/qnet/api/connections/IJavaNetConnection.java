package me.zombii.qnet.api.connections;

import java.net.Socket;

/**
 * An API class used internally for the Java.Net backend implementation.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface IJavaNetConnection extends IConnection {

    Socket getSocket();
    void setSocket(Socket socket);

}
