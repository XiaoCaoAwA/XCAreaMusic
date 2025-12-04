package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 淡出停止音乐指令
 * 用法: /xcareamusic fadeoutstop [玩家ID] <instanceName> [duration]
 */
public class SubFadeOutStopCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "fadeoutstop";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "淡出停止音乐";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic fadeoutstop [玩家ID] <instanceName> [duration]";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player targetPlayer;
        String instanceName;
        Long duration = null;

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
            if (args.length >= 3) {
                try {
                    duration = Long.parseLong(args[2]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("§c无效的时长参数，请输入数字！");
                    return;
                }
            }
        } else if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 2) {
                // 尝试判断第一个参数是否为玩家名
                Player possiblePlayer = Bukkit.getPlayer(args[0]);
                if (possiblePlayer != null && args.length >= 2) {
                    // 第一个参数是玩家名
                    targetPlayer = possiblePlayer;
                    instanceName = args[1];
                    if (args.length >= 3) {
                        try {
                            duration = Long.parseLong(args[2]);
                        } catch (NumberFormatException e) {
                            player.sendMessage("§c无效的时长参数，请输入数字！");
                            return;
                        }
                    }
                } else {
                    // 第一个参数不是玩家名，作用于自己
                    targetPlayer = player;
                    instanceName = args[0];
                    if (args.length >= 2) {
                        try {
                            duration = Long.parseLong(args[1]);
                        } catch (NumberFormatException e) {
                            player.sendMessage("§c无效的时长参数，请输入数字！");
                            return;
                        }
                    }
                }
            } else if (args.length >= 1) {
                // 作用于自己
                targetPlayer = player;
                instanceName = args[0];
            } else {
                player.sendMessage("§c用法: " + getUsage());
                return;
            }
        } else {
            return;
        }

        if (duration != null) {
            BGMNetwork.sendFadeOutStop(targetPlayer, duration, instanceName);
            sender.sendMessage("§a正在为玩家 §e" + targetPlayer.getName() + " §a淡出停止音乐: §e" + instanceName + " §7(淡出时长: " + duration + "ms)");
        } else {
            BGMNetwork.sendFadeOutStop(targetPlayer, instanceName);
            sender.sendMessage("§a正在为玩家 §e" + targetPlayer.getName() + " §a淡出停止音乐: §e" + instanceName + " §7(默认淡出时长)");
        }
    }
}

