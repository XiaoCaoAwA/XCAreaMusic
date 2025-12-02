package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 停止音乐指令
 * 用法: /xcareamusic stop <instanceName>
 */
public class SubStopCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "stop";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "停止指定的音乐实例";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic stop <instanceName>";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§c用法: " + getUsage());
            return;
        }

        String instanceName = args[0];
        BGMNetwork.sendStopBGM(player, instanceName);
        player.sendMessage("§a已停止音乐: §e" + instanceName);
    }
}

