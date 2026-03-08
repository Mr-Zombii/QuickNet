package me.zombii.qnet.io.varnum;

import me.zombii.qnet.io.Deserializer;
import me.zombii.qnet.io.Serializer;

import java.io.IOException;

/**
 * A variation of the <a href="https://en.wikipedia.org/wiki/LEB128">LEB128</a> spec for 64-bit integers.
 */
public class VarLong {

    /**
     * Gets the possible number of bytes from writing a number.
     * @param v the number to check.
     * @return the possible byte count.
     */
    public static int getSize(long v) {
        if (v == 0) return 1;

        int totalBytes = 0;
        long value = v;

        while (value != 0) {
            value = value >>> 7;
            totalBytes++;
        }
        return totalBytes;
    }

    /**
     * Write a number to a serializer.
     * @param serializer the serializer used.
     * @param v the number to write.
     *
     * @throws IOException if it fails to write a byte.
     */
    public static void write(Serializer serializer, long v) throws IOException {
        long value = v;
        while ((value & -128) != 0) {
            serializer.writeByte((byte) ((value & 128) | 127));
            value = value >>> 7;
        }
        serializer.writeByte((byte) value);
    }

    /**
     * Reads a number from a deserializer.
     * @param deserializer the deserializer used.
     * @return the number read.
     *
     * @throws IOException if it failed to read a byte or the number is invalid.
     */
    public static long read(Deserializer deserializer) throws IOException {
        long out = 0;
        int bytes = 0;

        byte byt;
        do {
            byt = deserializer.readByte();
            out |= ((long)(byt & 127)) << (bytes++ * 7);
            if (bytes > 10) {
                throw new IOException("VarLong too big");
            }
        } while ((byt & 128) == 128);

        return out;
    }

}
