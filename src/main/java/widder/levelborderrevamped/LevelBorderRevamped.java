package widder.levelborderrevamped;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import widder.levelborderrevamped.commands.CommandsRegister;

public class LevelBorderRevamped implements ModInitializer {
	public static final String MOD_ID = "level--border-revamped";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final String VERSION = "0.0.2";

	private static MinecraftServer SERVER;
	public static MinecraftServer getServer() { return SERVER; }

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTING.register(server -> Config.load(server));
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
				SERVER = server;
				FakeBorder.initialize(server);
		});
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			if (!Config.INSTANCE.Enabled) return;
			FakeBorder.tick(server);
		});
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> Config.save());
		ServerLifecycleEvents.AFTER_SAVE.register((server, flush, force) -> Config.save());

		CommandsRegister.Register();

		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			if (Config.INSTANCE.Enabled) {
				XPManager.MergeXP(server, false, handler.player);
			}
		});

		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			if (Config.INSTANCE.Enabled) {
				MinecraftServer server = newPlayer.level().getServer();
				if (!alive) XPManager.MergeXP(server, false, newPlayer);
			}
		});

		LOGGER.info("Level = Border Revamped loaded successfully");
	}
}

/*
			ToDo´s

	- resetXPByDeath and resetBorderByDeath work Properly

	- resetBorderByDeath

	- WorldBorder

	- Commands
 */