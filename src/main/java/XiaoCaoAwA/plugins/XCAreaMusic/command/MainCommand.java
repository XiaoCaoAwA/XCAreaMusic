package XiaoCaoAwA.plugins.XCAreaMusic.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.Nullable;

/**
 * 命令管理器，用于注册和管理所有子命令
 */
public class MainCommand extends AbstractMainCommand {

    public MainCommand() {
        // 注册所有子命令
        registerSubCommand(new SubHelpCommand());
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
    }

    @Override
    protected @Nullable String getUnknownSubCommandMessage() {
        return "§c未知的子命令. 输入xcareamusic help §c查看帮助.";
    }

    @Override
    protected @Nullable String getNoPermissionMessage() {
        return "§c你没有权限执行此命令！";
    }

}