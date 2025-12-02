package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 停止所有音乐指令
 * 用法: /xcareamusic stopall
 */
public class SubStopAllCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "stopall";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "停止所有音乐实例";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic stopall";
    }

    @Override
    public void onCommand(Player player, String[] args) {
        BGMNetwork.sendStopAllBGM(player);
        player.sendMessage("§a已停止所有音乐");
    }
}

