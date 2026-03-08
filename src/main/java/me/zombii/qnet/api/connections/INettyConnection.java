package me.zombii.qnet.api.connections;

import io.netty.channel.ChannelHandlerContext;

/**
 * An API class used internally for the Netty backend implementation.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface INettyConnection extends IConnection {

    ChannelHandlerContext getHandlerContext();
    void setHandlerContext(ChannelHandlerContext socket);


}
