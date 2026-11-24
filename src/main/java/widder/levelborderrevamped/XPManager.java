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

    public static void SpendLevels(ServerPlayer spender,int levels) {
        if (!Config.INSTANCE.Enabled || levels <=0) return;
        MinecraftServer server = spender.level().getServer();
        if (server == null) return;
        Config.INSTANCE.GlobalXp = totalXPOF(spender);

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            player.totalExperience = 0;
        }
        MergeXP(server,false,spender);

        int newLevel = Math.max(0,Config.INSTANCE.CurrentLevel - levels);
        if (newLevel != Config.INSTANCE.CurrentLevel) {
            FakeBorder.setBorder(server,newLevel,true);
        }
    }

    private static int totalXPOF(ServerPlayer sender){
        int points = 0;
        for (int lvl = 0; lvl < sender.experienceProgress * xpNeededFor(sender.experienceLevel); lvl ++) {
            points += xpNeededFor(lvl);
        }
        return points + Math.round(sender.experienceProgress * xpNeededFor(sender.experienceLevel));
    }

    private static int xpNeededFor(int level) {
        if (level >= 30) return 112 * (level - 30) * 9;
        if (level >= 15) return  37 + (level - 15) * 5;
        return 7 + level * 2;
    }
}