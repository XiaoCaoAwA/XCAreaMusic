package XiaoCaoAwA.plugins.XCAreaMusic.area;

import XiaoCaoAwA.plugins.XCAreaMusic.XCAreaMusic;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * 区域指令执行器
 * 处理配置文件中定义的各种指令类型
 * 
 * @author XiaoCaoAwA
 * @version 1.0
 */
public class AreaCommandExecutor {
    
    /**
     * 执行指令
     * 
     * @param player 玩家
     * @param command 指令字符串
     */
    public static void executeCommand(Player player, String command) {
        if (command == null || command.isEmpty()) {
            return;
        }

        String processedCommand = processPlaceholders(player, command);

        if (processedCommand.startsWith("[command]")) {
            executePlayerCommand(player, processedCommand.substring(9));
        } else if (processedCommand.startsWith("[op]")) {
            executeOpCommand(player, processedCommand.substring(4));
        } else if (processedCommand.startsWith("[console]")) {
            executeConsoleCommand(processedCommand.substring(9));
        } else if (processedCommand.startsWith("[broadcast]")) {
            executeBroadcast(processedCommand.substring(11));
        } else if (processedCommand.startsWith("[tell]")) {
            executeTell(player, processedCommand.substring(6));
        } else {
            // 默认作为玩家指令执行
            executePlayerCommand(player, processedCommand);
        }
    }
    
    /**
     * 处理 PlaceholderAPI 变量和内置变量
     */
    private static String processPlaceholders(Player player, String text) {

        text = text.replace("{p}", player.getName())
                   .replace("{player}", player.getName())
                   .replace("{world}", player.getWorld().getName())
                   .replace("{x}", String.valueOf(player.getLocation().getBlockX()))
                   .replace("{y}", String.valueOf(player.getLocation().getBlockY()))
                   .replace("{z}", String.valueOf(player.getLocation().getBlockZ()));
        

        text = ChatColor.translateAlternateColorCodes('&', text);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            try {
                text = PlaceholderAPI.setPlaceholders(player, text);
            } catch (Exception e) {
                XCAreaMusic.debug("PlaceholderAPI 变量替换失败: " + e.getMessage());
            }
        }
        
        return text;
    }
    
    /**
     * 玩家执行指令
     */
    private static void executePlayerCommand(Player player, String command) {
        XCAreaMusic.debug("玩家 " + player.getName() + " 执行指令: " + command);
        Bukkit.dispatchCommand(player, command);
    }
    
    /**
     * 玩家以 OP 权限执行指令
     */
    private static void executeOpCommand(Player player, String command) {
        boolean wasOp = player.isOp();
        try {
            player.setOp(true);
            XCAreaMusic.debug("玩家 " + player.getName() + " 以 OP 执行指令: " + command);
            Bukkit.dispatchCommand(player, command);
        } finally {
            player.setOp(wasOp);
        }
    }
    
    /**
     * 控制台执行指令
     */
    private static void executeConsoleCommand(String command) {
        XCAreaMusic.debug("控制台执行指令: " + command);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }
    
    /**
     * 广播消息
     */
    private static void executeBroadcast(String message) {
        XCAreaMusic.debug("广播消息: " + message);
        Bukkit.broadcastMessage(message);
    }
    
    /**
     * 发送消息给玩家
     */
    private static void executeTell(Player player, String message) {
        XCAreaMusic.debug("发送消息给 " + player.getName() + ": " + message);
        player.sendMessage(message);
    }
}

