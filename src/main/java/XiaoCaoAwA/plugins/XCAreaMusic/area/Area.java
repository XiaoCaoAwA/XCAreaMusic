package XiaoCaoAwA.plugins.XCAreaMusic.area;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

/**
 * 区域数据类
 * 
 * @author XiaoCaoAwA
 * @version 1.0
 */
@Getter
public class Area {
    
    private final String id;
    private final String fileName;
    private final World world;
    private final int minX;
    private final int minY;
    private final int minZ;
    private final int maxX;
    private final int maxY;
    private final int maxZ;
    private final List<String> joinCommands;
    private final List<String> leaveCommands;
    
    public Area(String id, String fileName, World world, 
                int x1, int y1, int z1, 
                int x2, int y2, int z2,
                List<String> joinCommands, 
                List<String> leaveCommands) {
        this.id = id;
        this.fileName = fileName;
        this.world = world;
        this.minX = Math.min(x1, x2);
        this.minY = Math.min(y1, y2);
        this.minZ = Math.min(z1, z2);
        this.maxX = Math.max(x1, x2);
        this.maxY = Math.max(y1, y2);
        this.maxZ = Math.max(z1, z2);
        this.joinCommands = joinCommands;
        this.leaveCommands = leaveCommands;
    }
    
    /**
     * 检查位置是否在区域内
     * 
     * @param location 要检查的位置
     * @return true 如果在区域内
     */
    public boolean contains(Location location) {
        if (location.getWorld() == null || !location.getWorld().equals(world)) {
            return false;
        }
        
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        
        return x >= minX && x <= maxX
            && y >= minY && y <= maxY
            && z >= minZ && z <= maxZ;
    }
    
    /**
     * 获取区域的唯一标识符
     * 
     * @return 格式: fileName:id
     */
    public String getUniqueId() {
        return fileName + ":" + id;
    }
    
    @Override
    public String toString() {
        return "Area{" +
                "id='" + id + '\'' +
                ", world=" + world.getName() +
                ", pos1=(" + minX + "," + minY + "," + minZ + ")" +
                ", pos2=(" + maxX + "," + maxY + "," + maxZ + ")" +
                '}';
    }
}

