package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 淡出停止音乐指令
 * 用法: /xcareamusic fadeoutstop <instanceName> [duration]
 */
public class SubFadeOutStopCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "fadeoutstop";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "淡出停止音乐";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic fadeoutstop <instanceName> [duration]";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§c用法: " + getUsage());
            return;
        }

        String instanceName = args[0];

        // 如果提供了时长参数
        if (args.length >= 2) {
            try {
                long duration = Long.parseLong(args[1]);
                BGMNetwork.sendFadeOutStop(player, duration, instanceName);
                player.sendMessage("§a正在淡出停止音乐: §e" + instanceName + " §7(淡出时长: " + duration + "ms)");
            } catch (NumberFormatException e) {
                player.sendMessage("§c无效的时长参数，请输入数字！");
            }
        } else {
            // 使用默认淡出时长
            BGMNetwork.sendFadeOutStop(player, instanceName);
            player.sendMessage("§a正在淡出停止音乐: §e" + instanceName + " §7(默认淡出时长)");
        }
    }
}

