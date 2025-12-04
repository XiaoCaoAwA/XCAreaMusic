package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 设置实例音量指令
 * 用法: /xcareamusic setvolume [玩家ID] <volume> <instanceName>
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
        return "/xcareamusic setvolume [玩家ID] <volume> <instanceName>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player targetPlayer;
        float volume;
        String instanceName;

        try {
            if (sender instanceof ConsoleCommandSender) {
                // 控制台必须指定玩家
                if (args.length < 3) {
                    sender.sendMessage("§c控制台用法: " + getUsage());
                    return;
                }
                targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage("§c玩家 " + args[0] + " 不在线！");
                    return;
                }
                volume = Float.parseFloat(args[1]);
                instanceName = args[2];
            } else if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length >= 3) {
                    // 指定了玩家
                    targetPlayer = Bukkit.getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage("§c玩家 " + args[0] + " 不在线！");
                        return;
                    }
                    volume = Float.parseFloat(args[1]);
                    instanceName = args[2];
                } else if (args.length >= 2) {
                    // 没有指定玩家，作用于自己
                    targetPlayer = player;
                    volume = Float.parseFloat(args[0]);
                    instanceName = args[1];
                } else {
                    player.sendMessage("§c用法: " + getUsage());
                    return;
                }
            } else {
                return;
            }

            if (volume < 0.0f || volume > 1.0f) {
                sender.sendMessage("§c音量值必须在 0.0 到 1.0 之间！");
                return;
            }

            BGMNetwork.sendSetVolume(targetPlayer, volume, instanceName);
            sender.sendMessage("§a已为玩家 §e" + targetPlayer.getName() + " §a设置音乐实例 §e" + instanceName + " §a的音量为: §e" + volume);
        } catch (NumberFormatException e) {
            sender.sendMessage("§c无效的音量参数，请输入 0.0 到 1.0 之间的数字！");
        }
    }
}

