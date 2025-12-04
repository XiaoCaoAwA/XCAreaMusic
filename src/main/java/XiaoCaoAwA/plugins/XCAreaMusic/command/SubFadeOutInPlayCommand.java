package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 淡出淡入播放音乐指令
 * 用法: /xcareamusic fadeoutinplay [玩家ID] <instanceName> <totalDuration> <url>
 */
public class SubFadeOutInPlayCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "fadeoutinplay";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "淡出当前音乐并淡入播放新音乐";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic fadeoutinplay [玩家ID] <instanceName> <totalDuration> <url>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player targetPlayer;
        String instanceName;
        long totalDuration;
        String url;

        try {
            if (sender instanceof ConsoleCommandSender) {
                // 控制台必须指定玩家
                if (args.length < 4) {
                    sender.sendMessage("§c控制台用法: " + getUsage());
                    return;
                }
                targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer == null) {
                    sender.sendMessage("§c玩家 " + args[0] + " 不在线！");
                    return;
                }
                instanceName = args[1];
                totalDuration = Long.parseLong(args[2]);
                url = args[3];
            } else if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length >= 4) {
                    // 指定了玩家
                    targetPlayer = Bukkit.getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage("§c玩家 " + args[0] + " 不在线！");
                        return;
                    }
                    instanceName = args[1];
                    totalDuration = Long.parseLong(args[2]);
                    url = args[3];
                } else if (args.length >= 3) {
                    // 没有指定玩家，作用于自己
                    targetPlayer = player;
                    instanceName = args[0];
                    totalDuration = Long.parseLong(args[1]);
                    url = args[2];
                } else {
                    player.sendMessage("§c用法: " + getUsage());
                    return;
                }
            } else {
                return;
            }

            BGMNetwork.sendFadeOutInPlay(targetPlayer, instanceName, totalDuration, url);
            sender.sendMessage("§a正在为玩家 §e" + targetPlayer.getName() + " §a淡出淡入播放音乐: §e" + instanceName + " §7(总时长: " + totalDuration + "ms)");
        } catch (NumberFormatException e) {
            sender.sendMessage("§c无效的时长参数，请输入数字！");
        }
    }
}

