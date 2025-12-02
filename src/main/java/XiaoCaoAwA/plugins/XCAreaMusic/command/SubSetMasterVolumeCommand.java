package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 设置主音量指令
 * 用法: /xcareamusic setmastervolume <volume>
 */
public class SubSetMasterVolumeCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "setmastervolume";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "设置主音量";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic setmastervolume <volume>";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if (args.length < 1) {
            player.sendMessage("§c用法: " + getUsage());
            return;
        }

        try {
            float volume = Float.parseFloat(args[0]);

            if (volume < 0.0f || volume > 1.0f) {
                player.sendMessage("§c音量值必须在 0.0 到 1.0 之间！");
                return;
            }

            BGMNetwork.sendSetMasterVolume(player, volume);
            player.sendMessage("§a已设置主音量为: §e" + volume);
        } catch (NumberFormatException e) {
            player.sendMessage("§c无效的音量参数，请输入 0.0 到 1.0 之间的数字！");
        }
    }
}

