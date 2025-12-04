package XiaoCaoAwA.plugins.XCAreaMusic.network;

import org.bukkit.entity.Player;
import priv.seventeen.artist.arcartx.core.entity.data.ArcartXPlayer;
import priv.seventeen.artist.arcartx.util.PlayerUtils;

/**
 * @program: BGMNetwork
 * @description: XCAreaMusic 区域音乐网络通讯工具类
 * @author: XiaoCaoAwA
 * @create: 2025-12-02
 **/
public class BGMNetwork {

    private static final String PACKET_ID = "arcartx_xcmusic";

    /**
     * 播放音乐
     * @param player 玩家
     * @param url 音乐URL或文件路径
     * @param instanceName 实例名称（用于区分不同的音乐播放器）
     */
    public static void sendPlayBGM(Player player, String url, String instanceName) {
        if (player == null || url == null || instanceName == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"play", url, instanceName};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }

    /**
     * 淡入播放音乐
     * @param player 玩家
     * @param duration 淡入时长（毫秒）
     * @param url 音乐URL或文件路径
     * @param instanceName 实例名称
     */
    public static void sendFadeInPlay(Player player, long duration, String url, String instanceName) {
        if (player == null || url == null || instanceName == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"fadeInPlay", String.valueOf(duration), url, instanceName};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }

    /**
     * 停止所有音乐实例
     * @param player 玩家
     */
    public static void sendStopAllBGM(Player player) {
        if (player == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"stop"};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }

    /**
     * 停止指定音乐实例
     * @param player 玩家
     * @param instanceName 实例名称
     */
    public static void sendStopBGM(Player player, String instanceName) {
        if (player == null || instanceName == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"stop", instanceName};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }

    /**
     * 淡出停止音乐（默认3秒）
     * @param player 玩家
     * @param instanceName 实例名称
     */
    public static void sendFadeOutStop(Player player, String instanceName) {
        if (player == null || instanceName == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"fadeOutStop", instanceName};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }

    /**
     * 淡出停止音乐（指定时长）
     * @param player 玩家
     * @param duration 淡出时长（毫秒）
     * @param instanceName 实例名称
     */
    public static void sendFadeOutStop(Player player, long duration, String instanceName) {
        if (player == null || instanceName == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"fadeOutStop", String.valueOf(duration), instanceName};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }

    /**
     * 设置主音量
     * @param player 玩家
     * @param volume 音量值（0.0 - 1.0）
     */
    public static void sendSetMasterVolume(Player player, float volume) {
        if (player == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"set_master_volume", String.valueOf(volume)};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }

    /**
     * 设置实例音量
     * @param player 玩家
     * @param volume 音量值（0.0 - 1.0）
     * @param instanceName 实例名称
     */
    public static void sendSetVolume(Player player, float volume, String instanceName) {
        if (player == null || instanceName == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"set_volume", String.valueOf(volume), instanceName};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }

    /**
     * 淡出淡入播放音乐
     * @param player 玩家
     * @param instanceName 实例名称（要淡出停止的实例）
     * @param totalDuration 总时长（毫秒）
     * @param url 音乐URL或文件路径
     */
    public static void sendFadeOutInPlay(Player player, String instanceName, long totalDuration, String url) {
        if (player == null || instanceName == null || url == null) {
            return;
        }
        
        ArcartXPlayer arcartXPlayer = PlayerUtils.getArcartXHandler(player);
        if (arcartXPlayer != null) {
            String[] args = new String[]{"fadeOutInPlay", instanceName, String.valueOf(totalDuration), url};
            arcartXPlayer.sendCustomPacket(PACKET_ID, args);
        }
    }
}
