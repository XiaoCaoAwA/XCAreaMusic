package XiaoCaoAwA.plugins.XCAreaMusic.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

/**
 * 命令管理器，用于注册和管理所有子命令
 */
public class MainCommand extends AbstractMainCommand {

    public MainCommand() {
        // 注册所有子命令
        registerSubCommand(new SubPlayCommand());
        registerSubCommand(new SubFadeInPlayCommand());
        registerSubCommand(new SubFadeOutInPlayCommand());
        registerSubCommand(new SubStopCommand());
        registerSubCommand(new SubStopAllCommand());
        registerSubCommand(new SubFadeOutStopCommand());
        registerSubCommand(new SubSetVolumeCommand());
        registerSubCommand(new SubSetMasterVolumeCommand());
    }

    @Override
    public void onCommand(CommandSender sender) {
        sender.sendMessage("§aXcAreaMusic音乐插件§7[§f1.1.0§7]");
        sender.sendMessage("§7作者: §fXiaoCaoAwA");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic play <url> <instanceName> §7- 播放音乐");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic fadeinplay <duration> <url> <instanceName> §7- 淡入播放");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic fadeoutinplay <instanceName> <totalDuration> <url> §7- 淡出淡入播放");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic stop <instanceName> §7- 停止指定音乐");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic stopall §7- 停止所有音乐");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic fadeoutstop <instanceName> [duration] §7- 淡出停止");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic setvolume <volume> <instanceName> §7- 设置实例音量");
        sender.sendMessage("[§aXc音乐§f]/xcareamusic setmastervolume <volume> §7- 设置主音量");
    }

    @Override
    protected @Nullable String getUnknownSubCommandMessage() {
        return "§c未知的子命令. 输入/xcareamusic查看帮助.";
    }

    @Override
    protected @Nullable String getNoPermissionMessage() {
        return "§c你没有权限执行此命令！";
    }

}