package net.matthieuclam.skinswitch;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.List;

import net.matthieuclam.skinswitch.util.PacketLimiter;

public class SkinSwitch implements ModInitializer {

    public static final PlayerModelPart[] modelParts = PlayerModelPart.values();
    public static PacketLimiter packetLimiter = new PacketLimiter();
    public static MinecraftClient client = MinecraftClient.getInstance();

    public static void hideSkin(PlayerModelPart[] modelParts) {
        for(PlayerModelPart modelPart : modelParts) {
            if (modelPart != PlayerModelPart.CAPE) {
                client.options.togglePlayerModelPart(modelPart, false);
            }
        }
    }

    @Override
    public void onInitialize() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ItemStack stackShear = new ItemStack(Items.SHEARS);
            ItemStack stackWool = new ItemStack(Items.PINK_WOOL);
            if (!packetLimiter.getPacketLimiter()) {
                try {
                    List<AbstractClientPlayerEntity> players = client.world.getPlayers();
                    for(PlayerEntity playerIterator : players) {
                        if (playerIterator != client.player
                                && client.world.isClient()
                                && playerIterator.getMainHandStack().getItem() == stackShear.getItem()
                                && playerIterator.isSneaking()
                                && playerIterator.squaredDistanceTo(client.player) <= 9
                        ) {
                            hideSkin(modelParts);
                            packetLimiter.setPacketLimiter(true);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
