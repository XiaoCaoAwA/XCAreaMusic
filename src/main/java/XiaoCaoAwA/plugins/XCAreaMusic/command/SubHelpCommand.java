package XiaoCaoAwA.plugins.XCAreaMusic.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * 帮助指令
 * 用法: /xcareamusic help
 */
public class SubHelpCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "help";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "显示帮助信息";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic help";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        sender.sendMessage("[§aXc音乐§f]/xcareamusic play [玩家ID] <url> <instanceName> §7- 播放音乐");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic fadeinplay [玩家ID] <duration> <url> <instanceName> §7- 淡入播放");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic fadeoutinplay [玩家ID] <instanceName> <totalDuration> <url> §7- 淡出淡入播放");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic stop [玩家ID] <instanceName> §7- 停止指定音乐");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic stopall [玩家ID] §7- 停止所有音乐");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic fadeoutstop [玩家ID] <instanceName> [duration] §7- 淡出停止");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic setvolume [玩家ID] <volume> <instanceName> §7- 设置实例音量");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic setmastervolume [玩家ID] <volume> §7- 设置主音量");
    }
}

