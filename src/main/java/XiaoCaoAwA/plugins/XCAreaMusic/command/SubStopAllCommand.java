package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.network.BGMNetwork;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * 停止所有音乐指令
 * 用法: /xcareamusic stopall [玩家ID]
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
        return "/xcareamusic stopall [玩家ID]";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Player targetPlayer;

        if (sender instanceof ConsoleCommandSender) {
            // 控制台必须指定玩家
            if (args.length < 1) {
                sender.sendMessage("§c控制台用法: " + getUsage());
                return;
            }
            targetPlayer = Bukkit.getPlayer(args[0]);
            if (targetPlayer == null) {
                sender.sendMessage("§c玩家 " + args[0] + " 不在线！");
                return;
            }
        } else if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 1) {
                // 指定了玩家
                targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer == null) {
                    player.sendMessage("§c玩家 " + args[0] + " 不在线！");
                    return;
                }
            } else {
                // 没有指定玩家，作用于自己
                targetPlayer = player;
            }
        } else {
            return;
        }

        BGMNetwork.sendStopAllBGM(targetPlayer);
        sender.sendMessage("§a已为玩家 §e" + targetPlayer.getName() + " §a停止所有音乐");
    }
}

