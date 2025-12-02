package XiaoCaoAwA.plugins.XCAreaMusic;

import XiaoCaoAwA.plugins.XCAreaMusic.command.MainCommand;
import lombok.Getter;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class XCAreaMusic extends JavaPlugin {

    @Getter
    public static XCAreaMusic instance;

    @Override
    public void onEnable() {
        instance = this;
        
        // 注册指令
        registerCommands();
        
        getLogger().info("XCAreaMusic 插件已启用！");
    }

    @Override
    public void onDisable() {
        getLogger().info("XCAreaMusic 插件已禁用！");
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
            getLogger().warning("无法注册指令 xcareamusic");
        }
    }
}
