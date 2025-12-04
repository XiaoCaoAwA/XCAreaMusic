package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 停止音乐指令
 * 用法: /xcareamusic stop [玩家ID] <instanceName>
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
        return "/xcareamusic stop [玩家ID] <instanceName>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player targetPlayer;
        String instanceName;

        if (sender instanceof ConsoleCommandSender) {
            // 控制台必须指定玩家
            if (args.length < 2) {
                sender.sendMessage("§c控制台用法: " + getUsage());
                return;
            }
            targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage("§c玩家 " + args[0] + " 不在线！");
                return;
            }
            instanceName = args[1];
        } else if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 2) {
                // 指定了玩家
                targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer == null) {
                    player.sendMessage("§c玩家 " + args[0] + " 不在线！");
                    return;
                }
                instanceName = args[1];
            } else if (args.length >= 1) {
                // 没有指定玩家，作用于自己
                targetPlayer = player;
                instanceName = args[0];
            } else {
                player.sendMessage("§c用法: " + getUsage());
                return;
            }
        } else {
            return;
        }

        BGMNetwork.sendStopBGM(targetPlayer, instanceName);
        sender.sendMessage("§a已为玩家 §e" + targetPlayer.getName() + " §a停止音乐: §e" + instanceName);
    }
}

