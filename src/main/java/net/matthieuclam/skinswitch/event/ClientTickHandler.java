package net.matthieuclam.skinswitch.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.List;

public class ClientTickHandler {

    private static boolean packetLimiter = false;
    public static MinecraftClient client = MinecraftClient.getInstance();
    public static final PlayerModelPart[] modelParts = PlayerModelPart.values();

    public static boolean getPacketLimiter() {
        return packetLimiter;
    }

    public static void setPacketLimiter(boolean limiter) {
        packetLimiter = limiter;
    }

    public static void hideSkin(PlayerModelPart[] modelParts) {
        for(PlayerModelPart modelPart : modelParts) {
            if (modelPart != PlayerModelPart.CAPE) {
                client.options.togglePlayerModelPart(modelPart, false);
            }
        }
    }

    public static void playerCondition(PlayerEntity playerIterator) {
        if (playerIterator != client.player
                && client.world.isClient()
                && playerIterator.getMainHandStack().getItem() == new ItemStack(Items.SHEARS).getItem()
                && playerIterator.isSneaking()
                && playerIterator.squaredDistanceTo(client.player) <= 9
        ) {
            hideSkin(modelParts);
            setPacketLimiter(true);
        }
    }

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null && !getPacketLimiter()) {
                try {
                    List<AbstractClientPlayerEntity> players = client.world.getPlayers();
                    for(PlayerEntity playerIterator : players) {
                        playerCondition(playerIterator);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
