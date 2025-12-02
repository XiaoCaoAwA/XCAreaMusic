package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 淡入播放音乐指令
 * 用法: /xcareamusic fadeinplay <duration> <url> <instanceName>
 */
public class SubFadeInPlayCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "fadeinplay";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "淡入播放音乐";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic fadeinplay <duration> <url> <instanceName>";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length < 3) {
            player.sendMessage("§c用法: " + getUsage());
            return;
        }

        try {
            long duration = Long.parseLong(args[0]);
            String url = args[1];
            String instanceName = args[2];

            BGMNetwork.sendFadeInPlay(player, duration, url, instanceName);
            player.sendMessage("§a正在淡入播放音乐: §e" + instanceName + " §7(淡入时长: " + duration + "ms)");
        } catch (NumberFormatException e) {
            player.sendMessage("§c无效的时长参数，请输入数字！");
        }
    }
}

