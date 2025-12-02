package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 播放音乐指令
 * 用法: /xcareamusic play <url> <instanceName>
 */
public class SubPlayCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "play";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "播放音乐";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic play <url> <instanceName>";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§c用法: " + getUsage());
            return;
        }

        String url = args[0];
        String instanceName = args[1];

        BGMNetwork.sendPlayBGM(player, url, instanceName);
        player.sendMessage("§a正在播放音乐: §e" + instanceName);
    }
}

