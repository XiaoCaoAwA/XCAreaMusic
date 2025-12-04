package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 淡出淡入播放音乐指令
 * 用法: /xcareamusic fadeoutinplay <instanceName> <totalDuration> <url>
 */
public class SubFadeOutInPlayCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "fadeoutinplay";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "淡出当前音乐并淡入播放新音乐";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic fadeoutinplay <instanceName> <totalDuration> <url>";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§c用法: " + getUsage());
            return;
        }

        try {
            String instanceName = args[0];
            long totalDuration = Long.parseLong(args[1]);
            String url = args[2];

            BGMNetwork.sendFadeOutInPlay(player, instanceName, totalDuration, url);
            player.sendMessage("§a正在淡出淡入播放音乐: §e" + instanceName + " §7(总时长: " + totalDuration + "ms)");
        } catch (NumberFormatException e) {
            player.sendMessage("§c无效的时长参数，请输入数字！");
        }
    }
}

