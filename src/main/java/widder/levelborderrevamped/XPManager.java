package widder.levelborderrevamped;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class XPManager {

    public static void MergeXP(MinecraftServer server, boolean reset, ServerPlayer extra) {
        List<ServerPlayer> players = new ArrayList<>(server.getPlayerList().getPlayers());
        if (extra != null && !players.contains(extra)) players.add(extra);
        if (players.isEmpty()) return;

        ServerPlayer MaxXP = players.stream().max(Comparator.comparingInt(p -> p.totalExperience)).orElse(null);
        int MaxXPInt = 0;
        if (MaxXP.totalExperience > Config.INSTANCE.globalXp) {
            MaxXPInt = MaxXP.totalExperience;
            Config.INSTANCE.globalXp = MaxXPInt;
        } else {
            MaxXPInt = Config.INSTANCE.globalXp;
        }

        for (ServerPlayer player : players) {
            player.experienceLevel = 0;
            player.experienceProgress = 0;
            player.totalExperience = 0;
            if (reset) {
                player.giveExperiencePoints(0);
            }else {
                player.giveExperiencePoints(MaxXPInt);
            }
        }
    }
}