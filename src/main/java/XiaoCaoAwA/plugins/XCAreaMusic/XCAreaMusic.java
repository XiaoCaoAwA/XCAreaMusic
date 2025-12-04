package XiaoCaoAwA.plugins.XCAreaMusic;

import XiaoCaoAwA.plugins.XCAreaMusic.area.AreaManager;
import XiaoCaoAwA.plugins.XCAreaMusic.command.MainCommand;
import XiaoCaoAwA.plugins.XCAreaMusic.config.AreaConfigManager;
import XiaoCaoAwA.plugins.XCAreaMusic.config.MainConfig;
import XiaoCaoAwA.plugins.XCAreaMusic.listener.AreaListener;
import lombok.Getter;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class XCAreaMusic extends JavaPlugin {

    @Getter
    public static XCAreaMusic instance;
    
    @Getter
    private AreaConfigManager areaConfigManager;
    
    @Getter
    private AreaManager areaManager;

    @Override
    public void onEnable() {
        instance = this;

        loadConfigs();

        areaManager = new AreaManager();

        registerListeners();

        registerCommands();
        
        XCAreaMusic.info("XCAreaMusic 插件已启用！");
    }

    @Override
    public void onDisable() {
        XCAreaMusic.info("XCAreaMusic 插件已禁用！");
    }

    /**
     * 加载配置文件
     */
    private void loadConfigs() {
        saveDefaultConfig();

        MainConfig.INSTANCE.load();

        areaConfigManager = new AreaConfigManager(this);
        
        XCAreaMusic.info("配置文件加载完成！");
    }
    
    /**
     * 注册事件监听器
     */
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new AreaListener(), this);
        XCAreaMusic.info("事件监听器注册完成！");
    }
    
    /**
     * 注册所有指令
     */
    private void registerCommands() {
        PluginCommand command = getCommand("xcareamusic");
        if (command != null) {
            MainCommand mainCommand = new MainCommand();
            command.setExecutor(mainCommand);
            command.setTabCompleter(mainCommand);
        } else {
            XCAreaMusic.warning("无法注册指令 xcareamusic");
        }
    }

    /**
     * 输出调试日志
     * 只有当 config.yml 中 debug: true 时才会输出
     * 
     * @param message 日志消息
     * @param objects 格式化参数
     */
    public static void debug(String message, Object... objects) {
        if (MainConfig.INSTANCE.isDebug()) {
            instance.getLogger().log(Level.INFO, message, objects);
        }
    }

    /**
     * 输出普通信息日志
     * 
     * @param message 日志消息
     * @param objects 格式化参数
     */
    public static void info(String message, Object... objects) {
        instance.getLogger().log(Level.INFO, message, objects);
    }

    /**
     * 输出警告日志
     * 
     * @param message 日志消息
     * @param objects 格式化参数
     */
    public static void warning(String message, Object... objects) {
        instance.getLogger().log(Level.WARNING, message, objects);
    }

    /**
     * 输出严重错误日志
     * 
     * @param message 日志消息
     * @param objects 格式化参数
     */
    public static void severe(String message, Object... objects) {
        instance.getLogger().log(Level.SEVERE, message, objects);
    }
}
