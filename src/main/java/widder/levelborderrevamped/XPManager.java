package widder.levelborderrevamped;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class XPManager {

    public static void MergeXP(MinecraftServer server, boolean reset, ServerPlayer extra) {
        List<ServerPlayer> players = new ArrayList<>(server.getPlayerList().getPlayers());
        if (extra != null && !players.contains(extra)) players.add(extra);
        if (players.isEmpty()) return;

        ServerPlayer MaxXP = players.stream().max(Comparator.comparingInt(p -> p.totalExperience)).orElse(null);
        int MaxXPInt = 0;
        if (MaxXP.totalExperience > Config.INSTANCE.GlobalXp) {
            MaxXPInt = MaxXP.totalExperience;
            Config.INSTANCE.GlobalXp = MaxXPInt;
        } else {
            MaxXPInt = Config.INSTANCE.GlobalXp;
        }

        for (ServerPlayer player : players) {
            player.experienceLevel = 0;
            player.experienceProgress = 0;
            player.totalExperience = 0;
            if (reset) {
                player.giveExperiencePoints(0);
                Config.INSTANCE.GlobalXp = 0;
                if (Config.INSTANCE.ResetBorderByDeath) {
                    //Muss Hier was hinn?
                }
            }else {
                player.giveExperiencePoints(MaxXPInt);
            }
        }

        if (!reset && Config.INSTANCE.AutoExpand) {
            int level = players.get(0).experienceLevel;
            if (level > Config.INSTANCE.CurrentLevel) {
                FakeBorder.setBorder(server,level,true);
            }
        }
    }

    public static int getLevel(MinecraftServer server) {
        List<ServerPlayer> players = server.getPlayerList().getPlayers();
        if (players.isEmpty()) return Config.INSTANCE.CurrentLevel;
        return players.get(0).experienceLevel;
    }
}