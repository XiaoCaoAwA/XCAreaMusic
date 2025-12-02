package XiaoCaoAwA.plugins.XCAreaMusic.config;

import XiaoCaoAwA.plugins.XCAreaMusic.XCAreaMusic;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author XiaoCaoAwA
 * @version 1.0
 */
@Getter
public class MainConfig extends BaseConfig {

    public static final MainConfig INSTANCE = new MainConfig(XCAreaMusic.instance, "config.yml");


    private MainConfig(JavaPlugin plugin, String fileName) {
        super(plugin, fileName);
        saveDefault();
    }

    @Override
    public void load() {
        super.load();

    }

}
