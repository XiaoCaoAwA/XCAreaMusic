package XiaoCaoAwA.plugins.XCAreaMusic.command;

import XiaoCaoAwA.plugins.XCAreaMusic.config.AreaConfigManager;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 重载配置指令
 * 用法: /xcareamusic reload
 * 
 * @author XiaoCaoAwA
 * @version 1.0
 */
public class SubReloadCommand extends SubCommand {

    @NotNull
    @Override
    public String getName() {
        return "reload";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "重载所有配置文件";
    }

    @Override
    public String getUsage() {
        return "/xcareamusic reload";
    }

    @Nullable
    @Override
    public String getPermission() {
        return "xcareamusic.reload";
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        try {
            AreaConfigManager manager = AreaConfigManager.getInstance();
            if (manager != null) {
                manager.reloadAllConfigs();
                sender.sendMessage("§e[§aXc音乐§e] §a配置文件重载成功！");
            } else {
                sender.sendMessage("§e[§aXc音乐§e] §c配置管理器未初始化！");
            }
        } catch (Exception e) {
            sender.sendMessage("§e[§aXc音乐§e] §c配置文件重载失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

