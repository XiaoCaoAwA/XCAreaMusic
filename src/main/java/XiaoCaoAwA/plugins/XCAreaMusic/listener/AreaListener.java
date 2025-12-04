package XiaoCaoAwA.plugins.XCAreaMusic.listener;

import XiaoCaoAwA.plugins.XCAreaMusic.area.AreaManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * 区域事件监听器
 * 监听玩家移动、传送、进入服务器等事件
 * 
 * @author XiaoCaoAwA
 * @version 1.0
 */
public class AreaListener implements Listener {
    
    /**
     * 监听玩家移动事件
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getFrom().getBlockX() != event.getTo().getBlockX()
            || event.getFrom().getBlockY() != event.getTo().getBlockY()
            || event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
            
            Player player = event.getPlayer();
            AreaManager manager = AreaManager.getInstance();
            if (manager != null) {
                manager.checkPlayerAreas(player);
            }
        }
    }
    
    /**
     * 监听玩家传送事件
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        AreaManager manager = AreaManager.getInstance();
        if (manager != null) {
            org.bukkit.Bukkit.getScheduler().runTaskLater(
                XiaoCaoAwA.plugins.XCAreaMusic.XCAreaMusic.getInstance(),
                () -> manager.checkPlayerAreas(player),
                5L
            );
        }
    }
    
    /**
     * 监听玩家加入服务器事件
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        AreaManager manager = AreaManager.getInstance();
        if (manager != null) {
            // 延迟检测，确保玩家完全加载
            org.bukkit.Bukkit.getScheduler().runTaskLater(
                XiaoCaoAwA.plugins.XCAreaMusic.XCAreaMusic.getInstance(),
                () -> manager.checkPlayerAreas(player),
                10L
            );
        }
    }
    
    /**
     * 监听玩家退出服务器事件
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        AreaManager manager = AreaManager.getInstance();
        if (manager != null) {
            manager.onPlayerQuit(player);
        }
    }
}

