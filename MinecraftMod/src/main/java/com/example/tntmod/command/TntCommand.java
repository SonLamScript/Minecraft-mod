package com.example.tntmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt; // Corrected import
import net.minecraft.network.chat.Component;

import java.util.Random;

public class TntCommand {

    private static final Random RANDOM = new Random();

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("tnt")
                        .requires(source -> source.hasPermission(2)) // Requires op-level permission
                        .executes(TntCommand::execute)
        );
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = context.getSource().getPlayerOrException();
        ServerLevel level = player.serverLevel();
        double playerX = player.getX();
        double playerY = player.getY();
        double playerZ = player.getZ();

        for (int i = 0; i < 10; i++) {
            double xOffset = playerX + (RANDOM.nextDouble() * 10.0) - 5.0; // Random offset between -5 and +5
            double zOffset = playerZ + (RANDOM.nextDouble() * 10.0) - 5.0; // Random offset between -5 and +5
            double yPos = playerY; // Or adjust slightly if needed, e.g., playerY + 1.0

            PrimedTnt tnt = EntityType.TNT.create(level); // Corrected class name
            if (tnt != null) {
                tnt.setPos(xOffset, yPos, zOffset);
                tnt.setFuse(80); // 4 seconds (20 ticks per second)
                level.addFreshEntity(tnt);
            }
        }

        context.getSource().sendSuccess(() -> Component.literal("Summoned 10 TNT around you!"), true);
        return Command.SINGLE_SUCCESS;
    }
}
