package XiaoCaoAwA.plugins.XCAreaMusic.config;

import XiaoCaoAwA.plugins.XCAreaMusic.XCAreaMusic;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 区域配置管理器
 * 用于加载和管理 area 文件夹中的所有配置文件
 * 
 * @author XiaoCaoAwA
 * @version 1.0
 */
@Getter
public class AreaConfigManager {

    @Getter
    private static AreaConfigManager instance;
    private final XCAreaMusic plugin;
    private final File areaFolder;
    private final Map<String, BaseConfig> areaConfigs;

    public AreaConfigManager(XCAreaMusic plugin) {
        this.plugin = plugin;
        this.areaFolder = new File(plugin.getDataFolder(), "area");
        this.areaConfigs = new HashMap<>();
        instance = this;

        ensureAreaFolderExists();

        loadAllAreaConfigs();
    }

    /**
     * 确保 area 文件夹存在
     */
    private void ensureAreaFolderExists() {
        if (!areaFolder.exists()) {
            if (areaFolder.mkdirs()) {
                XCAreaMusic.info("创建 area 文件夹成功");
            } else {
                XCAreaMusic.severe("创建 area 文件夹失败");
            }
        }

        File exampleFile = new File(areaFolder, "example.yml");
        if (!exampleFile.exists()) {
            plugin.saveResource("area/example.yml", false);
        }
    }

    /**
     * 加载所有区域配置文件
     */
    public void loadAllAreaConfigs() {
        areaConfigs.clear();
        
        File[] files = areaFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));
        
        if (files == null || files.length == 0) {
            XCAreaMusic.warning("area 文件夹中没有找到任何配置文件");
            return;
        }
        
        XCAreaMusic.debug("开始加载区域配置文件，找到 " + files.length + " 个文件");
        
        int loadedCount = 0;
        for (File file : files) {
            try {
                BaseConfig config = new BaseConfig(plugin, file);
                config.load();
                areaConfigs.put(file.getName(), config);
                loadedCount++;
            } catch (Exception e) {
                XCAreaMusic.severe("加载区域配置文件失败: " + file.getName());
                e.printStackTrace();
            }
        }
        
        XCAreaMusic.info("成功加载 " + loadedCount + " 个区域配置文件");
    }

    /**
     * 重载所有配置
     */
    public void reloadAllConfigs() {
        MainConfig.INSTANCE.load();
        XCAreaMusic.debug("主配置文件重载完成");

        loadAllAreaConfigs();
        XCAreaMusic.debug("区域配置文件重载完成");

        XiaoCaoAwA.plugins.XCAreaMusic.area.AreaManager manager = 
            XiaoCaoAwA.plugins.XCAreaMusic.area.AreaManager.getInstance();
        if (manager != null) {
            manager.reloadAreas();
            XCAreaMusic.debug("区域管理器重载完成");
        }
        
        XCAreaMusic.info("配置文件重载完成！");
    }

    /**
     * 根据文件名获取区域配置
     * 
     * @param fileName 文件名（包含 .yml 扩展名）
     * @return 区域配置对象，如果不存在则返回 null
     */
    public BaseConfig getAreaConfig(String fileName) {
        return areaConfigs.get(fileName);
    }

    /**
     * 获取所有区域配置
     * 
     * @return 所有区域配置的映射
     */
    public Map<String, BaseConfig> getAllAreaConfigs() {
        return new HashMap<>(areaConfigs);
    }

    /**
     * 获取区域配置数量
     * 
     * @return 配置文件数量
     */
    public int getConfigCount() {
        return areaConfigs.size();
    }
}

