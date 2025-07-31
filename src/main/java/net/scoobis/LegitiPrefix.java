package net.scoobis;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class LegitiPrefix implements ModInitializer {
	public static final String MOD_ID = "LegitiPrefix";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static MinecraftClient CLIENT = MinecraftClient.getInstance();
	public static ConfigHolder<LegitiPrefixConfig> CONFIG;

	@Override
	public void onInitialize() {
		LOGGER.info("LegitiPrefix is LegitiHere!");
		AutoConfig.register(LegitiPrefixConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(LegitiPrefixConfig.class);

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommandManager.literal("legiticonfig").executes(context -> {
				GameMenuScreen gameMenuScreen = new GameMenuScreen(true);
				CLIENT.send(() -> CLIENT.setScreen(AutoConfig.getConfigScreen(LegitiPrefixConfig.class, gameMenuScreen).get()));
				return 1;
			}));
		});
	}
}