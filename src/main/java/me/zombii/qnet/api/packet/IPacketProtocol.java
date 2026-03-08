package me.zombii.qnet.api.packet;

import me.zombii.qnet.api.Side;
import me.zombii.qnet.impl.packet.DefaultPacketProtocol;

/**
 * A registry class that has a set {@link IPacketFormat} used for every packet sent using an instance of this class.
 * Every instance of this class can store instances {@link IPacketHandler} and classes implementing {@link IPacket} for later instancing, sending and recieving data.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface IPacketProtocol {

    /**
     * Makes a prebuilt instance of this class using default settings.
     * <ul>
     *     <li>Compression: <strong>Enabled</strong></li>
     *     <li>CustomPacket and PacketHandler Reregistration: <strong>Disabled</strong></li>
     *     <li>Compression Level: {@link java.util.zip.Deflater#DEFAULT_COMPRESSION}</li>
     * </ul>
    */
    static IPacketProtocol makeDefault() {
        return new DefaultPacketProtocol();
    }

    /**
     * Makes a prebuilt instance of this class using default settings and a premade {@link IPacketFormat}.
     * <ul>
     *     <li>Compression: <strong>Enabled</strong></li>
     *     <li>Compression Level: {@link java.util.zip.Deflater#DEFAULT_COMPRESSION}</li>
     * </ul>
     */
    static IPacketProtocol makeDefault(boolean allowReregistration) {
        return new DefaultPacketProtocol(allowReregistration);
    }


    /**
     * Makes a prebuilt instance of this class with a custom instance of {@link IPacketFormat} and default settings.<br/>
     * <ul>
     *     <li>CustomPacket and PacketHandler Reregistration: <strong>Disabled</strong></li>
     * </ul>
     */
    static IPacketProtocol makeDefault(IPacketFormat packetFormat) {
        return new DefaultPacketProtocol(packetFormat);
    }

    /**
     * Makes a prebuilt instance of this class with a custom instance of {@link IPacketFormat}.<br/>
     */
    static IPacketProtocol makeDefault(IPacketFormat packetFormat, boolean allowReregistration) {
        return new DefaultPacketProtocol(packetFormat, allowReregistration);
    }

    /**
     * Dynamically registers a packet to an available ID
     * @param packetClass The custom packet to register.
     * @return id registered to the packet.
     */
    int registerPacket(Class<? extends IPacket> packetClass);

    /**
     *
     * @param packetClass The custom packet to register.
     * @param id The id used to register the packet.
     * @return id registered to the packet.
     */
    int registerPacket(Class<? extends IPacket> packetClass, int id);

    /**
     * Registers a packet handler to a given network side and packet-id.
     *
     * @param side The network side to register the handler for
     * @param id The packet-id to register the handler for.
     * @param handler The packet handler
     */
    void registerHandler(Side side, int id, IPacketHandler<?> handler);

    /**
     * Registers a packet handler to a given network side and packet.
     *
     * @param side The network side to register the handler for.
     * @param packetClass The packet to register the handler for.
     * @param handler The packet handler
     */
    <T extends IPacket> void registerHandler(Side side, Class<T> packetClass, IPacketHandler<T> handler);

    /**
     * Gets a registered packet handler from a given side and packet-id.
     * @param id the packet-id the handler belongs to.
     * @param side the network side the handler was registered for.
     */
    IPacketHandler<?> getHandler(Side side, int id);

    /**
     * Gets a registered packet handler from a given side and packet-id.
     * @param packetClass packet the handler belongs to.
     * @param side the network side the handler was registered for.
     * @return The packet handler for the side and packet.
     */
    <T extends IPacket> IPacketHandler<?> getHandler(Side side, Class<T> packetClass);

    /**
     * Gets a registered packet handler from a given side and packet-id.
     * @param packet packet the handler belongs to.
     * @param side the network side the handler was registered for.
     * @return The packet handler for the side and packet.
     */
    <T extends IPacket> IPacketHandler<?> getHandler(Side side, T packet);

    /**
     * Gets the id the packet was registered on.
     * @param packet the registered packet.
     * @return the id paired with the packet.
     */
    int getPacketId(IPacket packet);

    /**
     * Gets the id the packet was registered on.
     * @param packetClass the registered packet.
     * @return the id paired with the packet.
     */
    int getPacketId(Class<? extends IPacket> packetClass);

    /**
     * Gets the packet-class the id is paired with.
     * @param id the packet-id.
     * @return the packet-class registered to id.
     */
    Class<? extends IPacket> getPacketClass(int id);

    /**
     * Determines if a given packet was registered to this protocol.
     * @param packetClass the packet to check.
     * @return the registration state of a packet.
     */
    boolean hasPacketBeenRegistered(Class<? extends IPacket> packetClass);

    /**
     * Determines if a given id already has a packet registered to it.
     * @param packetId the id to check.
     * @return the registration state of an id.
     */
    boolean hasPacketIdBeenTaken(int packetId);

    /**
     * Creates an instance of a registered packet for a given id.
     * @param id the packet-id of a registered packet.
     * @return the packet instance.
     */
    IPacket instancePacket(int id);

    /**
     * Retrieves the used packet format within the protocol.
     * @return the used packet-format.
     */
    IPacketFormat getPacketFormat();
}
