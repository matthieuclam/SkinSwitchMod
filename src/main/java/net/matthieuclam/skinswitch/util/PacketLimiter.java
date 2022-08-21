package net.matthieuclam.skinswitch.util;

public class PacketLimiter {

    private boolean packetLimiter = true;

    public boolean getPacketLimiter() {
        return this.packetLimiter;
    }

    public void setPacketLimiter(boolean limiter) {
        this.packetLimiter = limiter;
    }

}
