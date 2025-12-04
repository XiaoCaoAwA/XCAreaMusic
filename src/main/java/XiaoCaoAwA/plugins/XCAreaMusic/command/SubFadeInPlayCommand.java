package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 淡入播放音乐指令
 * 用法: /xcareamusic fadeinplay [玩家ID] <duration> <url> <instanceName>
 */
public class SubFadeInPlayCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "fadeinplay";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "淡入播放音乐";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic fadeinplay [玩家ID] <duration> <url> <instanceName>";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player targetPlayer;
        long duration;
        String url;
        String instanceName;

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
                duration = Long.parseLong(args[1]);
                url = args[2];
                instanceName = args[3];
            } else if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length >= 4) {
                    // 指定了玩家
                    targetPlayer = Bukkit.getPlayer(args[0]);
                    if (targetPlayer == null) {
                        player.sendMessage("§c玩家 " + args[0] + " 不在线！");
                        return;
                    }
                    duration = Long.parseLong(args[1]);
                    url = args[2];
                    instanceName = args[3];
                } else if (args.length >= 3) {
                    // 没有指定玩家，作用于自己
                    targetPlayer = player;
                    duration = Long.parseLong(args[0]);
                    url = args[1];
                    instanceName = args[2];
                } else {
                    player.sendMessage("§c用法: " + getUsage());
                    return;
                }
            } else {
                return;
            }

            BGMNetwork.sendFadeInPlay(targetPlayer, duration, url, instanceName);
            sender.sendMessage("§a正在为玩家 §e" + targetPlayer.getName() + " §a淡入播放音乐: §e" + instanceName + " §7(淡入时长: " + duration + "ms)");
        } catch (NumberFormatException e) {
            sender.sendMessage("§c无效的时长参数，请输入数字！");
        }
    }
}

