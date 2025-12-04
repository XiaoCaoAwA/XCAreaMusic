package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 播放音乐指令
 * 用法: /xcareamusic play [玩家ID] <url> <instanceName>
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
        return "/xcareamusic play [玩家ID] <url> <instanceName>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player targetPlayer;
        String url;
        String instanceName;

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
            url = args[1];
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
                url = args[1];
                instanceName = args[2];
            } else if (args.length >= 2) {
                // 没有指定玩家，作用于自己
                targetPlayer = player;
                url = args[0];
                instanceName = args[1];
            } else {
                player.sendMessage("§c用法: " + getUsage());
                return;
            }
        } else {
            return;
        }

        BGMNetwork.sendPlayBGM(targetPlayer, url, instanceName);
        sender.sendMessage("§a正在为玩家 §e" + targetPlayer.getName() + " §a播放音乐: §e" + instanceName);
    }
}

