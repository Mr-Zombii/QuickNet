package me.zombii.qnet.api.packet;

import me.zombii.qnet.impl.packet.DefaultPacketFormat;
import me.zombii.qnet.io.Deserializer;
import me.zombii.qnet.io.Serializer;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * An API class that allows the custom definition of header and footer data surrounding a packet's payload given a protocol.
 *
 * @since 1.0.0
 * @author Mr-Zombii
 */
public interface IPacketFormat {

    /**
     * Makes a prebuilt instance of this class using default settings.
     * <ul>
     *     <li>Compression: <strong>Enabled</strong></li>
     *     <li>Compression Level: {@link java.util.zip.Deflater#DEFAULT_COMPRESSION}</li>
     * </ul>
     *
     * @return the default packet format.
     */
    static IPacketFormat makeDefault() {
        return new DefaultPacketFormat();
    }

    /**
     * Makes a prebuilt instance of this class using custom settings.
     * <ul>
     *     <li>Compression Level (if enabled): {@link java.util.zip.Deflater#DEFAULT_COMPRESSION}</li>
     * </ul>
     *
     * @param compressionEnabled sets if compression is to be used.
     *
     * @return the default packet format.
     */
    static IPacketFormat makeDefault(boolean compressionEnabled) {
        return new DefaultPacketFormat(compressionEnabled);
    }

    /**
     * Makes a prebuilt instance of this class using custom settings.
     * <ul>
     *     <li>Compression: <strong>Enabled</strong></li>
     *     <li>Compression Level: {@link java.util.zip.Deflater#DEFAULT_COMPRESSION}</li>
     * </ul>
     *
     * @param compressionLevel sets the compression level to compress bytes at.
     *
     * @return the default packet format.
     */
    static IPacketFormat makeDefault(int compressionLevel) {
        return new DefaultPacketFormat(true, compressionLevel);
    }

    void write(IPacketProtocol protocol, Serializer serializer, IPacket packet) throws IOException;
    IPacket read(IPacketProtocol protocol, Deserializer deserializer) throws IOException, DataFormatException;

}
