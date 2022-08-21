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

    public static void showSkin(PlayerModelPart[] modelParts) {
        for(PlayerModelPart modelPart : modelParts) {
            if (modelPart != PlayerModelPart.CAPE) {
                client.options.togglePlayerModelPart(modelPart, true);
            }
        }
    }

    @Override
    public void onInitialize() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!packetLimiter.getPacketLimiter()) {
                try {
                    ItemStack stack = new ItemStack(Items.SHEARS);
                    List<AbstractClientPlayerEntity> players = client.world.getPlayers();

                    for(PlayerEntity playerIterator : players) {
                        if (playerIterator != client.player
                                && client.world.isClient()
                                && playerIterator.getMainHandStack().getItem() == stack.getItem()
                                && playerIterator.isSneaking()
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

        /*
        UseItemCallback.EVENT.register((player, world, hand) ->
        {
            ItemStack stack = new ItemStack(Items.SHEARS);
            List<PlayerEntity> players = (List<PlayerEntity>) world.getPlayers();

            for(PlayerEntity playerIterator : players) {
                if (playerIterator != player
                        && world.isClient()
                        && playerIterator.getMainHandStack().getItem() == stack.getItem()
                        && playerIterator.isSneaking()
                )
                {
                    hideSkin(modelParts);
                }
            }

            if(world.isClient() && player.getStackInHand(hand).getItem() == stack.getItem()) {
                toggleSkin(modelParts);
            }

            return TypedActionResult.pass(stack);
        });
        */

    }
}
