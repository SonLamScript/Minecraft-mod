package com.example.tntmod;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.tntmod.command.TntCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("tntmod")
public class TntMod {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public TntMod() {
        // Register the setup method for modloading
        // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        // FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Placeholder for event listeners
        // 예를 들어, 아이템 등록, 블록 등록 등을 위한 이벤트 리스너를 여기에 추가할 수 있습니다.
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        TntCommand.register(event.getDispatcher());
    }

    // Placeholder for other mod logic
    // 예를 들어, 커맨드 처리, 새로운 기능 구현 등을 위한 코드를 여기에 추가할 수 있습니다.
}
