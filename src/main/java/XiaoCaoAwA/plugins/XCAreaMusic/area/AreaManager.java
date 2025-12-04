package XiaoCaoAwA.plugins.XCAreaMusic.area;

import XiaoCaoAwA.plugins.XCAreaMusic.XCAreaMusic;
import XiaoCaoAwA.plugins.XCAreaMusic.config.AreaConfigManager;
import XiaoCaoAwA.plugins.XCAreaMusic.config.BaseConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 区域管理器
 * 负责加载、管理所有区域，并跟踪玩家所在的区域
 * 
 * @author XiaoCaoAwA
 * @version 1.0
 */
@Getter
public class AreaManager {
    
    private static AreaManager instance;
    private final List<Area> areas;
    // 记录玩家当前所在的区域
    private final Map<UUID, Set<String>> playerAreas;
    
    public AreaManager() {
        instance = this;
        this.areas = new ArrayList<>();
        this.playerAreas = new ConcurrentHashMap<>();
        loadAllAreas();
    }
    
    public static AreaManager getInstance() {
        return instance;
    }
    
    /**
     * 加载所有区域配置
     */
    public void loadAllAreas() {
        areas.clear();
        
        AreaConfigManager configManager = AreaConfigManager.getInstance();
        if (configManager == null) {
            XCAreaMusic.warning("AreaConfigManager 未初始化，无法加载区域");
            return;
        }
        
        Map<String, BaseConfig> configs = configManager.getAllAreaConfigs();
        
        for (Map.Entry<String, BaseConfig> entry : configs.entrySet()) {
            String fileName = entry.getKey();
            BaseConfig config = entry.getValue();
            
            loadAreasFromConfig(fileName, config);
        }
        
        XCAreaMusic.info("成功加载 " + areas.size() + " 个区域");
    }
    
    /**
     * 从配置文件加载区域
     */
    private void loadAreasFromConfig(String fileName, BaseConfig config) {
        Set<String> keys = config.getKeys(false);
        
        for (String key : keys) {
            try {
                ConfigurationSection section = config.getConfigurationSection(key);
                if (section == null) {
                    continue;
                }
                
                // 读取世界
                String worldName = section.getString("world");
                if (worldName == null) {
                    XCAreaMusic.warning("区域 " + key + " 缺少世界配置");
                    continue;
                }
                
                World world = Bukkit.getWorld(worldName);
                if (world == null) {
                    XCAreaMusic.warning("区域 " + key + " 的世界 " + worldName + " 不存在");
                    continue;
                }
                
                // 读取坐标
                int x1 = section.getInt("x1", 0);
                int y1 = section.getInt("y1", 0);
                int z1 = section.getInt("z1", 0);
                int x2 = section.getInt("x2", 0);
                int y2 = section.getInt("y2", 0);
                int z2 = section.getInt("z2", 0);
                
                // 读取指令列表
                List<String> joinCommands = section.getStringList("JoinCommands");
                List<String> leaveCommands = section.getStringList("LeaveCommands");
                
                Area area = new Area(key, fileName, world, x1, y1, z1, x2, y2, z2, 
                                   joinCommands, leaveCommands);
                areas.add(area);
                
                XCAreaMusic.debug("加载区域: " + area.getUniqueId() + " - " + area);
                
            } catch (Exception e) {
                XCAreaMusic.severe("加载区域 " + key + " 时出错: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 重新加载所有区域
     */
    public void reloadAreas() {
        XCAreaMusic.debug("重新加载所有区域...");
        loadAllAreas();
    }
    
    /**
     * 检查玩家进入/离开区域
     * 
     * @param player 玩家
     */
    public void checkPlayerAreas(Player player) {
        UUID playerId = player.getUniqueId();
        Set<String> currentAreas = playerAreas.getOrDefault(playerId, new HashSet<>());
        Set<String> newAreas = new HashSet<>();

        for (Area area : areas) {
            if (area.contains(player.getLocation())) {
                newAreas.add(area.getUniqueId());

                if (!currentAreas.contains(area.getUniqueId())) {
                    onPlayerEnterArea(player, area);
                }
            }
        }

        for (String areaId : currentAreas) {
            if (!newAreas.contains(areaId)) {
                Area area = getAreaById(areaId);
                if (area != null) {
                    onPlayerLeaveArea(player, area);
                }
            }
        }

        if (newAreas.isEmpty()) {
            playerAreas.remove(playerId);
        } else {
            playerAreas.put(playerId, newAreas);
        }
    }
    
    /**
     * 玩家进入区域
     */
    private void onPlayerEnterArea(Player player, Area area) {
        XCAreaMusic.debug("玩家 " + player.getName() + " 进入区域: " + area.getUniqueId());

        for (String command : area.getJoinCommands()) {
            AreaCommandExecutor.executeCommand(player, command);
        }
    }
    
    /**
     * 玩家离开区域
     */
    private void onPlayerLeaveArea(Player player, Area area) {
        XCAreaMusic.debug("玩家 " + player.getName() + " 离开区域: " + area.getUniqueId());

        for (String command : area.getLeaveCommands()) {
            AreaCommandExecutor.executeCommand(player, command);
        }
    }
    
    /**
     * 根据唯一ID获取区域
     */
    private Area getAreaById(String uniqueId) {
        for (Area area : areas) {
            if (area.getUniqueId().equals(uniqueId)) {
                return area;
            }
        }
        return null;
    }
    
    /**
     * 玩家退出服务器时清理数据
     */
    public void onPlayerQuit(Player player) {
        playerAreas.remove(player.getUniqueId());
    }
    
    /**
     * 获取区域数量
     */
    public int getAreaCount() {
        return areas.size();
    }
}

