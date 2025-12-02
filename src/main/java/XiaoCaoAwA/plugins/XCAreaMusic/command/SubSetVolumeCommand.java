package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 设置实例音量指令
 * 用法: /xcareamusic setvolume <volume> <instanceName>
 */
public class SubSetVolumeCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "setvolume";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "设置指定音乐实例的音量";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic setvolume <volume> <instanceName>";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage("§c用法: " + getUsage());
            return;
        }

        try {
            float volume = Float.parseFloat(args[0]);
            String instanceName = args[1];

            if (volume < 0.0f || volume > 1.0f) {
                player.sendMessage("§c音量值必须在 0.0 到 1.0 之间！");
                return;
            }

            BGMNetwork.sendSetVolume(player, volume, instanceName);
            player.sendMessage("§a已设置音乐实例 §e" + instanceName + " §a的音量为: §e" + volume);
        } catch (NumberFormatException e) {
            player.sendMessage("§c无效的音量参数，请输入 0.0 到 1.0 之间的数字！");
        }
    }
}

